package auth.future.service.platform.service;

import auth.future.service.platform.entity.MenuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 菜单信息管理数据接口
 * @author Hzy
 * @since 2023-12-19
 */
public interface MenuInfoService extends IService<MenuInfo> {

    /**
     * 保存/修改菜单
     * @param menuInfo 菜单信息
     * @return 菜单主键
     */
    String saveMenuInfo(MenuInfo menuInfo);

    /**
     * 根据ID 删除菜单资源
     * @param id 菜单主键
     * @return 删除结果
     */
    boolean removeMenuInfo(String id);

    /**
     * 批量 删除菜单资源
     * @param ids 菜单ID集合
     * @return 删除结果
     */
    boolean removeMenuInfos(List<String> ids);

    /**
     * 根据ID获取菜单详情
     * @param id 主键ID
     * @return 菜单信息
     */
    MenuInfo getMenuInfo(String id);

    /**
     * 根据ID集合 获取菜单集合
     * @param ids 主键ID
     * @return 菜单信息
     */
    List<MenuInfo> getMenuInfoByIds(List<String> ids);

    /**
     * 根据父级ID 查询子集菜单
     * @param parentId 菜单父级ID
     * @return 菜单子集集合
     */
    List<MenuInfo> queryMenuListParentId(String parentId);

    /**
     * 根据路径查询路径下所有的菜单
     * @param path 菜单路径
     * @return 菜单集合
     */
    List<MenuInfo> queryMenuListByPath(String path);

    /**
     * 查询菜单树
     * @param parentId 父级ID
     * @return 树形结构的集合
     */
    List<MenuInfo> querySaMenuInfoTree(String parentId);

    /**
     * 获取资源分配树
     * 主要是为了适应前端展示所组装数据
     * @param clientId 应用ID
     * @return 资源分配树所需数据
     */
    Map<String, Object> getMenuInfoAllocationTree(String clientId);

    /**
     * 根据用户身份获取该用户所拥有的菜单（主要用于管理员的直接指定）
     * @param userIdentity 用户身份
     * @return 菜单集合
     */
    List<MenuInfo> queryMenuListByIdentity(String userIdentity);

}
