package dao.impl;

import dao.RoleDao;
import entity.Role;
import utils.LineUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author lyq
 * @time 2023/12/30 14:36
 */
public class IRoleDao implements RoleDao {

    LineUtils lineUtils = new LineUtils();

    @Override
    public String query(int id) {
        Scanner roles = getFile();
        while (roles.hasNextLine()) {
            String[] role = roles.nextLine().split(",");
            if (role[0].equals(id)) {
                return role[1];
            }
        }
        roles.close();
        return null;
    }

    @Override
    public boolean add(String roleName) {
        Scanner roles = getFile();
        if(findByName(roleName) != null){
            return false;
        }
        while (roles.hasNextLine()){
            String s = roles.nextLine();
            String[] role = s.split(",");
            if (role[1].equals(roleName)) {
                return false;
            }
            try {
                FileWriter fileWriter = new FileWriter("src/data/role.txt");
                String id = lineUtils.getLines("src/data/role.txt") + 1 + "";
                fileWriter.write(id + "," + roleName + "\n");
                fileWriter.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        roles.close();
        return false;
    }

    @Override
    public boolean delete(int id) {
        Scanner roles = getFile();
        while (roles.hasNextLine()) {
            String s = roles.nextLine();
            String[] role = s.split(",");
            if (role[0].equals(id)) {
                try {
                    FileWriter fileWriter = new FileWriter("src/data/role.txt");
                    s = s.replaceAll(s, "");
                    s = s + "\r\n";
                    fileWriter.write(s);
                    fileWriter.close();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        roles.close();
        return false;
    }

    @Override
    public boolean update(int id, String roleName) {
        Scanner roles = getFile();
        if(findByName(roleName) != null){
            return false;
        }
        while (roles.hasNextLine()) {
            String s = roles.nextLine();
            String[] role = s.split(",");
            if (role[0].equals(id)) {
                try {
                    FileWriter fileWriter = new FileWriter("src/data/role.txt");
                    s = s.replaceAll(s,"");
                    s= s+"\r\n";
                    fileWriter.write(s);
                    fileWriter.write(id + "," + roleName + "\n");
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        roles.close();
        return false;
    }

    @Override
    public Role findByName(String roleName) {
        Scanner roles = getFile();
        while (roles.hasNextLine()) {
            String[] role = roles.nextLine().split(",");
            if (role[1].equals(roleName)) {
                return new Role(role[0],role[1]);
            }
        }
        roles.close();
        return null;
    }

    public Scanner getFile(){
        File roleFile = new File("src/data/role.txt");
        Scanner roles;
        {
            try {
                roles = new Scanner(roleFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return roles;
    }
}
