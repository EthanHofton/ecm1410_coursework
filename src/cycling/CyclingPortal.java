package cycling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Cycling Portal implaments CyclingPortalInterface class
 *  
 * @author Ethan Hofton
 * @atuher Jon Tao
 * @version 1.0
 */
public class CyclingPortal implements CyclingPortalInterface {

    private ArrayList<Team> teams;
    private ArrayList<Race> races;

    /**
     * CyclingPortal constructor initalises teams and races array list
     * 
     * @return nothing
     */
    public CyclingPortal() {
        // constructior to init lists
        teams = new ArrayList<>();
        races = new ArrayList<>();
    }

    private Team findTeam(int teamID) throws IDNotRecognisedException {
        // check if the list 'teams' has teamID
        // O(n)

        // loop throguh teams list and cheack the team class's id 
        // against the given id teamID
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamId() == teamID) {
                return teams.get(i);
            }
        }

        // throw IDNotRecognisedException if not found
        throw new IDNotRecognisedException("Team Id '"+teamID+"' not found");
    }

    private Rider findRider(int riderID) throws IDNotRecognisedException {
        // check if the list 'teams' has teamID

        // loop through each team and check if any of the riders on that team
        // match the given rider id
        for (int i = 0; i < teams.size(); i++) {
            for (int j = 0; j < teams.get(i).getRiders().size(); j++) {
                if (teams.get(i).getRiders().get(j).getRiderId() == riderID) {
                    return teams.get(i).getRiders().get(j);
                }
            }
        }

        // throw IDNotRecognisedException if not found
        throw new IDNotRecognisedException("Rider Id '"+riderID+"' not found");
    }

    private Race findRace(int raceID) throws IDNotRecognisedException {
        // check if the list 'races' has raceID

        // loop through races list and check given raceID
        // against the race objects id
        for (int i = 0; i < races.size(); i++) {
            if (races.get(i).getRaceId() == raceID) {
                return races.get(i);
            }
        }

        // throw IDNotRecognisedException if not found
        throw new IDNotRecognisedException("Race Id '"+raceID+"' not found");
    }

    private Stage findStage(int stageId) throws IDNotRecognisedException {
        // check if the list 'races' has stageId

        // loop though each race and loop through each races' stages
        // if stage matches given id, return the stage
        for (int i = 0; i < races.size(); i++) {
            for (int j = 0; j < races.get(i).getStages().size(); j++) {
                if (races.get(i).getStages().get(j).getStageId() == stageId) {
                    return races.get(i).getStages().get(j);
                }
            }
        }

        throw new IDNotRecognisedException("Stage Id '"+stageId+"' not found");
    }

    private Segment findSegment(int segmentId) throws IDNotRecognisedException {
        // check if the list 'races' has Segment with id segmentId

        // loop through each races stages' segments
        // if the segment id matches the given id, return that segment
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getRaceIds() {

        // loop thorugh each race in race list and add races id
        // to a list of ids, return this list
        int raceIds[] = new int[races.size()];
        for (int i = 0; i < races.size(); i++) {
            raceIds[i] = races.get(i).getRaceId();
        }

        return raceIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {

        // erronus arguments checking
        // check if the name is null, empty, contains wihitespace or is longer the 30 charicters
        if (name == null || name.equals("") || name.length() > 30 || name.contains(" ")) {
            // throw an error if name does not meet these paramiters
            throw new InvalidNameException("name cannot be null, empty, have more than 30 characters or contain white spaces");
        }

        // check if the name allready exists in the platform
        // loop through each race and check if the races name matches the given input name
        for (int i = 0; i < races.size(); i++) {
            if (name.equals(races.get(i).getName())) {
                // theow exception if the name allreadt exists on platform
                throw new IllegalNameException("name alrwdy exists in platform");
            }
        }

        // create a new race
        Race race = new Race(name, description);

        // add the race to the cycling portals array list of races
        races.add(race);

        // return the race id
        return race.getRaceId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String viewRaceDetails(int raceId) throws IDNotRecognisedException {

        // find the race object in the system
        // throws IDNotRecognisedException if the id does not exist on the platform
        Race race = findRace(raceId);

        // find the total length
        // init total length to zero
        double totalLen = 0.0;

        // loop through each stage in the race and add the stage length to the total length
        for (Stage stage : race.getStages()) {
            totalLen += stage.getLength();
        }

        // stringify race details using race peramiters
        String raceDetails = "raceID="+raceId;
        raceDetails += ",name="+race.getName();
        raceDetails += ",description="+race.getDescription();
        raceDetails += ",numberOfStages="+race.getStages().size();
        raceDetails += ",totalLength="+totalLen;

        // return the stringified race detials
        return raceDetails;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeRaceById(int raceId) throws IDNotRecognisedException {
        // find the race class in the portal
        Race raceToRemove = findRace(raceId);

        // removing race from the system also removes all related data
        // since the race itself is the only thing that holds references to those
        // related data classes
        // remove the race class from the races array list
        races.remove(raceToRemove);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
        // find the race within the portal
        Race race = findRace(raceId);

        // return the size of the array that stores the stages
        return race.getStages().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
            StageType type)
            throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
        
        // find race in portal
        Race race = findRace(raceId);
        
        // loop throguh all the stages in the race
        for (int i = 0; i < race.getStages().size(); i++) {
            // check if the name allready exists in the race
            // compare each stage name to the new stage name
            if (race.getStages().get(i).getStageName().equals(stageName)) {
                // if stage name allready excists throw an IllegalNameException
                throw new IllegalNameException("name already exists on platform");
            }
        }

        // check if the stage name is null, empty or grater than 30 charicters
        if (stageName == null || stageName.equals("") || stageName.length() > 30) {
            // throw InvalidNameException if paramaters are met
            throw new InvalidNameException("Name cannot be null, empty or more than 30 characters");
        }

        // check if the stage length is less then 5km
        if (length < 5) {
            // throw InvalidLengthException
            throw new InvalidLengthException("Length cannot be less than 5km");
        }

        // create the new stage
        Stage stage = new Stage(race, stageName, description, length, startTime, type);

        // add the stage to the race
        race.addStage(stage);

        // return the stage id
        return stage.getStageId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
        // find the race in the portal
        Race race = findRace(raceId);

        // initalise stage id list to return
        // set array to the size of the number of stages for that stage
        int stageIds[] = new int[race.getStages().size()];

        // loop through all the stages in the race
        for (int i = 0; i < stageIds.length; i++) {
            // set each value of the array to the corrisponding stage id
            stageIds[i] = race.getStages().get(i).getStageId();
        }

        // return the list of stage ids
        return stageIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getStageLength(int stageId) throws IDNotRecognisedException {
        // find the stage in the system
        Stage stage = findStage(stageId);

        // return the length of the stage
        return stage.getLength();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeStageById(int stageId) throws IDNotRecognisedException {
        // find the stage in the portal
        Stage stage = findStage(stageId);

        // removing the stage also removes all stage related data
        // this is because the stage class is the only class that stores a referance
        // to these classes
        //
        // remove the stage from the race
        stage.getRace().removeStage(stage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
            Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
            InvalidStageTypeException {
        
        // a climb segment cannot be a sprint
        if (type == SegmentType.SPRINT) {
            // throw an illigal argument exception if the given segment time is sprint
            throw new IllegalArgumentException("Segment type is not valid.");
        }

        // find stage in portal
        // throws IDNotRecognisedException
        Stage stage = findStage(stageId);

        // check if the segment location is out of bounds of the stage
        if (stage.getLength() < location) {
            // throw InvalidLocationException
            throw new InvalidLocationException("location is out of bounds of the stage length");
        }

        // check if the stage stage is correct
        // cannot add a new segment if the stage has conculded the stage preperation
        if (stage.getStageState() == StageState.WAITING_FOR_RESULTS) {
            // throw InvalidStageStateException
            throw new InvalidStageStateException("Stage cannot be added while waiting for results");
        }

        // time trial stages cannot contain a segment
        // check if the stage type is time trial
        if (stage.getType() == StageType.TT) {
            // if the type is a time trial, throw an InvalidStageTypeException
            throw new InvalidStageTypeException("Time-trial stages cannot contain any segment");
        }

        // create new climb segment with the paramiters
        ClimbSegment segment = new ClimbSegment(stage, location, type, averageGradient, length);

        // add the segment to the stage
        stage.addSegment(segment);

        // return the id of the new segment
        return segment.getSegmentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
            InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {

        // find stage in portal
        // trows IDNotRecognisedException
        Stage stage = findStage(stageId);

        // check the location is in bounds of the stage
        if (stage.getLength() < location) {
            // throw InvalidLocationException if out of bounds
            throw new InvalidLocationException("location is out of bounds of the stage length");
        }

        // cannot add segment if stage has fininished stage preperation
        // check the stage state is not waiting for results
        if (stage.getStageState() == StageState.WAITING_FOR_RESULTS) {
            // throw InvalidStageStateException
            throw new InvalidStageStateException("Stage cannot be removed while waiting for results");
        }

        // time trial stages cannot have any segments
        // check the stage type is not time trial
        if (stage.getType() == StageType.TT) {
            // if the stage type is time trial, throw InvalidStageTypeException
            throw new InvalidStageTypeException("Time-trial stages cannot contain any segment");
        }

        // create a new sprint segment
        SprintSegment segment = new SprintSegment(stage, location);

        // add sprint segment to stage
        stage.addSegment(segment);
        
        // return the new segment id
        return segment.getSegmentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
        
        // find segment in portal
        // throws IDNotRecognisedException
        Segment segmentToRemove = findSegment(segmentId);

        // get the stage the segment belongs to
        Stage stage = segmentToRemove.getStage();

        // cannot remove segment if stage preperation has finsihed
        // check the state of the stage is not waiting for results
        if (stage.getStageState() == StageState.WAITING_FOR_RESULTS) {
            // if stage state is wiating for results, throw InvalidStageStateException
            throw new InvalidStageStateException("Stage cannot be removed while waiting for results");
        }

        // remove segment from stage
        stage.removeSegment(segmentToRemove);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
        // find the stage in the portal
        // throws IDNotRecognisedExceiption
        Stage stage = findStage(stageId);

        // conculde the stage preperation
        // throws InvalidStageStateException
        stage.concludeStagePreparation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getStageSegments(int stageId) throws IDNotRecognisedException {

        // find the stage in the portal
        // throws IDNotRecognisedExceiption
        Stage stage = findStage(stageId);

        // init new array the size of the number of segments in the stage
        int[] stageSegmentIds = new int[stage.getSegments().size()];

        // loop through each segment in the stage
        for (int i = 0; i < stageSegmentIds.length; i++) {
            // add the segments id to the respective index in the array
            stageSegmentIds[i] = stage.getSegments().get(i).getSegmentId();
        }

        // return the segment ids
        return stageSegmentIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {

        // check if team name allready exists
        // loop through each time
        for (Team team : teams) {
            // check if the team name is equal to the new team name
            if (name.equals(team.getTeamName())) {
                // if equal, throw IllegalNameException
                throw new IllegalNameException("Team name allready exisits");
            }
        }

        // check the desciption
        // the description has to be less then 30 chars, not null and not empty
        if (name.length() > 30 || name.equals("") || name == null) {
            // throw InvalidNameException if params are not met
            throw new InvalidNameException("Name cannot be null, empty or longer then 30");
        }

        // create a new team and add it to the teams array list
        Team newTeam = new Team(name, description);
        teams.add(newTeam);

        // return the new teams id
        return newTeam.getTeamId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTeam(int teamId) throws IDNotRecognisedException {

        // find the team in the portal
        // throws IDNotRecognisedException
        Team teamToRemove = findTeam(teamId);

        // remove the team referance from the teams array list
        // the team is the only object that stores the team realted data
        // threfore, deleting the team also deletes all its related data
        teams.remove(teamToRemove);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getTeams() {
        // return the ids as an array of all the teams
        // init new array the size of the numnber of teams in the portal
        int[] teamsToReturn = new int[teams.size()];

        // loop through each value in the array
        for (int i = 0; i < teams.size(); i++) {
            // add the team id to the respective index in the array
            teamsToReturn[i] = teams.get(i).getTeamId();
        }

        // return the array
        return teamsToReturn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
        // find team in portal
        // Throws IDNotRecognisedException
        Team team = findTeam(teamId);

        // create an array the size of all the riders there are in the given team
        int teamRiders[] = new int[team.getRiders().size()];

        // loop through each rider in the team
        for (int i = 0; i < team.getRiders().size(); i++) {
            // add there id to the array to there corrisponding index
            teamRiders[i] = team.getRiders().get(i).getRiderId();
        }
        
        // return the array of rider ids
        return teamRiders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int createRider(int teamID, String name, int yearOfBirth) throws IDNotRecognisedException, IllegalArgumentException {
    
        // check that the rider name is not null
        // and the year of birth is not before 1900
        if (name == null || yearOfBirth < 1900) {
            // if the name or year of birth breaks these paramiters, throw IllegalArgumentException
            throw new IllegalArgumentException("name cannot be null or year less then 1900");
        }

        // find the riders team in the portal
        // throws IDNotRecognisedException
        Team ridersTeam = findTeam(teamID);

        // create a new rider
        Rider newRider = new Rider(ridersTeam, name, yearOfBirth);

        // add the rider to the tema
        ridersTeam.addRider(newRider);

        // return the new riders id
        return newRider.getRiderId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeRider(int riderId) throws IDNotRecognisedException {

        // find rider in portal
        // throws IDNotRecognisedException
        Rider rider = findRider(riderId);

        // remove the rider from the team
        rider.getTeam().removeRider(rider);
        
        // remove rider race results
        // loop through each race in the portal
        for (int i = 0; i < races.size(); i++)
        {
            // store the race
            Race race = races.get(i);
            // loop through each races stages
            for (int j = 0; j < race.getStages().size(); j++)
            {
                // store the stage
                Stage stage = race.getStages().get(i);

                // create a tempory array to store all the results that need to be
                // removed from the stage as they referance rider
                ArrayList<Results> resultsToRemove = new ArrayList<>();
                for (int m = 0; m < stage.getResults().size(); m++)
                {
                    // store the result
                    Results result = stage.getResults().get(m);
                    // check if the riders id of the result matches the given rider id to remove
                    if (result.getRider().getRiderId() == riderId)
                    {
                        // if the result needs to be removed, add it to the remove list
                        resultsToRemove.add(result);
                    }
                }

                // loop through each result to remove
                for (Results result : resultsToRemove)
                {
                    // remove result from stage
                    stage.removeResults(result);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
            throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
            InvalidStageStateException {

        // find rider in portal
        // throws IDNotRecognisedException
        Rider rider = findRider(riderId);

        // find stage in portal
        // throws IDNotRecognisedException
        Stage stage = findStage(stageId);
        
        // check rider does not have duplicate result
        // loop through each result in stage
        for (int i = 0; i < stage.getResults().size(); i++) {
            // check the rider does not have a result by
            // comparing the riders id with the stages riders id
            if (stage.getResults().get(i).getRider() == rider) {
                // duplicate found
                // throw DuplicatedResultException
                throw new DuplicatedResultException("Stage allready has results for rider");
            }
        }

        // check length of checkpoints is equal to n+2
        if (checkpoints.length != stage.getSegments().size() + 2) {
            // throw InvalidCheckpointsException
            throw new InvalidCheckpointsException("length of checkpoints is invalid");
        }

        // check if stage is "waiting for results"
        if (stage.getStageState() != StageState.WAITING_FOR_RESULTS) {
            // stage waiting for results, throwInvalidStageStateException
            throw new InvalidStageStateException("Invalid stage state");
        }

        // create a new result
        Results result = new Results(stage, rider, checkpoints);

        // add result to stage
        stage.addResults(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {

        // find stage in portal
        // throws IDNotRecognisedException
        Stage stage = findStage(stageId);

        // find rider in portal
        // throws IDNotRecognisedException
        Rider rider = findRider(riderId);

        // init rider result to null
        Results riderResult = null;

        // find rider results
        // loop through each stages result
        for (int i = 0; i < stage.getResults().size(); i++) {
            // if the target riders id matches the stages results rider id
            // then rider result found
            if (rider == stage.getResults().get(i).getRider()) {
                // save the rider result
                riderResult = stage.getResults().get(i);
            }
        }

        // if the rider result is still null, the result has not been found
        if (riderResult == null) {
            // return an empty localtime array
            return new LocalTime[0];
        }

        // initalise a rider results array that is the size of all of the riders results in the stage
        // add one at the end to store the elapsed time
        LocalTime[] riderResults = new LocalTime[riderResult.getTimes().length + 1];

        // loop through all the results times
        for (int i = 0; i < riderResult.getTimes().length; i++) {
            // store the result times in the array
            riderResults[i] = riderResult.getTimes()[i];
        }

        // store the elapsed time in the last spot of the array
        // elpased time calculated using result calculateElapsedTime() function
        riderResults[riderResult.getTimes().length] = riderResult.calculateElapsedTime();

        // return the results array
        return riderResults;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {

        // find stage in portal
        // throws IDNotRecognisedException
        Stage stage = findStage(stageId);

        // find the rider in the portal
        // throws IDNotRecognisedException
        Rider rider = findRider(riderId);
        
        // initalise rider result as null
        Results riderResult = null;

        // find rider results
        // loop through all the resutls in the stage
        for (int i = 0; i < stage.getResults().size(); i++) {
            // cheack if the results rider matches the target rider
            if (rider == stage.getResults().get(i).getRider()) {
                // if the ids are the same, result is found
                // store result in riderResult
                riderResult = stage.getResults().get(i);
            }
        }

        // if riderResult is still null, no result found
        if (riderResult == null) {
            // if not result found, return null
            return null;
        }

        // otherwise, return the riders adjusted elapsed time
        // calculated using results calcaulateAdjustedElapsedTime()
        return riderResult.calculateAdjustedElapsedTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
        
        // find stage in portal
        // throws IDNOtRecognisedException
        Stage stage = findStage(stageId);

        // find rider in stage
        // throws IDNOtRecognisedException
        Rider rider = findRider(riderId);

        // initalise rider result as null
        Results riderResult = null;

        // find rider results
        // loop through all the results in the stage
        for (int i = 0; i < stage.getResults().size(); i++) {
            // check the results rider id matches target rider id
            if (rider == stage.getResults().get(i).getRider()) {
                // if ids match, rider result found
                riderResult = stage.getResults().get(i);
            }
        }

        // if rider result still null, rider result does not excist
        if (riderResult == null) {
            // no results to be removed
            // return
            return;
        }

        // otherwise, remove results from stage
        stage.removeResults(riderResult);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
        
        // find stage in portal
        // throws IDNotRecognisedException
        Stage stage = findStage(stageId);

        // create a list of results the size of all the results in the stage
        Results[] rankedResults = new Results[stage.getResults().size()];

        // loop throguh all results in stage
        for (int i = 0; i < rankedResults.length; i++) {
            // add the results to the results array
            rankedResults[i] = stage.getResults().get(i);
        }

        // sort array of results by there elapsed time
        // do this using custom comparitor class, ResultsElapsedTimeComparator.
        // this returns the differeance between the elapsed times of the results and orders by differance
        Arrays.sort(rankedResults, new ResultsElapsedTimeComparator());

        // create a new array to return the riders results
        // array the size of all theriders in the stage
        int[] riderRanks = new int[rankedResults.length];

        // loop throguh all the results
        for (int i = 0; i < riderRanks.length; i++) {
            // add the rider id to the array
            // since rankedResults is ordered by elapsed time, so will riderRanks
            riderRanks[i] = rankedResults[i].getRider().getRiderId();
        }

        // return the ranked list of riders
        return riderRanks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {

        // find stage in portal
        // throws IDNotRecognisedException
        Stage stage = findStage(stageId);

        // get riders rank in stage
        // throws IDNotRecognisedException
        int[] ridersRanked = getRidersRankInStage(stageId);

        // create a new array of localtimes to store the ranked adjusted elpased times
        LocalTime[] riderAdjustedElapsedTimes = new LocalTime[ridersRanked.length];

        // loop through all the riders from ridersRanked
        for (int i = 0; i < riderAdjustedElapsedTimes.length; i++) {
            // get the rider
            Rider rider = findRider(ridersRanked[i]);

            // loop throguh the stages reults to find the rider result
            for (int x = 0; x < stage.getResults().size(); x++) {
                // cheack if the stage result belongs to the rider
                if (stage.getResults().get(x).getRider() == rider) {
                    // use the found result to calculate the adjusted elapsed time
                    // append to array at index i. Sicne ridersRanked is ordered by elapsed time,
                    // so will riderAdjustedElapsedTimes
                    riderAdjustedElapsedTimes[i] = stage.getResults().get(x).calculateAdjustedElapsedTime();

                    // break out of inner loop to save time
                    continue;
                }
            }
        }

        // return the array or ranked adjusted elapsed times
        return riderAdjustedElapsedTimes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
        
        // find stage in portal
        // throws IDNotRecognisedException
        Stage stage = findStage(stageId);

        // get a ranked list of rider ids
        // throws IDNotRecognisedException
        int[] ridersRanked = getRidersRankInStage(stageId);

        // init an array to store the ranked riders points
        // the size of the number of riders in the stage
        int[] riderPoints = new int[ridersRanked.length];

        // loop through all the riders
        for (int i = 0; i < riderPoints.length; i++) {
            // get the rider
            Rider rider = findRider(ridersRanked[i]);

            // add the rider points to the riderPoins array at index i
            // since ridersRanked is ordered by elapsed time, so will riderPoints
            // rider points calculated using Rider.getPointsInStage() function
            riderPoints[i] = rider.getPointsInStage(stage, i+1);
        }

        // return the ordered array of rider points
        return riderPoints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {

        // find stage in portal
        // throws IDNotRecognisedException
        Stage stage = findStage(stageId);

        // get the ranked list of riders
        // throws IDNotRecognisedException
        int[] ridersRanked = getRidersRankInStage(stageId);

        // init a new array to store the mountain points for a rider in a stage
        int[] riderPoints = new int[ridersRanked.length];

        // loop through each rider in the stage
        for (int i = 0; i < riderPoints.length; i++) {
            // get the rider
            Rider rider = findRider(ridersRanked[i]);

            // set the points at index i in riderPoints for the mountain points that rider has aquired
            // mountain points calculated using Stage.pointsForMountainClassification()
            // since ridersRanked is ordered by elapsed time, so will riderPoints
            riderPoints[i] = stage.pointsForMountainClassification(rider);
        }

        // return the ordered list of mountain points
        return riderPoints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eraseCyclingPortal() {

        // clear cycling portal lists
        // clear team
        // clear races
        teams.clear();
        races.clear();

        // reset counters
        // reset race id counter
        // reset rider id counder
        // reset segment id counter
        // reset stage id counter
        // reset ream id counter
        Race.resetCounter();
        Rider.resetCounter();
        Segment.resetCounter();
        Stage.resetCounter();
        Team.resetCounter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveCyclingPortal(String filename) throws IOException {
        
        // create a new output file stream
        ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(filename));

        // write the cycling portal to the output stream
        ostream.writeObject(this);

        // close and commit the output stream
        ostream.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {

        // create a new input file stream
        ObjectInputStream istream = new ObjectInputStream(new FileInputStream(filename));

        // create a new object and assin it to the value in the file
        Object portalObject = istream.readObject();

        // chack if the portal is an instance of cycling portal
        if (!(portalObject instanceof CyclingPortal)) {
            // close input file stream
            istream.close();

            // throw exceiption
            throw new InvalidClassException("Object from file is not an instance of cycling portal");
        }

        // otherwise, upcast the portal object to a cycling portal
        CyclingPortal portal = (CyclingPortal)portalObject;

        // assin this cycling portals race list and team list to serialised portals
        this.races = portal.races;
        this.teams = portal.teams;

        // close the input file stream
        istream.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeRaceByName(String name) throws NameNotRecognisedException {

        // initalise a race as null
        Race race = null;

        // loop through all the races
        for (int i = 0; i < races.size(); i++) {
            // cheack the target name and races name match
            if (races.get(i).getName() == name) {
                // if they match, assin race to the current race
                race = races.get(i);

                // break out of loop as race allready found
                break;
            }
        }

        // if race still null, race not found
        if (race == null) {
            // throw NameNotRecognisedException
            throw new NameNotRecognisedException("Race is not found with name " + name);
        }
        
        // remove the race from the portal
        races.remove(race);

        // since stage is stored in race and segments and results are stored in stage
        // deleting the race will also delete segments, results and stage
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
        // throws IDNotRecognisedException
        Race race = findRace(raceId);
        
        for (int i = 0; i < race.getStages().size(); i++) {
            if (race.getStages().get(i).getResults().size() == 0) {
                return new int[0];
            }
        }

        ArrayList<Results> results = new ArrayList<>();
        for (int i = 0; i < race.getStages().size(); i++) {
            for (int x = 0; x < race.getStages().get(i).getResults().size(); x++) {
                results.add(race.getStages().get(i).getResults().get(x));
            }
        }

        Map<Rider, LocalTime> timesMap = new HashMap<Rider, LocalTime>();
        for (int i = 0; i < results.size(); i++) {
            Rider currentRider = results.get(i).getRider();
            if (timesMap.containsKey(currentRider)) {
                long nanos = results.get(i).calculateAdjustedElapsedTime().toNanoOfDay();
                LocalTime newTime = timesMap.get(currentRider).plusNanos(nanos);
                timesMap.replace(currentRider, newTime);
            } else {
                timesMap.put(currentRider, results.get(i).calculateAdjustedElapsedTime());
            }
        }

        ArrayList<Map.Entry<Rider, LocalTime>> sorted = new ArrayList<>(timesMap.entrySet());
        sorted.sort(new ResultsAdjustedElapsedTimeCompatiror());

        int orderedRiderIds[] = new int[sorted.size()];
        for (int i = 0; i < orderedRiderIds.length; i++) {
            orderedRiderIds[i] = sorted.get(i).getKey().getRiderId();
        }

        return orderedRiderIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
        // throws IDNotRecognisedException
        Race race = findRace(raceId);
        
        for (int i = 0; i < race.getStages().size(); i++) {
            if (race.getStages().get(i).getResults().size() == 0) {
                return new LocalTime[0];
            }
        }
        
        ArrayList<Results> results = new ArrayList<>();
        for (int i = 0; i < race.getStages().size(); i++) {
            for (int x = 0; x < race.getStages().get(i).getResults().size(); x++) {
                results.add(race.getStages().get(i).getResults().get(x));
            }
        }

        Map<Rider, LocalTime> timesMap = new HashMap<Rider, LocalTime>();
        for (int i = 0; i < results.size(); i++) {
            Rider currentRider = results.get(i).getRider();
            if (timesMap.containsKey(currentRider)) {
                long nanos = results.get(i).calculateAdjustedElapsedTime().toNanoOfDay();
                LocalTime newTime = timesMap.get(currentRider).plusNanos(nanos);
                timesMap.replace(currentRider, newTime);
            } else {
                timesMap.put(currentRider, results.get(i).calculateAdjustedElapsedTime());
            }
        }

        ArrayList<Map.Entry<Rider, LocalTime>> sorted = new ArrayList<>(timesMap.entrySet());
        sorted.sort(new ResultsAdjustedElapsedTimeCompatiror());

        LocalTime orderedTimes[] = new LocalTime[sorted.size()];
        for (int i = 0; i < orderedTimes.length; i++) {
            orderedTimes[i] = sorted.get(i).getValue();
        }

        return orderedTimes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
        // throws IDNotRecognisedException
        Race race = findRace(raceId);
        
        for (int i = 0; i < race.getStages().size(); i++) {
            if (race.getStages().get(i).getResults().size() == 0) {
                return new int[0];
            }
        }

        ArrayList<Results> results = new ArrayList<>();
        for (int i = 0; i < race.getStages().size(); i++) {
            for (int x = 0; x < race.getStages().get(i).getResults().size(); x++) {
                results.add(race.getStages().get(i).getResults().get(x));
            }
        }

        Map<Rider, LocalTime> timesMap = new HashMap<Rider, LocalTime>();
        for (int i = 0; i < results.size(); i++) {
            Rider currentRider = results.get(i).getRider();
            if (timesMap.containsKey(currentRider)) {
                long nanos = results.get(i).calculateAdjustedElapsedTime().toNanoOfDay();
                LocalTime newTime = timesMap.get(currentRider).plusNanos(nanos);
                timesMap.replace(currentRider, newTime);
            } else {
                timesMap.put(currentRider, results.get(i).calculateAdjustedElapsedTime());
            }
        }

        ArrayList<Map.Entry<Rider, LocalTime>> sorted = new ArrayList<>(timesMap.entrySet());
        sorted.sort(new ResultsAdjustedElapsedTimeCompatiror());

        int ridersPoints[] = new int[sorted.size()];
        for (int i = 0; i < ridersPoints.length; i++) {
            ridersPoints[i] = 0;
        }

        // for each rider, find the total points in all stages
        for (int i = 0; i < race.getStages().size(); i++) {
            Stage currentStage = race.getStages().get(i);
            int ridersRanks[] = getRidersRankInStage(currentStage.getStageId());

            for (int x = 0; x < ridersRanks.length; x++) {
                int id = ridersRanks[x];
                int rank = x + 1;

                for (int y = 0; y < sorted.size(); y++) {
                    if (id == sorted.get(y).getKey().getRiderId()) {
                        ridersPoints[y] += sorted.get(y).getKey().getPointsInStage(currentStage, rank);
                    }
                }
            }
        }

        return ridersPoints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
       // throws IDNotRecognisedException
       Race race = findRace(raceId);
        
       for (int i = 0; i < race.getStages().size(); i++) {
           if (race.getStages().get(i).getResults().size() == 0) {
               return new int[0];
           }
       }

       ArrayList<Results> results = new ArrayList<>();
       for (int i = 0; i < race.getStages().size(); i++) {
           for (int x = 0; x < race.getStages().get(i).getResults().size(); x++) {
               results.add(race.getStages().get(i).getResults().get(x));
           }
       }

       Map<Rider, LocalTime> timesMap = new HashMap<Rider, LocalTime>();
       for (int i = 0; i < results.size(); i++) {
           Rider currentRider = results.get(i).getRider();
           if (timesMap.containsKey(currentRider)) {
               long nanos = results.get(i).calculateAdjustedElapsedTime().toNanoOfDay();
               LocalTime newTime = timesMap.get(currentRider).plusNanos(nanos);
               timesMap.replace(currentRider, newTime);
           } else {
               timesMap.put(currentRider, results.get(i).calculateAdjustedElapsedTime());
           }
       }

       ArrayList<Map.Entry<Rider, LocalTime>> sorted = new ArrayList<>(timesMap.entrySet());
       sorted.sort(new ResultsAdjustedElapsedTimeCompatiror());

       int ridersPoints[] = new int[sorted.size()];
       for (int i = 0; i < ridersPoints.length; i++) {
           ridersPoints[i] = 0;
       }

       // for each rider, find the total points in all stages
       for (int i = 0; i < race.getStages().size(); i++) {
           Stage currentStage = race.getStages().get(i);

            for (int y = 0; y < sorted.size(); y++) {
                ridersPoints[y] += sorted.get(y).getKey().getMountainPointsInStage(currentStage);
           }
       }

       return ridersPoints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
        
        int riderIds[] = getRidersGeneralClassificationRank(raceId);
        int riderPoints[] = getRidersPointsInRace(raceId);

        Map<Rider, Integer> pointsMap = new HashMap<Rider, Integer>();
        for (int i = 0; i < riderIds.length; i++) {
            Rider currentRider = findRider(riderIds[i]);
            pointsMap.put(currentRider, riderPoints[i]);
        }

        ArrayList<Map.Entry<Rider, Integer>> sorted = new ArrayList<>(pointsMap.entrySet());

        sorted.sort(Comparator.comparing(Map.Entry<Rider, Integer>::getValue, (p1, p2) -> {
            return p2 - p1;
        }));

        int sortedIds[] = new int[riderIds.length];
        for (int i = 0; i < sortedIds.length; i++) {
            sortedIds[i] = sorted.get(i).getKey().getRiderId();
        }

        return sortedIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
        int riderIds[] = getRidersGeneralClassificationRank(raceId);
        int riderPoints[] = getRidersMountainPointsInRace(raceId);

        Map<Rider, Integer> pointsMap = new HashMap<Rider, Integer>();
        for (int i = 0; i < riderIds.length; i++) {
            Rider currentRider = findRider(riderIds[i]);
            pointsMap.put(currentRider, riderPoints[i]);
        }

        ArrayList<Map.Entry<Rider, Integer>> sorted = new ArrayList<>(pointsMap.entrySet());

        sorted.sort(Comparator.comparing(Map.Entry<Rider, Integer>::getValue, (p1, p2) -> {
            return p2 - p1;
        }));

        int sortedIds[] = new int[riderIds.length];
        for (int i = 0; i < sortedIds.length; i++) {
            sortedIds[i] = sorted.get(i).getKey().getRiderId();
        }

        return sortedIds;
    }
    
}
