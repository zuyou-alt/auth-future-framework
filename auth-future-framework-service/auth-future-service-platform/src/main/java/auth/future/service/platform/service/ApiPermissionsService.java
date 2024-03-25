package auth.future.service.platform.service;

import auth.future.service.platform.entity.ApiPermissions;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 接口权限表 服务类
 * </p>
 *
 * @author Hzy
 * @since 2023-11-22
 */
public interface ApiPermissionsService extends IService<ApiPermissions> {

    /**
     * 根据接口ID删除全部权限
     * @param apiIds 接口ID
     */
    void removeAllPermissionByApiIds(List<String> apiIds);


    /**
     * 删除指定接口的指定权限
     * @param apiId 接口ID
     * @param roleIds 权限ID集合
     * @return 删除结果
     */
    boolean removePermission(String apiId, List<String> roleIds);

    /**
     * 根据接口ID查询权限
     * @param apiId 接口ID
     * @return 接口权限
     */
    List<ApiPermissions> queryApiPermissionById(String apiId);


}
