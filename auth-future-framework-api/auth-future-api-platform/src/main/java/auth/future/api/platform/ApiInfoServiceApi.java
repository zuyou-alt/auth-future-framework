package auth.future.api.platform;

import auth.future.api.platform.model.ApiInfoVo;
import auth.future.api.platform.model.request.RequestApiPage;
import auth.future.component.common.model.PageResult;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
import java.util.Map;

/**
 * 接口管理API
 * @author hzy
 * @since 2023-11-22
 **/

public interface ApiInfoServiceApi {
    /**
     * 保存接口信息
     * @param apiInfoVo 接口详情
     * @return 接口ID
     */
    String saveApiInfo(ApiInfoVo apiInfoVo);

    /**
     * 删除接口
     * @param apiIds 接口ID集合
     * @return 删除结果
     */
    boolean removeApiInfos(List<String> apiIds);

    /**
     * 根据接口ID查询接口详情
     * @param apiId 接口ID
     * @return 接口详情
     */
    ApiInfoVo getApiInfo(String apiId);

    /**
     * 分页查询接口信息
     * @param requestApiPage 查询条件
     * @return 接口列表
     */
    PageResult<ApiInfoVo> pageApiInfo(RequestApiPage requestApiPage);

    /**
     * 添加接口权限
     * @param apiId 接口ID
     * @param roleIds 角色集合
     * @return 添加结果
     */
    boolean updateApiPermission(String apiId,List<String> roleIds);

    /**
     * 删除接口权限
     * @param apiId 接口ID
     * @param roleIds 角色集合
     * @return 删除结果
     */
    boolean removeApiPermission(String apiId,List<String> roleIds);

    /**
     * 根据接口路径查询接口权限
     * @param apiPattern 接口路径
     * @return 接口角色
     */
    List<String> queryApiPermissionByPattern(String apiPattern);

    /**
     * 根据接口ID查询接口权限
     * @param apiId 接口路径
     * @return 接口角色
     */
    List<String> queryApiPermissionById(String apiId);



    /**
     * 查询所有接口的权限
     * @return 接口对应的权限列表
     */
    Map<String,List<String>> queryApiRoles();
}
