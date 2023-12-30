package dao;

import entity.Team;
import entity.User;

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
     * @param userName
     * @return
     */
    public User query(String userName);

    /**
     * @param id
     * @return
     */
    public User findById(String id);
}