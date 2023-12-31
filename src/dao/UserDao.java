package dao;

import entity.Team;
import entity.User;

import java.util.List;

/**
 * @author lyq
 * @time 2023/12/30 13:36
 */
public interface UserDao {
/**
     * @param userName
     * @param password
     * @return
     */
    public boolean login(String userName, String password);

    /**
     * @param userName
     * @param password
     * @param introduce
     * @return
     */
    public boolean register(String userName, String password, String introduce);

    /**
     * @param userName
     * @param password
     * @return
     */
    public boolean update(String id, String userName, String password, String role, double volunteerHours, int teamId, String introduce);

    /**
     * @param id
     * @return
     */
    public boolean delete(String id);

    /**
     * 根据用户名查询信息
     * @param userName
     * @return
     */
    public User query(String userName);

    /**
     * @param id
     * @return
     */
    public User findById(String id);

    /**
     * 根据用户id查询角色
     * @param roleId
     * @return
     */
    public User findRoleIdByUserId(String roleId);

    /**
     * 查询所有志愿者
     * @return
     */
    public List<User> findAllVolunteer();


    /**
     * 查询所有管理员信息
     * @return
     */
    public List<User> findAllAdmin();

    /**
     * 根据团队id查询团队成员
     * @param teamId
     * @return
     */
    public List<User> findUsersByTeamId(int teamId);

    /**
     * 重置密码
     * @param userId
     * @return
     */
    public String resetPassword(String userId);

}
