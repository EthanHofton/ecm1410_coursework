package cycling;

import java.util.ArrayList;
import cycling.Rider;

public class Team {
    
    private static int teamCount = 0;

    private ArrayList<Rider> teamRiders;

    private int teamId; // readonly (write once)
    private String teamName; // readonly (write once)
    private String teamDescription; // readonly (write once)

    Team(String teamName, String teamDescription) {
        this.teamRiders = new ArrayList<>();
        this.teamId = teamCount++;

        this.teamName = teamName;
        this.teamDescription = teamDescription;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public ArrayList<Rider> getRiders() {
        return teamRiders;
    }

    public void addRider(Rider newRider) {
        // add rider to arraylist
        teamRiders.add(newRider);
    }

    public void removeRider(Rider riderToRemove) throws IDNotRecognisedException {
        // try find the riderID 
        try {
            int riderPosition = findRider(riderToRemove);
            teamRiders.remove(riderPosition);
        } catch (IDNotRecognisedException e) {
            throw new IDNotRecognisedException("Team with id '"+teamId+"' does not have rider with id '"+riderToRemove.getRiderId()+"'");
        }
    }

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

    // TODO
    public String toString() {
        return "Team[";
    }
}
