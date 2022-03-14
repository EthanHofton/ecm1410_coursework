package cycling;

import java.io.Serializable;

/**
 * The rider class. Stores rider id and other data relevent to the rider
 * 
 * @author Ethan Hofton
 * @author Jon Tao
 * @version 1.0
 */
public class Rider implements Serializable {

    private static int riderCount = 0;

    private int riderId;
    private String riderName;
    private int riderYearOfBirth;
    private Team riderTeam;

    /**
     * The rider constructor
     * 
     * @param team the team the rider belongs to
     * @param riderName the name of the rider
     * @param riderYearOfBirth the year of bith of the rider
     * @see cycling.Team
     */
    public Rider(Team team, String riderName, int riderYearOfBirth) {
        this.riderId = riderCount++;
        
        this.riderName = riderName;
        this.riderYearOfBirth = riderYearOfBirth;
        this.riderTeam = team;
    }

    /**
     * Getter for {@code this.riderId}
     * 
     * @return the id of the rider
     */
    public int getRiderId() {
        return riderId;
    }

    /**
     * Getter for {@code this.riderTeam}
     * 
     * @return the team of the rider
     * @see cycling.Team
     */
    public Team getTeam() {
        return riderTeam;
    }

    /**
     * Getter for {@code this.riderName}
     * 
     * @return the name of the rider
     */
    public String getRiderName() {
        return riderName;
    }

    /**
     * Getter for {@code this.riderYearOfBirth}
     * 
     * @return the year of birth of the rider
     */
    public int getRiderYearOfBirth() {
        return riderYearOfBirth;
    }

    /**
     * sums the rank points and sprint points for a rider and given stage
     * 
     * @param stage the stage the rider accumlated points for
     * @param rank the rank the rider got
     * @return the total points accumlated for the given stage
     */
    public int getPointsInStage(Stage stage, int rank) {
        int points = 0;

        points += stage.pointsForRank(rank);
        points += stage.pointsForIntermediateSprints(this);

        return points;
    }

    /**
     * returns the mountain points for that rider in the given stage
     * 
     * @param stage the stage the rider accumlated points for
     * @return the total points accumlated for the given stage
     */
    public int getMountainPointsInStage(Stage stage) {
        return stage.pointsForMountainClassification(this);
    }

    /**
     * Rest the static counter to set the ids
     */
    public static void resetCounter() {
        riderCount = 0;
    }

    /**
     * To string method for the rider
     * 
     * @return formatted string with rider information
     */
    public String toString() {
        return "Rider[riderId="+riderId+",riderTeam="+riderTeam+",riderName="+riderName+",riderYearOfBirth="+riderYearOfBirth+"]";
    }
}
