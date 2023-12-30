package dao.impl;

import dao.UserDao;
import entity.User;
import utils.LineUtils;
import utils.UuidUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * @author lyq
 * @time 2023/12/30 13:38
 */
public class IUserDao implements UserDao {
    UuidUtils uuidUtils = new UuidUtils();

    LineUtils lineUtils = new LineUtils();

    @Override
    public boolean login(String userName, String password) {
        Scanner users = getFile();
        while (users.hasNextLine()) {
            String[] user = users.nextLine().split(",");
            if (user[1].equals(userName) && user[2].equals(password)) {
                return true;
            }
        }
        users.close();
        return false;
    }

    @Override
    public boolean register(String userName, String password, String introduce) {
        Scanner users = getFile();
        if(query(userName) != null){
            return false;
        }
        while (users.hasNextLine()) {
            String s = users.nextLine();
            String[] user = s.split(",");
            if (user[1].equals(userName)) {
                return false;
            }
            try {
                FileWriter fileWriter = new FileWriter("src/data/user.txt", true);
                String id = uuidUtils.RandomNumber();
                fileWriter.write(id+ "," + userName + "," + password + "," + "1" + "," + "0.0" + "," + "-1" + "," + introduce + "\n");
                fileWriter.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                users.close();
            }
        }
        return false;
    }

    @Override
    public boolean update(String id,String userName, String password, String role, double volunteerHours, int teamId, String introduce) {
        Scanner users = getFile();
        int index = 0;
        while (users.hasNextLine()) {
            String s = users.nextLine();
            String[] user = s.split(",");
            if (user[0].equals(id)) {
                break;
            }
            index++;
        }
        users.close();
        try {
            lineUtils.removeLineInFile("src/data/user.txt",index);
            FileWriter fileWriter = new FileWriter("src/data/user.txt", true);
            fileWriter.write(id + "," + userName + "," + password + "," + role + "," + volunteerHours + "," + teamId + "," + introduce + "\n");
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        Scanner users = getFile();
        int index = 0;
        while (users.hasNextLine()) {
            String s = users.nextLine();
            String[] user = s.split(",");
            if (user[0].equals(id)) {
                break;
            }
            index++;
        }
        users.close();
        try{
            lineUtils.removeLineInFile("src/data/user.txt",index);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User query(String userName) {
        Scanner users = getFile();
        while (users.hasNextLine()) {
            String[] user = users.nextLine().split(",");
            if (user[1].equals(userName)) {
                return new User(user[0], user[1], user[2], user[3], Double.parseDouble(user[4]), Integer.parseInt(user[5]), user[6]);
            }
        }
        users.close();
        return null;
    }

    @Override
    public User findById(String id) {
        Scanner users = getFile();
        while (users.hasNextLine()) {
            String[] user = users.nextLine().split(",");
            if (user[0].equals(id)) {
                return new User(user[0], user[1], user[2], user[3], Double.parseDouble(user[4]), Integer.parseInt(user[5]), user[6]);
            }
        }
        users.close();
        return null;
    }

    @Override
    public User findRoleIdByUserId(String roleId) {
        Scanner users = getFile();
        while (users.hasNextLine()) {
            String[] user = users.nextLine().split(",");
            if (user[3].equals(roleId)) {
                return new User(user[0], user[1], user[2], user[3], Double.parseDouble(user[4]), Integer.parseInt(user[5]), user[6]);
            }
        }
        users.close();
        return null;
    }

    public Scanner getFile(){
        File userFile = new File("src/data/User.txt");
        Scanner users;
        {
            try {
                users = new Scanner(userFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return users;
    }

}
