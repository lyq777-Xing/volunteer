package dao;

import entity.Role;

/**
 * @author lyq
 * @time 2023/12/30 14:34
 */
public interface RoleDao {
    /**
     * @param id
     * @return
     */
    public String query(int id);

    /**
     * 添加角色
     * @param roleName
     * @return
     */
    public boolean add(String roleName);

    /**
     * 删除角色
     * @param id
     * @return
     */
    public boolean delete(int id);

    /**
     * 更新角色
     * @param id
     * @param roleName
     * @return
     */
    public boolean update(int id, String roleName);

    /**
     * 根据角色名称查询角色
     * @param roleName
     * @return
     */
    public Role findByName(String roleName);

}
