package auth.future.service.platform.service.impl;

import auth.future.component.redis.RedisUtil;
import auth.future.service.platform.constant.MenuInfoRedisKey;
import auth.future.service.platform.entity.MenuInfo;
import auth.future.service.platform.mapper.MenuInfoMapper;
import auth.future.service.platform.service.MenuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单信息表数据接口实现
 * @author Hzy
 * @since 2023-12-19
 */
@Service
public class MenuInfoServiceImpl extends ServiceImpl<MenuInfoMapper, MenuInfo> implements MenuInfoService {

    @Transactional
    @Override
    @CacheEvict(cacheNames = MenuInfoRedisKey.MENU_INFO,key = "#menuInfo.id")
    public String saveMenuInfo(MenuInfo menuInfo) {
        this.saveOrUpdate(menuInfo);
        return menuInfo.getId();
    }

    @Override
    @CacheEvict(cacheNames = MenuInfoRedisKey.MENU_INFO,key = "#id")
    public boolean removeMenuInfo(String id) {
        return this.removeById(id);
    }

    @Transactional
    @Override
    public boolean removeMenuInfos(List<String> ids) {
        this.deleteBatchCache(ids);
        return this.removeByIds(ids);
    }

    @Override
    public MenuInfo getMenuInfo(String id) {
        return this.getById(id);
    }

    @Override
    public List<MenuInfo> getMenuInfoByIds(List<String> ids) {
        return this.listByIds(ids);
    }

    @Override
    public List<MenuInfo> queryMenuListParentId(String parentId) {
        return this.lambdaQuery().eq(MenuInfo::getMenuParentId,parentId).list();
    }

    @Override
    public List<MenuInfo> queryMenuListByPath(String path) {
        return this.lambdaQuery().likeRight(MenuInfo::getPath,path).list();
    }

    @Override
    public List<MenuInfo> querySaMenuInfoTree(String parentId) {
        return null;
    }

    @Override
    public Map<String, Object> getMenuInfoAllocationTree(String clientId) {
        return null;
    }

    @Override
    public List<MenuInfo> queryMenuListByIdentity(String userIdentity) {
        return this.lambdaQuery().like(MenuInfo::getMenuIdentity,userIdentity).list();
    }

    private void deleteBatchCache(List<String> ids){
        if (ids==null || ids.isEmpty()) return;
        List<String> keys = new ArrayList<>();
        for (String id : ids) {
            keys.add(MenuInfoRedisKey.MENU_INFO+id);
        }
        RedisUtil.delKeys(keys);
    }
}
