package service;

import entity.User;

import java.util.List;

/**
 * @author lyq
 * @time 2023/12/30 14:11
 */
public interface UserService {
    /**
     * @param userName
     * @param password
     * @return
     */
    boolean login(String userName, String password);

    /**
     * @param userName
     * @param password
     * @param introduce
     * @return
     */
    boolean register(String userName, String password, String introduce);

    /**
     * @param userName
     * @param password
     * @return
     */
    boolean update(String id, String userName, String password, String role, double volunteerHours, int teamId, String introduce);

    /**
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * @param userName
     * @return
     */
    User query(String userName);

    /**
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 根据用户id查询角色
     * @param roleId
     * @return
     */
    User findRoleIdByUserId(String roleId);

    /**
     * 查询所有志愿者
     * @return
     */
    List<User> findAllVolunteer();

    /**
     * 查询所有管理员
     * @return
     */
    List<User> findAllAdmin();

    /**
     * 根据团队id查询团队成员
     * @param teamId
     * @return
     */
    List<User> findUsersByTeamId(int teamId);

    /**
     * 重置密码
     * @param id
     * @return
     */
    String resetPassword(String id);
}
