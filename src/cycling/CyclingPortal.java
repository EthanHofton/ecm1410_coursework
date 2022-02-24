package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import cycling.Rider;
import cycling.Race;
import cycling.Team;
import cycling.CyclingPortalInterface;

public class CyclingPortal implements CyclingPortalInterface {

    private ArrayList<Team> teams;
    private ArrayList<Race> races;

    // DONE
    public CyclingPortal() {
        // constructior to init lists
        teams = new ArrayList<>();
        races = new ArrayList<>();
    }

    // DONE
    private boolean hasTeam(int teamID) {
        // check if the list 'teams' has teamID
        try {
            findTeam(teamID);
        } catch (IDNotRecognisedException e) {
            return false;
        }
        return true;
    }

    // DONE
    private boolean hasRider(int riderID) {
        
        // check if the list 'teams' has riderID
        try {
            findRider(riderID);
        } catch (IDNotRecognisedException e) {
            return false;
        }
        return true;
    }

    // DONE
    private boolean hasRace(int raceID) {
        
        // check if the list 'teams' has riderID
        try {
            findRace(raceID);
        } catch (IDNotRecognisedException e) {
            return false;
        }
        return true;
    }

    // DONE
    private Team findTeam(int teamID) throws IDNotRecognisedException {
        // check if the list 'teams' has teamID
        // O(n)
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamId() == teamID) {
                return teams.get(i);
            }
        }

        throw new IDNotRecognisedException("Team Id '"+teamID+"' not found");
    }

    // DONE
    private Rider findRider(int riderID) throws IDNotRecognisedException {
        // check if the list 'teams' has teamID
        // O(n^2)
        for (int i = 0; i < teams.size(); i++) {
            for (int j = 0; j < teams.get(i).getRiders().size(); j++) {
                if (teams.get(i).getRiders().get(j).getRiderId() == riderID) {
                    return teams.get(i).getRiders().get(j);
                }
            }
        }

        throw new IDNotRecognisedException("Rider Id '"+riderID+"' not found");
    }

    // DONE
    private Race findRace(int raceID) throws IDNotRecognisedException {
        // check if the list 'races' has raceID
        // O(n)
        for (int i = 0; i < races.size(); i++) {
            if (races.get(i).getRaceId() == raceID) {
                return races.get(i);
            }
        }

        throw new IDNotRecognisedException("Race Id '"+raceID+"' not found");
    }

    // DONE
    private Stage findStage(int stageId) throws IDNotRecognisedException {
        // check if the list 'races' has stageId
        // O(n^2)
        for (int i = 0; i < races.size(); i++) {
            for (int j = 0; j < races.get(i).getStages().size(); j++) {
                if (races.get(i).getStages().get(j).getStageId() == stageId) {
                    return races.get(i).getStages().get(j);
                }
            }
        }

        throw new IDNotRecognisedException("Stage Id '"+stageId+"' not found");
    }

    // DONE
    private Segment findSegment(int segmentId) throws IDNotRecognisedException {
        // check if the list 'races' has Segment with id segmentId

        // O(n^3)
        for (int i = 0; i < races.size(); i++) {
            Race currentRace = races.get(i);
            for (int j = 0; j < currentRace.getStages().size(); j++) {
                Stage currentStage = currentRace.getStages().get(j);
                for (int m = 0; m < currentStage.getSegments().size(); m++) {
                    Segment currentSegment = currentStage.getSegments().get(m);
                    if (currentSegment.getSegmentId() == segmentId) {
                        return currentSegment;
                    }
                }
            }
        }

        throw new IDNotRecognisedException("Segment Id '"+segmentId+"' not found");
    }

    // DONE
    private Segment findSegment(int segmentId, Stage stage) throws IDNotRecognisedException {
        // check if the list 'races' has Segment with id segmentId

        // O(n^3)
        for (int i = 0; i < stage.getSegments().size(); i++) {
            if (stage.getSegments().get(i).getSegmentId() == segmentId) {
                return stage.getSegments().get(i);
            }
        }

        throw new IDNotRecognisedException("Segment Id '"+segmentId+"' not found");
    }

    // DONE
    @Override
    public int[] getRaceIds() {
        int raceIds[] = new int[races.size()];
        for (int i = 0; i < races.size(); i++) {
            raceIds[i] = races.get(i).getRaceId();
        }

        return raceIds;
    }

    // DONE
    @Override
    public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
        if (name == null || name.equals("") || name.length() > 30 || name.contains(" ")) {
            throw new InvalidNameException("name cannot be null, empty, have more than 30 characters or contain white spaces");
        }

        for (int i = 0; i < races.size(); i++) {
            if (name.equals(races.get(i).getName())) {
                throw new IllegalNameException("name alrwdy exists in platform");
            }
        }

        Race race = new Race(name, description);

        races.add(race);

        return race.getRaceId();
    }

    // DONE
    @Override
    public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
        Race race = findRace(raceId);
        double totalLen = 0.0;
        for (Stage stage : race.getStages()) {
            totalLen += stage.getLength();
        }
        String raceDetails = "raceID="+raceId;
        raceDetails += ",name="+race.getName();
        raceDetails += ",description="+race.getDescription();
        raceDetails += ",numberOfStages="+race.getStages().size();
        raceDetails += ",totalLength="+totalLen;
        return raceDetails;
    }

    // HALF-DONE
    @Override
    public void removeRaceById(int raceId) throws IDNotRecognisedException {
        Race raceToRemove = findRace(raceId);
        races.remove(raceToRemove);

        // remove related info, Stages, segments, results
    }

    // DONE
    @Override
    public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
        Race race = findRace(raceId);
        return race.getStages().size();
    }

    // DONE
    @Override
    public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
            StageType type)
            throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
        Race race = findRace(raceId);
        
        for (int i = 0; i < race.getStages().size(); i++) {
            if (race.getStages().get(i).getStageName().equals(stageName)) {
                throw new IllegalNameException("name already exists on platform");
            }
        }

        if (stageName == null || stageName.equals("") || stageName.length() > 30) {
            throw new InvalidNameException("Name cannot be null, empty or more than 30 characters");
        }

        if (length < 5) {
            throw new InvalidLengthException("Length cannot be less than 5km");
        }

        Stage stage = new Stage(race, stageName, description, length, startTime, type);

        race.addStage(stage);

        return stage.getStageId();
    }

    // DONE
    @Override
    public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
        Race race = findRace(raceId);
        int stageIds[] = new int[race.getStages().size()];

        for (int i = 0; i < stageIds.length; i++) {
            stageIds[i] = race.getStages().get(i).getStageId();
        }

        return stageIds;
    }

    // DONE
    @Override
    public double getStageLength(int stageId) throws IDNotRecognisedException {
        Stage stage = findStage(stageId);
        return stage.getLength();
    }

    // HALF-DONE
    @Override
    public void removeStageById(int stageId) throws IDNotRecognisedException {
        Stage stage = findStage(stageId);
        stage.getRace().removeStage(stage);

        // TODO remove stage segments and results
    }

    // DONE
    @Override
    public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
            Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
            InvalidStageTypeException {
        
        if (type == SegmentType.SPRINT) {
            throw new IllegalArgumentException("Segment type is not valid.");
        }

        // throws IDNotRecognisedException
        Stage stage = findStage(stageId);

        if (stage.getLength() < location) {
            throw new InvalidLocationException("location is out of bounds of the stage length");
        }

        // throws InvalidStageStateException
        if (stage.getStageState() == StageState.WAITING_FOR_RESULTS) {
            throw new InvalidStageStateException("Stage cannot be removed while waiting for results");
        }

        if (stage.getType() == StageType.TT) {
            throw new InvalidStageTypeException("Time-trial stages cannot contain any segment");
        }

        ClimbSegment segment = new ClimbSegment(stage, location, type, averageGradient, length);

        stage.addSegment(segment);

        return segment.getSegmentId();
    }

    // DONE
    @Override
    public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
            InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {

        // trows IDNotRecognisedException
        Stage stage = findStage(stageId);

        if (stage.getLength() < location) {
            throw new InvalidLocationException("location is out of bounds of the stage length");
        }

        // throws InvalidStageStateException
        if (stage.getStageState() == StageState.WAITING_FOR_RESULTS) {
            throw new InvalidStageStateException("Stage cannot be removed while waiting for results");
        }

        if (stage.getType() == StageType.TT) {
            throw new InvalidStageTypeException("Time-trial stages cannot contain any segment");
        }

        SprintSegment segment = new SprintSegment(stage, location);

        stage.addSegment(segment);
        
        return segment.getSegmentId();
    }

    // DONE
    @Override
    public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
        
        // throws IDNotRecognisedException
        Segment segmentToRemove = findSegment(segmentId);

        Stage stage = segmentToRemove.getStage();

        // throws InvalidStageStateException
        if (stage.getStageState() == StageState.WAITING_FOR_RESULTS) {
            throw new InvalidStageStateException("Stage cannot be removed while waiting for results");
        }

        // remove segment from stage
        stage.removeSegment(segmentToRemove);
        
    }

    // DONE
    @Override
    public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
        
        // throws IDNotRecognisedExceiption
        Stage stage = findStage(stageId);

        // throws InvalidStageStateException
        stage.concludeStagePreparation();
    }

    // DONE
    @Override
    public int[] getStageSegments(int stageId) throws IDNotRecognisedException {

        // throws IDNotRecognisedExceiption
        Stage stage = findStage(stageId);

        int[] stageSegmentIds = new int[stage.getSegments().size()];

        for (int i = 0; i < stageSegmentIds.length; i++) {
            stageSegmentIds[i] = stage.getSegments().get(i).getSegmentId();
        }

        return stageSegmentIds;
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
        // throws IDNotRecognisedException
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

    //DONE
    @Override
    public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
        // Throws IDNotRecognisedException
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
    
        // check year and name
        if (name == null || yearOfBirth < 1900) {
            throw new IllegalArgumentException("name cannot be null or year less then 1900");
        }

        // throws IDNotRecognisedException
        Team ridersTeam = findTeam(teamID);
        Rider newRider = new Rider(ridersTeam, name, yearOfBirth);

        ridersTeam.addRider(newRider);

        return newRider.getRiderId();
    }

    // HALF-DONE
    @Override
    public void removeRider(int riderId) throws IDNotRecognisedException {
        // throws IDNotRecognisedException
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
