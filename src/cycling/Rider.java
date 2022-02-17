package cycling;

import cycling.Team;

public class Rider {

    private static int riderCount = 0;

    private int riderId; // readonly
    private String riderName; // readonly (write once)
    private int riderYearOfBirth; // readonly (write once)
    private Team riderTeam;

    public Rider(Team team, String riderName, int riderYearOfBirth) {
        this.riderId = riderCount++;
        
        this.riderName = riderName;
        this.riderYearOfBirth = riderYearOfBirth;
        this.riderTeam = team;
    }

    public int getRiderId() {
        return riderId;
    }

    public Team getTeam() {
        return riderTeam;
    }

    public String getRiderName() {
        return riderName;
    }

    public int getRiderYearOfBirth() {
        return riderYearOfBirth;
    }

    public String toString() {
        return "Rider[riderId="+riderId+",riderTeam="+riderTeam+",riderName="+riderName+",riderYearOfBirth="+riderYearOfBirth+"]";
    }
}
