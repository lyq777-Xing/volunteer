package service;

import entity.Team;

import java.util.List;

/**
 * @author lyq
 * @time 2023/12/30 17:12
 */
public interface TeamService {
    /**
     * @param id
     * @return
     */
    public String query(int id);

    /**
     * 添加团队
     * @param teamName
     * @return
     */
    public boolean add(String teamName, String teamLeaderId);

    /**
     * 删除团队
     * @param id
     * @return
     */
    public boolean delete(int id);

    /**
     * 更新团队
     * @param id
     * @param teamName
     * @return
     */
    public boolean update(int id, String teamName);

    /**
     * 根据团队名称查询团队
     * @param teamName
     * @return
     */
    public Team findByName(String teamName);

    /**
     * 查询所有团队
     * @return
     */
    public List<Team> findAll();
}
