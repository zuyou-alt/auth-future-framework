package auth.future.api.platform;

import auth.future.api.platform.model.applicationinfo.ApplicationInfoVo;
import auth.future.api.platform.model.applicationinfo.ApplicationQueryListVo;
import auth.future.component.common.model.PageResult;
import java.util.List;
import java.util.Set;

/**
 * @author hzy
 * @since 2023-08-17
 **/
public interface ApplicationInfoServiceApi {
    /**
     * 保存/修改 应用
     * @param applicationInfo 应用信息
     * @return 应用ID
     */
    String saveApplication(ApplicationInfoVo applicationInfo);

    /**
     * 根据ID删除应用
     * @param appId 应用ID
     * @return 删除状态
     */
    boolean removeApplication(String appId);

    /**
     * 根据ID获取应用信息
     * @param appId 应用ID
     * @return 应用信息
     */
    ApplicationInfoVo getApplicationInfo(String appId);

    /**
     * 根据ID获取应用信息
     * @param appId 应用ID
     * @return 应用信息
     */
    ApplicationInfoVo getApplicationInfo(String appId,Integer status);

    /**
     * 根据应用key获取应用信息
     * @param appKey 应用key
     * @return 应用信息
     */
    ApplicationInfoVo getApplicationInfoByKey(String appKey);

    /**
     * 根据应用key获取应用信息
     * @param appKey 应用key
     * @return 应用信息
     */
    ApplicationInfoVo getApplicationInfoByKey(String appKey,Integer status);

    /**
     * 根据条件查询应用列表
     * @return 应用集合
     */
    List<ApplicationInfoVo> queryApplicationList(ApplicationQueryListVo applicationQueryListVo);

    /**
     * 根据条件分页查询应用列表
     * @return 应用集合
     */
    PageResult<ApplicationInfoVo> pageApplicationList(ApplicationQueryListVo applicationQueryListVo);

    /**
     * 根据类型查询应用列表
     * @param type 应用类型 1 外部应用 0内部应用
     * @return 应用集合
     */
    List<ApplicationInfoVo> queryApplicationListByType(Integer type);

    /**
     * 根据ID集合查询应用列表
     * @param ids ID集合
     * @return 应用列表
     */
    List<ApplicationInfoVo> getApplicationListByIds(Set<String> ids);
}
