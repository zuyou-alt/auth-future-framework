package auth.future.service.platform.service;

import auth.future.component.common.model.PageResult;
import auth.future.service.platform.entity.ApplicationInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.api.platform.model.applicationinfo.ApplicationQueryListVo;

import java.util.List;

/**
 * 认证客户信息 服务类
 * @author hzy
 * @since 2022-09-28
 */
public interface ApplicationInfoService extends IService<ApplicationInfo> {

       /**
        * 保存/修改 应用
        * @param applicationInfo 应用信息
        * @return 应用ID
        */
       String saveApplication(ApplicationInfo applicationInfo);

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
       ApplicationInfo getApplicationInfo(String appId);

       /**
        * 根据ID获取应用信息
        * @param appId 应用ID
        * @return 应用信息
        */
       ApplicationInfo getApplicationInfo(String appId,Integer status);

       /**
        * 根据应用key获取应用信息
        * @param appKey 应用key
        * @return 应用信息
        */
       ApplicationInfo getApplicationInfoByKey(String appKey);

       /**
        * 根据应用key获取应用信息
        * @param appKey 应用key
        * @return 应用信息
        */
       ApplicationInfo getApplicationInfoByKey(String appKey,Integer status);

       /**
        * 根据条件查询应用列表
        * @return 应用集合
        */
       List<ApplicationInfo> queryApplicationList(ApplicationQueryListVo applicationQueryListVo);

       /**
        * 根据条件分页查询应用列表
        * @return 应用集合
        */
       IPage<ApplicationInfo> pageApplicationList(ApplicationQueryListVo applicationQueryListVo);

       /**
        * 根据类型查询应用列表
        * @param type 应用类型 1 外部应用 0内部应用
        * @return 应用集合
        */
       List<ApplicationInfo> queryApplicationListByType(Integer type);
}
