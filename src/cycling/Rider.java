package cycling;

public class Rider {

    private static int riderCount = 0;

    private int riderId; // readonly
    private int teamId; // readonly (write once)
    private String riderName; // readonly (write once)
    private int riderYearOfBirth; // readonly (write once)

    public Rider(int teamId, String riderName, int riderYearOfBirth) {
        this.riderId = riderCount++;

        this.teamId = teamId;
        this.riderName = riderName;
        this.riderYearOfBirth = riderYearOfBirth;
    }

    public int getRiderId() {
        return riderId;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getRiderName() {
        return riderName;
    }

    public int getRiderYearOfBirth() {
        return riderYearOfBirth;
    }

    public String toString() {
        return "Rider[riderId="+riderId+",teamId="+teamId+",riderName="+riderName+",riderYearOfBirth="+riderYearOfBirth+"]";
    }
}
