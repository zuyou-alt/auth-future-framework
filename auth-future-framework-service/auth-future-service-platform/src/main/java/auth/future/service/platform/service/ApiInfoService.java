package auth.future.service.platform.service;

import auth.future.api.platform.model.request.RequestApiPage;
import auth.future.service.platform.entity.ApiInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 接口信息表 服务类
 * </p>
 *
 * @author Hzy
 * @since 2023-11-22
 */
public interface ApiInfoService extends IService<ApiInfo> {
    /**
     * 根据接口路径查询接口
     * @param pattern 接口路径
     * @return 接口信息
     */
    ApiInfo getApiInfoByPattern(String pattern);

    /**
     * 查询所有的接口权限
     * @return 接口权限集合
     */
    List<Map<String,String>> queryApiRoles();

    /**
     * 根据条件分页查询接口
     * @param requestApiPage 查询条件
     * @return 接口分页信息
     */
    IPage<ApiInfo> pageApiInfo(RequestApiPage requestApiPage);

}
