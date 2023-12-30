package service.impl;

import dao.RoleDao;
import dao.UserDao;
import dao.impl.IRoleDao;
import dao.impl.IUserDao;
import entity.Role;
import service.RoleService;

/**
 * @author lyq
 * @time 2023/12/30 15:03
 */
public class IRoleService implements RoleService {
    RoleDao roleDao = new IRoleDao();
    @Override
    public String query(int id) {
        return roleDao.query(id);
    }

    @Override
    public boolean add(String roleName) {
        return roleDao.add(roleName);
    }

    @Override
    public boolean delete(int id) {
        return roleDao.delete(id);
    }

    @Override
    public boolean update(int id, String roleName) {
        return roleDao.update(id, roleName);
    }

    @Override
    public Role findByName(String roleName) {
        return roleDao.findByName(roleName);
    }
}
