package service.impl;

import dao.TeamDao;
import dao.impl.ITeamDao;
import entity.Team;
import service.TeamService;

import java.util.List;

/**
 * @author lyq
 * @time 2023/12/30 17:13
 */
public class ITeamService implements TeamService {
    TeamDao teamDao = new ITeamDao();

    @Override
    public String query(int id) {
        return teamDao.query(id);
    }

    @Override
    public boolean add(String teamName, String teamLeaderId) {
        return teamDao.add(teamName, teamLeaderId);
    }

    @Override
    public boolean delete(int id) {
        return teamDao.delete(id);
    }

    @Override
    public boolean update(int id, String teamName) {
        return teamDao.update(id, teamName);
    }

    @Override
    public Team findByName(String teamName) {
        return teamDao.findByName(teamName);
    }

    @Override
    public List<Team> findAll() {
        return teamDao.findAll();
    }
}
