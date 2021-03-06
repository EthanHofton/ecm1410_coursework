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
        // set rider id and increment rider count
        this.riderId = riderCount++;
        
        // set rider class attributes
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
        // initalize the points
        int points = 0;

        // add the rank points
        points += stage.pointsForRank(rank);

        // add the intermidiate sprint points
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
        // return the mountain points for this rider
        return stage.pointsForMountainClassification(this);
    }

    /**
     * Rest the static counter to set the ids
     */
    public static void resetCounter() {
        // reset static rider counter
        riderCount = 0;
    }
}
