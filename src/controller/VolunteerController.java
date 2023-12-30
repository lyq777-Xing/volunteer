package controller;

import entity.Team;
import entity.User;
import service.RoleService;
import service.TeamService;
import service.UserService;
import service.impl.IRoleService;
import service.impl.ITeamService;
import service.impl.IUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author lyq
 * @time 2023/12/30 14:15
 */
public class VolunteerController {

    private static UserService userService = new IUserService();
    private static RoleService roleService = new IRoleService();
    private static TeamService teamService = new ITeamService();
    public static void main(String[] args) throws IOException {
        while (true){
            mainMenu();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String cmd = bufferedReader.readLine();
            if(cmd == null || cmd.trim().equals("")) continue;
            switch (cmd){
                case "1":
                    System.out.println("请输入用户名：");
                    String userName = bufferedReader.readLine();
                    System.out.println("请输入密码：");
                    String password = bufferedReader.readLine();
                    if(userName == null || userName.trim().equals("") || password == null || password.trim().equals("")){
                        System.out.println("用户名或密码不能为空！");
                        break;
                    }
                    if(userName.length() > 20 || password.length() > 20){
                        System.out.println("用户名或密码长度不能超过20！");
                        break;
                    }
                    if(userService.login(userName, password)){
                        System.out.println("登录成功！");
//                        判断用户的角色
                        User user = userService.query(userName);
                        String roleId = user.getRoleId();
                        if(roleId.equals("1")) {
                            while (true) {
                                System.out.println("欢迎进入志愿者管理系统");
                                System.out.println("1.查询个人信息");
                                System.out.println("2.修改个人信息");
                                System.out.println("3.查询团队信息");
                                System.out.println("4.加入或退出团队");
                                System.out.println("5.创建团队");
                                System.out.println("6.退出");
                                System.out.println("请输入你的选择：");
                                String cmd1 = bufferedReader.readLine();
                                if (cmd1 == null || cmd1.trim().equals("")) continue;
                                switch (cmd1) {
                                    case "1":
                                        System.out.println("个人信息如下：");
                                        User query = userService.query(userName);
                                        System.out.println(query);
                                        break;
                                    case "2":
                                        String userName1 = userName;
                                        String password1 = password;
                                        System.out.println("请输入新的个人简介：");
                                        String introduce = bufferedReader.readLine();
                                        if (introduce == null || introduce.trim().equals("")) {
                                            System.out.println("个人简介不能为空！");
                                            break;
                                        }
                                        if (userService.update(user.getId(), userName1, password1, user.getRoleId(), user.getVolunteerHours(), user.getTeamId(), introduce)) {
                                            System.out.println("修改成功！");
                                        } else {
                                            System.out.println("修改失败！");
                                        }
                                        break;
                                    case "3":
                                        // TODO: 2023/12/30 查询团队信息
                                        User query1 = userService.query(userName);
                                        if(query1.getTeamId() == -1){
                                            System.out.println("你还没有加入团队！");
                                        }else {
                                            String query2 = teamService.query(query1.getTeamId());
                                            System.out.println(query2);
                                        }
                                        break;
                                    case "4":
                                        // TODO: 2023/12/30 加入或退出团队
                                        User query3 = userService.query(userName);
                                        if(query3.getTeamId() == -1){
                                            System.out.println("请选择需要加入的团队：");
                                            List<Team> all = teamService.findAll();
                                            for (Team team : all) {
                                                System.out.println(team);
                                            }
                                            System.out.println("请输入需要加入的团队的ID");
                                            String teamId = bufferedReader.readLine();
                                            if(teamId == null || teamId.trim().equals("")) continue;
                                            if(userService.update(user.getId(), user.getUserName(), user.getPassword(), user.getRoleId(), user.getVolunteerHours(), Integer.parseInt(teamId), user.getIntroduce())){
                                                System.out.println("加入成功！");
                                            }else{
                                                System.out.println("加入失败！");
                                            }
                                        }else {
                                            System.out.println("你已经加入了团队，是否退出？");
                                            System.out.println("1.是");
                                            System.out.println("2.否");
                                            String cmd4 = bufferedReader.readLine();
                                            if(cmd4 == null || cmd4.trim().equals("")) continue;
                                            switch (cmd4){
                                                case "1":
                                                    if(userService.update(user.getId(), user.getUserName(), user.getPassword(), user.getRoleId(), user.getVolunteerHours(), -1, user.getIntroduce())){
                                                        System.out.println("退出成功！");
                                                    }else{
                                                        System.out.println("退出失败！");
                                                    }
                                                    break;
                                                case "2":
                                                    break;
                                                default:
                                                    System.out.println("输入错误，请重新输入！");
                                                    break;
                                            }
                                        }
                                        break;
                                    case "5":
                                        // TODO: 2023/12/30 创建团队
                                        System.out.println("请输入团队名称：");
                                        String teamName = bufferedReader.readLine();
                                        if(teamName == null || teamName.trim().equals("")){
                                            System.out.println("团队名称不能为空！");
                                            break;
                                        }
                                        if(teamName.length() > 20){
                                            System.out.println("团队名称长度不能超过20！");
                                            break;
                                        }
                                        if(teamService.add(teamName, user.getId())){
                                            Team team = teamService.findByName(teamName);
                                            if(userService.update(user.getId(), user.getUserName(), user.getPassword(), user.getRoleId(), user.getVolunteerHours(), team.getId(), user.getIntroduce())){
                                                System.out.println("创建成功！");
                                            }else{
                                                System.out.println("创建失败！");
                                            }
                                        }else{
                                            System.out.println("创建失败！");
                                        }
                                        break;
                                    case "6":
                                        System.out.println("退出成功！");
                                        System.exit(0);
                                        break;
                                    default:
                                        System.out.println("输入错误，请重新输入！");
                                        break;
                                }
                            }
                        } else if (roleId.equals("2")) {
                            while (true) {
                                System.out.println("欢迎进入管理员管理系统");
                                System.out.println("1.查询个人信息");
                                System.out.println("2.修改个人信息");
                                System.out.println("3.查询团队信息");
                                System.out.println("4.修改团队信息");
                                System.out.println("5.查询志愿者信息");
                                System.out.println("6.修改志愿者信息");
                                System.out.println("7.查询管理员信息");
                                System.out.println("8.退出");
                                System.out.println("请输入你的选择：");
                                String cmd3 = bufferedReader.readLine();
                                if(cmd3 == null || cmd3.trim().equals("")) continue;
                                switch (cmd3){
                                    case "1":
                                        System.out.println("个人信息如下：");
                                        User query = userService.query(userName);
                                        System.out.println(query);
                                        break;
                                    case "2":
                                        System.out.println("请输入新的用户名：");
                                        String userName1 = bufferedReader.readLine();
                                        System.out.println("请输入新的密码：");
                                        String password1 = bufferedReader.readLine();
                                        System.out.println("请输入新的个人简介：");
                                        String introduce = bufferedReader.readLine();
                                        if(userName1 == null || userName1.trim().equals("") || password1 == null || password1.trim().equals("") || introduce == null || introduce.trim().equals("")){
                                            System.out.println("用户名、密码或个人简介不能为空！");
                                            break;
                                        }
                                        if(userName1.length() > 20 || password1.length() > 20 || introduce.length() > 200){
                                            System.out.println("用户名、密码或个人简介长度不能超过20、20、200！");
                                            break;
                                        }
                                        if(userService.update(user.getId(), userName1, password1, user.getRoleId(), user.getVolunteerHours(), user.getTeamId(), introduce)){
                                            System.out.println("修改成功！");
                                        }else{
                                            System.out.println("修改失败！");
                                        }
                                        break;
                                    case "3":
                                        // TODO: 2023/12/30 查询团队信息
                                        List<Team> all = teamService.findAll();
                                        for (Team team : all) {
                                            System.out.println(team);
                                        }
                                        break;
                                    case "4":
                                        // TODO: 2023/12/30 修改团队信息
                                        break;
                                    case "5":
                                        // TODO: 2023/12/30 查询志愿者信息
                                        break;
                                    case "6":
                                        // TODO: 2023/12/30 修改志愿者信息
                                        break;
                                    case "7":
                                        // TODO: 2023/12/30 查询管理员信息
                                        break;
                                    case "8":
                                        System.out.println("退出成功！");
                                        System.exit(0);
                                        break;
                                    default:
                                        System.out.println("输入错误，请重新输入！");
                                        break;
                                }
                            }
                        } else if (roleId.equals("3")) {

                        } else if (roleId.equals("4")) {
                            while (true) {
                                System.out.println("欢迎进入团队管理系统");
                                System.out.println("1.查询个人信息");
                                System.out.println("2.修改个人信息");
                                System.out.println("3.查询团队信息");
                                System.out.println("4.修改团队信息");
                                System.out.println("5.退出");
                                System.out.println("请输入你的选择：");
                                String cmd2 = bufferedReader.readLine();
                                if(cmd2 == null || cmd2.trim().equals("")) continue;
                                switch (cmd2){
                                    case "1":
                                        System.out.println("个人信息如下：");
                                        User query = userService.query(userName);
                                        System.out.println(query);
                                        break;
                                    case "2":
                                        String userName1 = userName;
                                        String password1 = password;
                                        System.out.println("请输入新的个人简介：");
                                        String introduce = bufferedReader.readLine();
                                        if(introduce == null || introduce.trim().equals("")){
                                            System.out.println("个人简介不能为空！");
                                            break;
                                        }
                                        if(userService.update(user.getId(), userName1, password1, user.getRoleId(), user.getVolunteerHours(), user.getTeamId(), introduce)){
                                            System.out.println("修改成功！");
                                        }else{
                                            System.out.println("修改失败！");
                                        }
                                        break;
                                    case "3":
                                        System.out.println("团队信息如下：");
                                        break;
                                    case "4":
                                        System.out.println("请输入新的团队名称：");
                                        String teamName = bufferedReader.readLine();
                                        if(teamName == null || teamName.trim().equals("")){
                                            System.out.println("团队名称不能为空！");
                                            break;
                                        }
                                        if(teamName.length() > 20){
                                            System.out.println("团队名称长度不能超过20！");
                                            break;
                                        }
                                        break;
                                    case "5":
                                        System.out.println("退出成功！");
                                        System.exit(0);
                                        break;
                                    default:
                                        System.out.println("输入错误，请重新输入！");
                                        break;
                                }
                            }
                        }else {
                            System.out.println("角色不存在！");
                        }
                    }else{
                        System.out.println("登录失败！");
                    }
                    break;
                case "2":
                    System.out.println("请输入用户名：");
                    String userName1 = bufferedReader.readLine();
                    System.out.println("请输入密码：");
                    String password1 = bufferedReader.readLine();
                    System.out.println("请输入个人简介：");
                    String introduce = bufferedReader.readLine();
                    if(userName1 == null || userName1.trim().equals("") || password1 == null || password1.trim().equals("") || introduce == null || introduce.trim().equals("")){
                        System.out.println("用户名、密码或个人简介不能为空！");
                        break;
                    }
                    if(userName1.length() > 20 || password1.length() > 20 || introduce.length() > 200){
                        System.out.println("用户名、密码或个人简介长度不能超过20、20、200！");
                        break;
                    }
                    if(userService.register(userName1, password1, introduce)){
                        System.out.println("注册成功！");
                    }else{
                        System.out.println("注册失败！");
                    }
                    break;
                case "3":
                    System.out.println("退出成功！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误，请重新输入！");
                    break;
            }
        }
    }

    public static void mainMenu(){
        System.out.println("欢迎进入志愿者管理系统");
        System.out.println("1.登录");
        System.out.println("2.注册");
        System.out.println("3.退出");
        System.out.println("请输入你的选择：");
    }
}
