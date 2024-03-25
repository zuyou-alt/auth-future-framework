package auth.future.service.platform.service.business;

import auth.future.api.platform.ApiInfoServiceApi;
import auth.future.api.platform.model.ApiInfoVo;
import auth.future.api.platform.model.request.RequestApiPage;
import auth.future.component.common.model.PageResult;
import auth.future.component.common.utils.ConversionUtil;
import auth.future.component.common.utils.PageFormatUtil;
import auth.future.component.redis.RedisUtil;
import auth.future.service.platform.beanconversion.ApiInfoMapperCvs;
import auth.future.service.platform.constant.ApiInfoRedisKey;
import auth.future.service.platform.entity.ApiInfo;
import auth.future.service.platform.entity.ApiPermissions;
import auth.future.service.platform.entity.RoleBind;
import auth.future.service.platform.service.ApiInfoService;
import auth.future.service.platform.service.ApiPermissionsService;
import auth.future.service.platform.service.RoleBindService;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 接口信息业务管理
 * 接口权限业务管理
 * @author hzy
 * @since 2023-11-22
 **/
@Service
public class BusinessApiInfoService implements ApiInfoServiceApi {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private ApiInfoService apiInfoService;

    @Resource
    private ApiPermissionsService apiPermissionsService;

    @Resource
    private RoleBindService roleBindService;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private Environment environment;

    @Override
    @Cacheable(cacheNames = ApiInfoRedisKey.API_INFO_KEY,key = "#apiId")
    public ApiInfoVo getApiInfo(String apiId) {
        ApiInfo apiInfo = apiInfoService.getById(apiId);
        return ApiInfoMapperCvs.INSTANCE.DbToVo(apiInfo);
    }

    @Override
    public String saveApiInfo(ApiInfoVo apiInfoVo) {
        Assert.isTrue(StrUtil.isNotBlank(apiInfoVo.getPattern()),"接口路径不能为空！");
        ApiInfo apiInfo = ApiInfoMapperCvs.INSTANCE.VoToDb(apiInfoVo);
        apiInfoService.saveOrUpdate(apiInfo);
        return apiInfo.getId();
    }

    @Transactional
    @Override
    public boolean removeApiInfos(List<String> apiIds) {
        if (apiIds==null || apiIds.isEmpty()){
            return false;
        }
        apiInfoService.removeBatchByIds(apiIds);
        apiPermissionsService.removeAllPermissionByApiIds(apiIds);
        return true;
    }

    @Override
    public PageResult<ApiInfoVo> pageApiInfo(RequestApiPage requestApiPage) {
        IPage<ApiInfo> apiInfoIPage = apiInfoService.pageApiInfo(requestApiPage);
        List<ApiInfoVo> apiInfoVos = ApiInfoMapperCvs.INSTANCE.DbListToVoList(apiInfoIPage.getRecords());
        return new PageResult<>(apiInfoIPage, apiInfoVos);
    }

    @Transactional
    @Override
    public boolean updateApiPermission(String apiId, List<String> roleIds) {
        Assert.isTrue(StrUtil.isNotBlank(apiId),"接口不能为空！");
        Assert.isTrue(roleIds!=null && ! roleIds.isEmpty(),"请选择权限！");
        apiPermissionsService.removeAllPermissionByApiIds(Collections.singletonList(apiId));
        List<ApiPermissions> apiPermissionList = new ArrayList<>();
        assert roleIds != null;
        for (String roleId : roleIds) {
            ApiPermissions apiPermissions = new ApiPermissions();
            apiPermissions.setApiId(apiId);
            apiPermissions.setRoleId(roleId);
            apiPermissionList.add(apiPermissions);
        }
        return apiPermissionsService.saveBatch(apiPermissionList);
    }

    @Override
    public boolean removeApiPermission(String apiId, List<String> roleIds) {
        if (StrUtil.isBlank(apiId) || roleIds==null || roleIds.isEmpty()){
            return false;
        }
        return apiPermissionsService.removePermission(apiId,roleIds);
    }

    @Override
    public List<String> queryApiPermissionByPattern(String apiPattern) {
        ApiInfo apiInfo = apiInfoService.getApiInfoByPattern(apiPattern);
        if (apiInfo==null) return new ArrayList<>();
        String id = apiInfo.getId();
        return queryApiPermissionById(id);
    }

    @Override
    public List<String> queryApiPermissionById(String apiId) {
        List<RoleBind> roleBindList = roleBindService.queryRoleListByBind(apiId);
        return roleBindList.stream().map(RoleBind::getRoleId).collect(Collectors.toList());
    }

    @Override
    public Map<String, List<String>> queryApiRoles() {
        Map<String, List<String>>  result = new HashMap<>();
        List<Map<String, String>> maps = apiInfoService.queryApiRoles();
        Map<String, List<Map<String, String>>> fPattern = maps.stream().collect(Collectors.groupingBy(o -> o.get("F_PATTERN"),Collectors.toList()));
        Set<Map.Entry<String, List<Map<String, String>>>> entries = fPattern.entrySet();
        for (Map.Entry<String, List<Map<String, String>>> entry : entries) {
            List<Map<String, String>> value = entry.getValue();
            String key = entry.getKey();
            result.put(key,value.stream().map(o->o.get("F_ROLE_ID")).collect(Collectors.toList()));
        }
       return result;
    }

    public List<Map<String,String>> getAllApi(){
        return getInterfaceList();
    }



    public Map<String,Object> refreshApiList(String clientId){
        List<ApiInfo> apiInfoList = apiInfoService.list();
        Map<String, List<ApiInfo>> apiMap = apiInfoList.stream().collect(Collectors.groupingBy(ApiInfo::getPattern, Collectors.toList()));
        List<Map<String, String>> allApi = getAllApi();
        List<ApiInfo> apiInfos  = new ArrayList<>();
        for (Map<String, String> map : allApi) {
            String pattern = map.get("pattern");
            String patternPre = map.get("patternPre");
            String method = map.get("method");
            List<ApiInfo> apiArr = apiMap.get(pattern);
            if (apiArr==null || apiArr.isEmpty()){
                ApiInfo apiInfo = new ApiInfo();
                apiInfo.setPattern(pattern);
                apiInfo.setApiMethod(method);
                apiInfo.setPatternPre(patternPre);
                apiInfo.setAppId(clientId);
                apiInfos.add(apiInfo);
            }
        }
        apiInfoService.saveBatch(apiInfos);
        Map<String ,Object> result = new HashMap<>();
        result.put("update",apiInfos.size());
        return result;
    }

    private List<Map<String,String>> getInterfaceList(){
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 拿到Handler适配器中的全部方法
        Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
        List<Map<String,String>> urlList = new ArrayList<>();
        for (RequestMappingInfo info : methodMap.keySet()){
            Map<String,String> map = new HashMap<>();
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            PathPatternsRequestCondition pathPatternsCondition = info.getPathPatternsCondition();
            if (pathPatternsCondition==null) continue;
            Set<RequestMethod> methods = methodsCondition.getMethods();
            String methodResult = "";
            for (RequestMethod method : methods) {
                HttpMethod httpMethod = method.asHttpMethod();
                methodResult = httpMethod.toString();
            }
            map.put("method",methodResult);
            Set<PathPattern> patterns = pathPatternsCondition.getPatterns();
            String patternResult = "";
            for (PathPattern pattern : patterns) {
                patternResult = pattern.getPatternString();
            }
            String patternPre = StrUtil.subBetween(patternResult, "/");
            map.put("patternPre",patternPre);
            map.put("pattern",patternResult);
            urlList.add(map);
        }
        return urlList;
    }

    public List<ApiInfoVo> getAPiListBySpringDoc(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> http = new HttpEntity<>("",headers);
        String port = environment.getProperty("server.port", "8080");
        JSONArray apiServerList = getApiServerList();
        List<ApiInfo> apiList = new ArrayList<>();
        for (Object object : apiServerList) {
            JSONObject jsonObject = JSONUtil.parseObj(object);
            String uri = jsonObject.getStr("url");
            String url = "http://127.0.0.1:"+port+uri;
            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, http, String.class);
            String body = exchange.getBody();
            List<ApiInfo> apiInfoBySpringDoc = getApiInfoBySpringDoc(body);
            apiList.addAll(apiInfoBySpringDoc);
        }
        apiInfoService.saveBatch(apiList);
        return ApiInfoMapperCvs.INSTANCE.DbListToVoList(apiList);
    }


    private List<ApiInfo> getApiInfoBySpringDoc(String body){

        JSONObject jsonObject = JSONUtil.parseObj(body);
        JSONObject api = jsonObject.getJSONObject("paths");
        Set<Map.Entry<String, Object>> entries = api.entrySet();
        List<ApiInfo> apiInfoList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : entries) {
            ApiInfo apiInfo = new ApiInfo();
            String key = entry.getKey();
            Object value = entry.getValue();
            JSONObject apiDes = JSONUtil.parseObj(value);
            JSONObject method = apiDes.getJSONObject("post");
            String methodStr = "POST";
            if (method==null){
                methodStr = "GET";
                method = apiDes.getJSONObject("get");
            }
            apiInfo.setName(method.getStr("summary",""));
            apiInfo.setApiMethod(methodStr);
            String tag = method.getJSONArray("tags").get(0).toString();
            apiInfo.setModelName(tag);
            apiInfo.setPattern(key);
            apiInfo.setAppId("1727733449919733761");
            String patternPre = StrUtil.subBetween(key, "/");
            apiInfo.setPatternPre(patternPre);
            apiInfoList.add(apiInfo);
        }
        return apiInfoList;
    }

    private JSONArray getApiServerList(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> http = new HttpEntity<>("",headers);
        String port = environment.getProperty("server.port", "8080");
        String url = "http://127.0.0.1:"+port+"/v3/api-docs/swagger-config";
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, http, String.class);
        String body = exchange.getBody();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        return jsonObject.getJSONArray("urls");
    }
}
