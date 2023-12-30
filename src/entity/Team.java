package entity;

/**
 * @author lyq
 * @time 2023/12/30 13:32
 */
public class Team {
    private int id;
    private String teamName;
    private String teamLeader;

    public Team() {
    }

    public Team(int id, String teamName, String teamLeader) {
        this.id = id;
        this.teamName = teamName;
        this.teamLeader = teamLeader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", teamLeader='" + teamLeader + '\'' +
                '}';
    }
}
