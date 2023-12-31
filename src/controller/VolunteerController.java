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
                                System.out.println("2.修改个人简介");
                                System.out.println("3.查询团队信息");
                                System.out.println("4.加入或退出团队");
                                System.out.println("5.创建团队");
                                System.out.println("6.查询团队成员");
                                System.out.println("7.修改密码");
                                System.out.println("8.退出");
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
                                        // TODO: 2023/12/31 查询团队成员
                                        User query4 = userService.query(userName);
                                        if(query4.getTeamId() == -1){
                                            System.out.println("你还没有加入团队！");
                                        }else {
                                            List<User> all = userService.findUsersByTeamId(query4.getTeamId());
                                            for (User user1 : all) {
                                                if(user1.getTeamId() == query4.getTeamId()){
                                                    System.out.println(user1);
                                                }
                                            }
                                        }
                                    case "7":
                                        // TODO: 2023/12/31 修改密码
                                        System.out.println("请输入新的密码：");
                                        String password2 = bufferedReader.readLine();
                                        if(password2 == null || password2.trim().equals("")){
                                            System.out.println("密码不能为空！");
                                            break;
                                        }
                                        if(password2.length() > 20){
                                            System.out.println("密码长度不能超过20！");
                                            break;
                                        }
                                        if(userService.update(user.getId(), user.getUserName(), password2, user.getRoleId(), user.getVolunteerHours(), user.getTeamId(), user.getIntroduce())){
                                            System.out.println("修改成功！");
                                        }else{
                                            System.out.println("修改失败！");
                                        }
                                    case "8":
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
                                System.out.println("8.修改个人密码");
                                System.out.println("9.重置志愿者密码");
                                System.out.println("10.更新志愿者时长");
                                System.out.println("11.志愿者人数");
                                System.out.println("12.退出");
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
//                                        System.out.println("请输入新的密码：");
//                                        String password1 = bufferedReader.readLine();
                                        System.out.println("请输入新的个人简介：");
                                        String introduce = bufferedReader.readLine();
                                        if(userName1 == null || userName1.trim().equals("")|| introduce == null || introduce.trim().equals("")){
                                            System.out.println("用户名、密码或个人简介不能为空！");
                                            break;
                                        }
                                        if(userName1.length() > 20 || introduce.length() > 200){
                                            System.out.println("用户名、密码或个人简介长度不能超过20、20、200！");
                                            break;
                                        }
                                        if(userService.update(user.getId(), userName1, password, user.getRoleId(), user.getVolunteerHours(), user.getTeamId(), introduce)){
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
                                        List<Team> teamList = teamService.findAll();
                                        for (Team team : teamList) {
                                            System.out.println(team);
                                        }
                                        System.out.println("请输入需要修改的团队的ID：");
                                        String teamId = bufferedReader.readLine();
                                        if(teamId == null || teamId.trim().equals("")) continue;
                                        if(teamService.query(Integer.parseInt(teamId)) == null){
                                            System.out.println("团队不存在！");
                                            break;
                                        }
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
                                        if(teamService.update(Integer.parseInt(teamId), teamName)){
                                            System.out.println("修改成功！");
                                        }else{
                                            System.out.println("修改失败！");
                                        }
                                        break;
                                    case "5":
                                        // TODO: 2023/12/30 查询志愿者信息
                                        List<User> allVolunteer = userService.findAllVolunteer();
                                        for (User user1 : allVolunteer) {
                                            System.out.println(user1);
                                        }
                                        break;
                                    case "6":
                                        // TODO: 2023/12/30 修改志愿者信息
                                        List<User> allVolunteer1 = userService.findAllVolunteer();
                                        for (User user1 : allVolunteer1) {
                                            System.out.println(user1);
                                        }
                                        System.out.println("请输入需要修改的志愿者的ID：");
                                        String userId = bufferedReader.readLine();
                                        if(userId == null || userId.trim().equals("")) continue;
                                        if(userService.findById(userId) == null){
                                            System.out.println("志愿者不存在！");
                                            break;
                                        }
                                        System.out.println("请输入新的用户名：");
                                        String userName2 = bufferedReader.readLine();
                                        System.out.println("请输入新的密码：");
                                        String password2 = bufferedReader.readLine();
                                        System.out.println("请输入新的个人简介：");
                                        String introduce1 = bufferedReader.readLine();
                                        if(userName2 == null || userName2.trim().equals("") || password2 == null || password2.trim().equals("") || introduce1 == null || introduce1.trim().equals("")){
                                            System.out.println("用户名、密码或个人简介不能为空！");
                                            break;
                                        }
                                        if(userName2.length() > 20 || password2.length() > 20 || introduce1.length() > 200){
                                            System.out.println("用户名、密码或个人简介长度不能超过20、20、200！");
                                            break;
                                        }
                                        User user2 = userService.findById(userId);
                                        if(userService.update(userId, userName2, password2, user2.getRoleId(), user2.getVolunteerHours(), user2.getTeamId(), introduce1)){
                                            System.out.println("修改成功！");
                                        }else{
                                            System.out.println("修改失败！");
                                        }
                                        break;
                                    case "7":
                                        // TODO: 2023/12/30 查询管理员信息
                                        List<User> allAdmin = userService.findAllAdmin();
                                        for (User user1 : allAdmin) {
                                            System.out.println(user1);
                                        }
                                        break;
                                    case "8":
                                        // TODO: 2023/12/31 修改个人密码
                                        System.out.println("请输入新的密码：");
                                        String password3 = bufferedReader.readLine();
                                        if(password3 == null || password3.trim().equals("")){
                                            System.out.println("密码不能为空！");
                                            break;
                                        }
                                        if(password3.length() > 20){
                                            System.out.println("密码长度不能超过20！");
                                            break;
                                        }
                                        if(userService.update(user.getId(), user.getUserName(), password3, user.getRoleId(), user.getVolunteerHours(), user.getTeamId(), user.getIntroduce())){
                                            System.out.println("修改成功！");
                                            System.out.println("请重新登录！");
                                            System.exit(0);
                                        }else{
                                            System.out.println("修改失败！");
                                        }
                                        break;
                                    case "9":
                                        // TODO: 2023/12/31 重置志愿者密码
                                        List<User> allVolunteer2 = userService.findAllVolunteer();
                                        for (User user1 : allVolunteer2) {
                                            System.out.println(user1);
                                        }
                                        System.out.println("请输入需要重置密码的志愿者的ID：");
                                        String userId1 = bufferedReader.readLine();
                                        if(userId1 == null || userId1.trim().equals("")) continue;
                                        if(userService.findById(userId1) == null){
                                            System.out.println("志愿者不存在！");
                                            break;
                                        }
                                        String s = userService.resetPassword(userId1);
                                        if(s != null){
                                            System.out.println("重置成功！");
                                            System.out.println("新密码为：" + s);
                                        }else{
                                            System.out.println("重置失败！");
                                        }
                                        break;
                                    case "10":
                                        // TODO: 2023/12/31 更新志愿者时长
                                        List<User> allVolunteer3 = userService.findAllVolunteer();
                                        for (User user1 : allVolunteer3) {
                                            System.out.println(user1);
                                        }
                                        System.out.println("请输入需要更新时长的志愿者的ID：");
                                        String userId2 = bufferedReader.readLine();
                                        if(userId2 == null || userId2.trim().equals("")) continue;
                                        if(userService.findById(userId2) == null){
                                            System.out.println("志愿者不存在！");
                                            break;
                                        }
                                        User user1 = userService.findById(userId2);
                                        System.out.println("请输入活动志愿者的时长(添加为正数，减少为负数)：");
                                        String volunteerHours = bufferedReader.readLine();
                                        if(volunteerHours == null || volunteerHours.trim().equals("")) continue;
                                        if(userService.update(userId2, user1.getUserName(), user1.getPassword(), user1.getRoleId(), user1.getVolunteerHours() + Double.parseDouble(volunteerHours), user1.getTeamId(), user1.getIntroduce())){
                                            System.out.println("更新成功！");
                                        }else{
                                            System.out.println("更新失败！");
                                        }
                                        break;
                                    case "11":
                                        // TODO: 2023/12/31 查询志愿者人数
                                        List<User> allVolunteer4 = userService.findAllVolunteer();
                                        System.out.println("志愿者人数为：" + allVolunteer4.size());
                                        break;
                                    case "12":
                                          System.out.println("退出成功！");
                                        System.exit(0);
                                        break;
                                    default:
                                        System.out.println("输入错误，请重新输入！");
                                        break;
                                }
                            }
                        } else if (roleId.equals("3")) {
                            // TODO: 2023/12/31 待完善

                        } else if (roleId.equals("4")) {
                            while (true) {
                                System.out.println("欢迎进入团队管理系统");
                                System.out.println("1.查询个人信息");
                                System.out.println("2.修改个人信息");
                                System.out.println("3.查询团队信息");
                                System.out.println("4.修改团队信息");
                                System.out.println("5.修改密码");
                                System.out.println("6.退出");
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
                                        if(teamService.update(user.getTeamId(), teamName)){
                                            System.out.println("修改成功！");
                                        }else{
                                            System.out.println("修改失败！");
                                        }
                                        break;
                                    case "5":
                                        // TODO: 2023/12/31 修改密码
                                        System.out.println("请输入新的密码：");
                                        String password2 = bufferedReader.readLine();
                                        if(password2 == null || password2.trim().equals("")){
                                            System.out.println("密码不能为空！");
                                            break;
                                        }
                                        if(password2.length() > 20){
                                            System.out.println("密码长度不能超过20！");
                                            break;
                                        }
                                        if(userService.update(user.getId(), user.getUserName(), password2, user.getRoleId(), user.getVolunteerHours(), user.getTeamId(), user.getIntroduce())){
                                            System.out.println("修改成功！");
                                            System.out.println("请重新登录！");
                                            System.exit(0);
                                        }else{
                                            System.out.println("修改失败！");
                                        }
                                    case "6":
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
