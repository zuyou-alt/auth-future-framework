package auth.future.service.platform.service.impl;

import auth.future.service.platform.entity.ApiPermissions;
import auth.future.service.platform.mapper.ApiPermissionsMapper;
import auth.future.service.platform.service.ApiPermissionsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 接口权限表 服务实现类
 * </p>
 *
 * @author Hzy
 * @since 2023-11-22
 */
@Service
public class ApiPermissionsServiceImpl extends ServiceImpl<ApiPermissionsMapper, ApiPermissions> implements ApiPermissionsService {

    @Override
    public void removeAllPermissionByApiIds(List<String> apiIds) {
        LambdaQueryWrapper<ApiPermissions> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ApiPermissions::getApiId,apiIds);
        this.remove(queryWrapper);
    }

    @Override
    public boolean removePermission(String apiId, List<String> roleIds) {
        LambdaQueryWrapper<ApiPermissions> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiPermissions::getApiId,apiId);
        queryWrapper.in(ApiPermissions::getRoleId,roleIds);
        return this.remove(queryWrapper);
    }

    @Override
    public List<ApiPermissions> queryApiPermissionById(String apiId) {
        return this.lambdaQuery().eq(ApiPermissions::getApiId,apiId).list();
    }
}
