package dao.impl;

import dao.TeamDao;
import entity.Team;
import utils.LineUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author lyq
 * @time 2023/12/30 15:50
 */
public class ITeamDao implements TeamDao {

    LineUtils lineUtils = new LineUtils();

    @Override
    public String query(int id) {
        Scanner teams = getFile();
        while (teams.hasNextLine()) {
            String[] team = teams.nextLine().split(",");
            if (team[0].equals(id)) {
                return team[1];
            }
        }
        teams.close();
        return null;
    }

    @Override
    public boolean add(String teamName, String teamLeaderId) {
        Scanner teams = getFile();
        if(findByName(teamName) != null){
            return false;
        }
        while (teams.hasNextLine()){
            String s = teams.nextLine();
            String[] team = s.split(",");
            if (team[1].equals(teamName)) {
                return false;
            }
            try {
                String id = lineUtils.getLines("src/data/team.txt") + 1 + "";
                FileWriter fileWriter = new FileWriter("src/data/team.txt", true);
                fileWriter.write(id + "," + teamName + "," + teamLeaderId + "\n");
                fileWriter.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        teams.close();
        return false;
    }

    @Override
    public boolean delete(int id) {
        Scanner teams = getFile();
        int index = 0;
        while (teams.hasNextLine()) {
            String s = teams.nextLine();
            String[] team = s.split(",");
            if (team[0].equals(id)) {
                break;
            }
            index++;
        }
        try {
            lineUtils.removeLineInFile("src/data/team.txt",index);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        teams.close();
        return false;
    }

    @Override
    public boolean update(int id, String teamName) {
        Scanner teams = getFile();
        if(findByName(teamName) != null){
            return false;
        }
        int index = 0;
        while (teams.hasNextLine()) {
            String s = teams.nextLine();
            String[] team = s.split(",");
            if (team[0].equals(id)) {
                break;
            }
            index++;
        }
        teams.close();
        try {
            lineUtils.removeLineInFile("src/data/team.txt",index);
            FileWriter fileWriter = new FileWriter("src/data/team.txt");
            fileWriter.write(id + "," + teamName + "\n");
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Team findByName(String teamName) {
        Scanner teams = getFile();
        while (teams.hasNextLine()) {
            String[] team = teams.nextLine().split(",");
            if (team[1].equals(teamName)) {
                return new Team(Integer.parseInt(team[0]), team[1], team[2]);
            }
        }
        teams.close();
        return null;
    }

    @Override
    public List<Team> findAll() {
        ArrayList<Team> teams = new ArrayList<>();
        Scanner file = getFile();
        while (file.hasNextLine()) {
            String[] team = file.nextLine().split(",");
            teams.add(new Team(Integer.parseInt(team[0]), team[1], team[2]));
        }
        return teams;
    }

    public Scanner getFile(){
        File teamFile = new File("src/data/Team.txt");
        Scanner teams;
        {
            try {
                teams = new Scanner(teamFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return teams;
    }
}
