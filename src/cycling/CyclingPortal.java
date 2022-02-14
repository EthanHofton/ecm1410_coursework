package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import cycling.Rider;
import cycling.CyclingPortalInterface;

public class CyclingPortal implements CyclingPortalInterface {

    private ArrayList<Team> teams;

    public CyclingPortal() {
        // constructior to init lists
        teams = new ArrayList<>();
    }

    private boolean hasTeam(int teamID) {
        // check if the list 'teams' has teamID
        try {
            findTeam(teamID);
        } catch (IDNotRecognisedException e) {
            return false;
        }
        return true;
    }

    private boolean hasRider(int riderID) {
        // check if the list 'teams' has teamID
        try {
            findRider(riderID);
        } catch (IDNotRecognisedException e) {
            return false;
        }
        return true;
    }

    private Team findTeam(int teamID) throws IDNotRecognisedException {
        // check if the list 'teams' has teamID
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamId() == teamID) {
                return teams.get(i);
            }
        }

        throw new IDNotRecognisedException("Team Id '"+teamID+"' not found");
    }

    private Rider findRider(int riderID) throws IDNotRecognisedException {
        // check if the list 'teams' has teamID
        for (int i = 0; i < teams.size(); i++) {
            for (int j = 0; j < teams.get(i).getRiders().size(); j++)
            {
                if (teams.get(i).getRiders().get(j).getRiderId() == riderID) {
                    return teams.get(i).getRiders().get(j);
                }
            }
        }

        throw new IDNotRecognisedException("Team Id '"+riderID+"' not found");
    }

    @Override
    public int[] getRaceIds() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeRaceById(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
            StageType type)
            throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getStageLength(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void removeStageById(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
            Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
            InvalidStageTypeException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
            InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    // DONE
    @Override
    public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {

        // check if team name allready exists
        for (Team team : teams) {
            if (name.equals(team.getTeamName())) {
                throw new IllegalNameException("Team name allready exisits");
            }
        }

        // check the desciption
        if (name.length() > 30 || name.equals("") || name == null) {
            throw new InvalidNameException("Name cannot be null, empty or longer then 30");
        }

        Team newTeam = new Team(name, description);
        teams.add(newTeam);

        return newTeam.getTeamId();
    }

    // DONE
    @Override
    public void removeTeam(int teamId) throws IDNotRecognisedException {
        // Check team exists
        if (!hasTeam(teamId)) {
            throw new IDNotRecognisedException("Team with id '"+teamId+"' not found");
        }

        Team teamToRemove = findTeam(teamId);

        teams.remove(teamToRemove);
    }

    // DONE
    @Override
    public int[] getTeams() {
        // return the ids as an array of all the teams
        int[] teamsToReturn = new int[teams.size()];
        for (int i = 0; i < teams.size(); i++) {
            teamsToReturn[i] = teams.get(i).getTeamId();
        }
        return teamsToReturn;
    }

    @Override
    public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        if (!hasTeam(teamId)) {
            throw new IDNotRecognisedException("Team with id '"+teamId+"' not found");
        }

        Team team = findTeam(teamId);
        int teamRiders[] = new int[team.getRiders().size()];

        for (int i = 0; i < team.getRiders().size(); i++) {
            teamRiders[i] = team.getRiders().get(i).getRiderId();
        }
        
        return teamRiders;
    }

    // DONE
    @Override
    public int createRider(int teamID, String name, int yearOfBirth) throws IDNotRecognisedException, IllegalArgumentException {
    
        // cheack the teamID exists, if not throw IDNotRecognisedException
        if (!hasTeam(teamID)) {
            throw new IDNotRecognisedException("Team with id '"+teamID+"' not found");
        }

        // check year and name
        if (name == null || yearOfBirth < 1900) {
            throw new IllegalArgumentException("name cannot be null or year less then 1900");
        }

        Team ridersTeam = findTeam(teamID);
        Rider newRider = new Rider(ridersTeam, name, yearOfBirth);

        ridersTeam.addRider(newRider);

        return newRider.getRiderId();
    }

    @Override
    public void removeRider(int riderId) throws IDNotRecognisedException {
        if (!hasRider(riderId)) {
            throw new IDNotRecognisedException("Rider with id '"+riderId+"' not found");
        }

        Rider rider = findRider(riderId);
        rider.getTeam().removeRider(rider);
        // TODO remove all race results (not implamented race)
    }

    @Override
    public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
            throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
            InvalidStageStateException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void eraseCyclingPortal() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void saveCyclingPortal(String filename) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeRaceByName(String name) throws NameNotRecognisedException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
