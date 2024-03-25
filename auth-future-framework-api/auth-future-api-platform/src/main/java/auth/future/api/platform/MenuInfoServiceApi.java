package auth.future.api.platform;

import auth.future.api.platform.model.menu.MenuInfoVo;

import java.util.List;
import java.util.Map;

public interface MenuInfoServiceApi {

    /**
     * 保存/修改菜单
     * @param menuInfoVo 菜单信息
     * @return 菜单主键
     */
    String saveMenuInfo(MenuInfoVo menuInfoVo);

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
    MenuInfoVo getMenuInfo(String id);

    /**
     * 根据ID集合 获取菜单集合
     * @param ids 主键ID
     * @return 菜单信息
     */
    List<MenuInfoVo> getMenuInfoByIds(List<String> ids);

    /**
     * 根据父级ID 查询子集菜单
     * @param parentId 菜单父级ID
     * @return 菜单子集集合
     */
    List<MenuInfoVo> queryMenuListParentId(String parentId);

    /**
     * 查询菜单树
     * @param parentId 父级ID
     * @return 树形结构的集合
     */
    List<MenuInfoVo> queryMenuInfoTree(String parentId);

    /**
     * 获取所有菜单的树形结构
     * @return 菜单集合
     */
    List<MenuInfoVo> queryMenuAllListTree();

    /**
     * 获取资源分配树
     * 主要是为了适应前端展示所组装数据
     * @param clientId 应用ID
     * @return 资源分配树所需数据
     */
    Map<String, Object> getMenuInfoAllocationTree(String clientId);


}
