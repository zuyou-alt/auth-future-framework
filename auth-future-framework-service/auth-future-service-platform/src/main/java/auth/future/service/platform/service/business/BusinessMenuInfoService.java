package auth.future.service.platform.service.business;

import auth.future.api.platform.MenuInfoServiceApi;
import auth.future.api.platform.model.menu.MenuInfoVo;
import auth.future.service.platform.beanconversion.MenuInfoMapperCvs;
import auth.future.service.platform.entity.MenuInfo;
import auth.future.service.platform.entity.RoleBind;
import auth.future.service.platform.service.MenuInfoService;
import auth.future.service.platform.service.RoleBindService;
import cn.hutool.core.stream.StreamUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hzy
 * @since 2023-12-19
 **/
@Service
public class BusinessMenuInfoService implements MenuInfoServiceApi {

    @Resource
    private MenuInfoService menuInfoService;

    @Resource
    private RoleBindService roleBindService;

    @Override
    public String saveMenuInfo(MenuInfoVo menuInfoVo) {
        MenuInfo menuInfo = MenuInfoMapperCvs.INSTANCE.VoToDb(menuInfoVo);
        String id = menuInfo.getId();
        if (StrUtil.isBlank(id)){
            menuInfo.setId(String.valueOf(IdWorker.getId(menuInfo)));
        }
        menuInfo.setMenuParentId(StrUtil.isBlank(menuInfo.getMenuParentId()) ? "root": menuInfo.getMenuParentId());
        MenuInfo parentMenu = menuInfoService.getMenuInfo(menuInfo.getMenuParentId());
        String path = parentMenu ==null ?  "root."+ id + "." : parentMenu.getPath() + id + ".";
        menuInfo.setPath(path);
        return menuInfoService.saveMenuInfo(menuInfo);
    }

    @Override
    public boolean removeMenuInfo(String id) {
        return menuInfoService.removeMenuInfo(id);
    }

    @Override
    public boolean removeMenuInfos(List<String> ids) {
        return menuInfoService.removeMenuInfos(ids);
    }

    @Override
    public MenuInfoVo getMenuInfo(String id) {
        MenuInfo menuInfo = menuInfoService.getMenuInfo(id);
        MenuInfo parent = menuInfoService.getMenuInfo(menuInfo.getMenuParentId());
        MenuInfoVo menuInfoVo = MenuInfoMapperCvs.INSTANCE.DbToVo(menuInfo);
        if (parent!=null){
            menuInfoVo.setMenuParentName(parent.getMenuTitle());
        }
        return menuInfoVo;
    }

    @Override
    public List<MenuInfoVo> getMenuInfoByIds(List<String> ids) {
        return MenuInfoMapperCvs.INSTANCE.DbListToVoList(menuInfoService.getMenuInfoByIds(ids));
    }

    @Override
    public List<MenuInfoVo> queryMenuListParentId(String parentId) {
        return MenuInfoMapperCvs.INSTANCE.DbListToVoList(menuInfoService.queryMenuListParentId(parentId));
    }

    @Override
    public List<MenuInfoVo> queryMenuInfoTree(String parentId) {
        MenuInfo menuInfo = menuInfoService.getMenuInfo(parentId);
        if (menuInfo==null) return new ArrayList<>();
        List<MenuInfo> menuInfos = menuInfoService.queryMenuListByPath(menuInfo.getPath());
        return getMenuTreeByList(menuInfos);
    }

    @Override
    public List<MenuInfoVo> queryMenuAllListTree() {
        List<MenuInfo> list = menuInfoService.list();
        return getMenuTreeByList(list);
    }
    private List<MenuInfoVo> getMenuTreeByList(List<MenuInfo> menuInfoList){
        List<MenuInfoVo> menuInfoVos = MenuInfoMapperCvs.INSTANCE.DbListToVoList(menuInfoList);
        Map<String, List<MenuInfoVo>> parentGroup = menuInfoVos.stream().collect(Collectors.groupingBy(MenuInfoVo::getMenuParentId, Collectors.toList()));
        return getChild("root", 1, 4, parentGroup);
    }

    @Override
    public Map<String, Object> getMenuInfoAllocationTree(String clientId) {
        return null;
    }

    private List<MenuInfoVo> getChild(String parentId, int depth, int maxDepth,Map<String, List<MenuInfoVo>> parentGroup){
        List<MenuInfoVo> menuInfoVos = parentGroup.get(parentId);
        if (menuInfoVos==null) {
            menuInfoVos = new ArrayList<>();
        }
        List<MenuInfoVo> result = new ArrayList<>();
        for (MenuInfoVo menuInfoVo : menuInfoVos) {
            List<MenuInfoVo> menuInfoVoList = new ArrayList<>();
            if (depth<maxDepth){
                menuInfoVoList = getChild(menuInfoVo.getId(), depth + 1, maxDepth,parentGroup);
            }
            menuInfoVo.setChildren(menuInfoVoList);
            result.add(menuInfoVo);
        }
        return result.stream().sorted(Comparator.comparing(MenuInfoVo::getMenuSort)).toList();
    }

    public Map<String,Object> initMenu(){
        JSONObject jsonObject = JSONUtil.readJSONObject(new File("E:\\code_manager\\auth-future-framework\\doc\\MenuInfo.json"), Charset.defaultCharset());
        JSONArray data = jsonObject.getJSONArray("data");
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setMenuTitle("组织用户中心");
        menuInfo.setMenuType("system");
        String id= String.valueOf(IdWorker.getId(menuInfo));
        menuInfo.setId(id);
        List<MenuInfo> menuInfos = new ArrayList<>();
        menuInfos.add(menuInfo);
        for (Object datum : data) {
            JSONObject menu = JSONUtil.parseObj(datum);
            MenuInfo menuRoot = this.parseMenuInfo(menu, id);

            String rootId= String.valueOf(IdWorker.getId(menuRoot));
            menuRoot.setId(rootId);
            JSONArray children = menu.getJSONArray("children");
            if (children!=null){
                for (Object child : children) {
                    JSONObject child1 = JSONUtil.parseObj(child);
                    MenuInfo menuInfo1 = this.parseMenuInfo(child1, rootId);
                    String me= String.valueOf(IdWorker.getId(menuInfo1));
                    menuInfo1.setId(me);
                    menuInfos.add(menuInfo1);
                    JSONArray children1 = child1.getJSONArray("children");
                    if (children1!=null){
                        for (Object object : children1) {
                            JSONObject child2 = JSONUtil.parseObj(object);
                            MenuInfo m2 = this.parseMenuInfo(child2, me);
                            String as= String.valueOf(IdWorker.getId(m2));
                            m2.setId(as);
                            menuInfos.add(m2);
                        }
                    }
                }
            }
            menuInfos.add(menuRoot);
        }
        menuInfoService.saveBatch(menuInfos);
        List<RoleBind> role = getRole(menuInfos);
        roleBindService.saveOrUpdateBatch(role);
        Map<String,Object> result = new HashMap<>();
        result.put("total",menuInfos.size());
        result.put("data",menuInfos);
        return result;
    }

    private List<RoleBind> getRole(List<MenuInfo> menuInfos){
        List<RoleBind> roleBindList = new ArrayList<>();
        RoleBind roleBind = new RoleBind();
        roleBind.setRoleId("1704808788248338434");
        roleBind.setBindId("1707645228842110978");
        roleBind.setBindType(1);
        roleBindList.add(roleBind);
        for (MenuInfo menuInfo : menuInfos) {
            RoleBind roleBindMenu = new RoleBind();
            roleBindMenu.setRoleId("1704808788248338434");
            roleBindMenu.setBindId(menuInfo.getId());
            roleBindMenu.setBindType(4);
            roleBindList.add(roleBindMenu);
        }
        return roleBindList;

    }

    private MenuInfo parseMenuInfo(JSONObject jsonObject,String parentId){
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setMenuName(jsonObject.getStr("name"));
        menuInfo.setMenuPath(jsonObject.getStr("path"));
        menuInfo.setMenuComponent(jsonObject.getStr("component"));
        menuInfo.setMenuIcon(jsonObject.getJSONObject("meta").getStr("icon"));
        menuInfo.setMenuUrl(jsonObject.getJSONObject("meta").getStr("isLink"));
//        menuInfo.setMenuShow(jsonObject.getJSONObject("meta").getStr("isHide"));
        menuInfo.setMenuStatus("enabled");
        menuInfo.setMenuSort(1);
        menuInfo.setMenuParentId(parentId);
        menuInfo.setMenuIdentity("10000");
        menuInfo.setMenuTitle(jsonObject.getJSONObject("meta").getStr("title"));
        return menuInfo;


    }
}
