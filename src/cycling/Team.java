package cycling;

import java.util.ArrayList;


/**
 * Team class stores team ID and data relavent to team
 * 
 * @author Ethan Hofton
 * @author Jon Tau
 * @version 1.0
 * 
 */
public class Team {
    
    private static int teamCount = 0;

    private ArrayList<Rider> teamRiders;

    private int teamId;
    private String teamName;
    private String teamDescription;

    /**
     * Team construtor. initalises team ID
     * 
     * @param teamName the name of the team
     * @param teamDescription the team description
     */
    Team(String teamName, String teamDescription) {
        this.teamRiders = new ArrayList<>();
        this.teamId = teamCount++;

        this.teamName = teamName;
        this.teamDescription = teamDescription;
    }

    /**
     * Getter for {@code this.teamId}
     * 
     * @return the id of the team
     */
    public int getTeamId() {
        return teamId;
    }

    /**
     * Getter for {@code this.teamName}
     * 
     * @return the name of the team
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Getter for {@code this.teamDescription}
     * 
     * @return the desciption of the team
     */
    public String getTeamDescription() {
        return teamDescription;
    }

    /**
     * Getter for {@code this.teamRiders}
     * 
     * @return an array of the riders on the team
     * @see cycling.Rider
     */
    public ArrayList<Rider> getRiders() {
        return teamRiders;
    }

    /**
     * add rider to team
     * 
     * @param newRider the rider to add to the team
     * @see cycling.Rider
     */
    public void addRider(Rider newRider) {
        // add rider to arraylist
        teamRiders.add(newRider);
    }

    /**
     * remove a rider from the team
     * 
     * @param riderToRemove the rider to remove from the team
     * @throws IDNotRecognisedException if the rider is not in the team
     * @see cycling.Rider
     */
    public void removeRider(Rider riderToRemove) throws IDNotRecognisedException {
        // findRider throws IDNotRecognisedException
        int riderPosition = findRider(riderToRemove);
        teamRiders.remove(riderPosition);
        
    }

    /**
     * return the index of the rider in {@code this.teamRiders}
     * 
     * @param riderToFind the rider to find
     * @return the index of the rider in the rider array
     * @throws IDNotRecognisedException if the rider is not in the team
     * @see cycling.Rider
     */
    public int findRider(Rider riderToFind) throws IDNotRecognisedException {

        // loops through all team riders
        // checks id against given rider id
        // if ids match, return the position, id not throw exception
        for (int i = 0; i < teamRiders.size(); i++) {
            if (teamRiders.get(i).getRiderId() == riderToFind.getRiderId()) {
                return i;
            }
        }

        throw new IDNotRecognisedException("Rider id not found");
    }

    /**
     * Check if the rider is in the team
     * 
     * @param riderToFind the rider to find
     * @return boolean wether the rider is in the team
     * @see cycling.Rider
     */
    public boolean containsRider(Rider riderToFind) {
        // try find the rider using findRider function
        // if the function throws an IDNotRecognisedException exception,
        // the rider does not exists and reutrn false,
        // otherwise return ture
        try {
            findRider(riderToFind);
        } catch (IDNotRecognisedException e) {
            return false;
        }

        return true;
    } 

    /**
     * Rider toString
     * 
     * @return a formatted string with relevent rider data
     */
    public String toString() {
        return "Team[";
    }
}
