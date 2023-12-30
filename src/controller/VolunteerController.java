package controller;

import service.UserService;
import service.impl.IUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author lyq
 * @time 2023/12/30 14:15
 */
public class VolunteerController {

    private static UserService userService = new IUserService();

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
