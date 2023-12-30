package entity;

public class User {
    private String id;
    private String userName;
    private String password;
    private String roleId;
    private double volunteerHours;
    private int teamId;
    private String introduce;


    public User() {
    }

    public User(String id, String userName, String password, String roleId, double volunteerHours, int teamId, String introduce) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roleId = roleId;
        this.volunteerHours = volunteerHours;
        this.teamId = teamId;
        this.introduce = introduce;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public double getVolunteerHours() {
        return volunteerHours;
    }

    public void setVolunteerHours(double volunteerHours) {
        this.volunteerHours = volunteerHours;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roleId='" + roleId + '\'' +
                ", volunteerHours=" + volunteerHours +
                ", teamId=" + teamId +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
