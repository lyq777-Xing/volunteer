package service.impl;

import dao.UserDao;
import dao.impl.IUserDao;
import entity.User;
import service.UserService;

/**
 * @author lyq
 * @time 2023/12/30 14:12
 */
public class IUserService implements UserService {

    UserDao userDao = new IUserDao();

    @Override
    public boolean login(String userName, String password) {
        return userDao.login(userName, password);
    }

    @Override
    public boolean register(String userName, String password, String introduce) {
        return userDao.register(userName, password, introduce);
    }

    @Override
    public boolean update(String id, String userName, String password, String role, double volunteerHours, int teamId, String introduce) {
        return userDao.update(id, userName, password, role, volunteerHours, teamId, introduce);
    }

    @Override
    public boolean delete(String id) {
        return userDao.delete(id);
    }

    @Override
    public User query(String userName) {
        return userDao.query(userName);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }
}
