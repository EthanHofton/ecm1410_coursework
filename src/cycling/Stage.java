package cycling;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Stage class to store stage id and data related to stage
 * 
 * @author Ethan Hofton
 * @author Jon Tao
 * @version 1.0
 */
public class Stage implements Serializable {
    private static int stageCount = 0;
    private int stageId;
    private Race race;
    private String stageName;
    private String description;
    private double length; // in KM
    private LocalDateTime startTime;
    private StageType type;
    private StageState stageState;

    private ArrayList<Segment> segments;
    private ArrayList<Results> results;
    
    /**
     * Stage contrustor
     * 
     * @param race the race the stage belongs to
     * @param stageName the name of the stage
     * @param description the stage description
     * @param length the length of the stage
     * @param startTime the time the stage will begin
     * @param type the type of stage
     * @see cycling.Race
     * @see cycling.StageType
     */
    public Stage(Race race, String stageName, String description, double length, LocalDateTime startTime, StageType type) {
        // set the stage id and increment the static stage counter
        this.stageId = stageCount++;

        // set class attributes
        this.race = race;
        this.stageName = stageName;
        this.description = description;
        this.length = length;
        this.startTime = startTime;
        this.type = type;
        this.stageState = StageState.STAGE_PREPERATION;

        // initalize the class array lists
        this.segments = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    /**
     * Getter for {@code this.stageId}
     * 
     * @return the id of the stage
     */
    public int getStageId() {
        return stageId;
    }

    /**
     * Getter for {@code this.race}
     * 
     * @return the race the stage belongs to
     * @see cycling.Race
     */
    public Race getRace() {
        return race;
    }

    /**
     * Getter for {@code this.stageName}
     * 
     * @return the name of the stage
     */
    public String getStageName() {
        return stageName;
    }

    /**
     * Getter for {@code this.description}
     * 
     * @return the description of the stage
     */
    public String getDescriptiom() {
        return description;
    }

    /**
     * Getter for {@code this.length}
     * 
     * @return the length of the stage
     */
    public double getLength() {
        return length;
    }

    /**
     * Getter for {@code this.startTime}
     * 
     * @return the time the stage will begin
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Getter for {@code this.type}
     * 
     * @return the type of the stage
     */
    public StageType getType() {
        return type;
    }

    /**
     * Getter for {@code this.segments}
     * 
     * @return a list of the segments the stage has
     * @see cycling.Segment
     */
    public ArrayList<Segment> getSegments() {
        return this.segments;
    }

    /**
     * Add a segment to the stage
     * 
     * @param segment the segment to be added to the stage
     * @see cycling.Segment
     */
    public void addSegment(Segment segment) {
        // add segment to segment array list
        this.segments.add(segment);
    }

    /**
     * Remove a segment from the stage
     * 
     * @param segment the segment to be removed from the stage
     * @see cycling.Segment
     */
    public void removeSegment(Segment segment) {
        // remove segment from segment array list
        this.segments.remove(segment);
    }

    /**
     * Getter for {@code this.stageState}
     * 
     * @return the state of the stage
     * @see cycling.StageState
     */
    public StageState getStageState() {
        return this.stageState;
    }

    /**
     * Chage the state of the stage to waiting for results.
     * Function can only be called once
     * 
     * @throws InvalidStageStateException if the function is called twice
     */
    public void concludeStagePreparation() throws InvalidStageStateException {
        // conculde stage preperation
        // if stage has allready been conculded throw error
        // check if stage type is allready waiting for results
        if (this.stageState == StageState.WAITING_FOR_RESULTS) {
            // throw InvalidStageStateException if stage state is allready waitng for results
            throw new InvalidStageStateException("Stage is allready waiting for results");
        }

        // set the stage state to waiting for resutls
        this.stageState = StageState.WAITING_FOR_RESULTS;
    }

    /**
     * add result to stage
     * 
     * @param result the result to be added
     * @see cycling.Results
     */
    public void addResults(Results result) {
        // add result to resutls array list
        results.add(result);
    }

    /**
     * getter for {@code this.results}
     * 
     * @return a list of results the stage contains
     * @see cycling.Results
     */
    public ArrayList<Results> getResults() {
        return results;
    }

    /**
     * remove result from stage
     * 
     * @param result result to be removed
     * @throws IDNotRecognisedException if the result is not in the race
     * @see cycling.Results
     */
    public void removeResults(Results result) throws IDNotRecognisedException {
        // remove result from result array list
        // check if result array contains result
        if (!results.contains(result)) {
            // if the result array does not contain result, throw an IDNotRecognisedException
            throw new IDNotRecognisedException("result does not exist in race with Id '"+stageId+"'");
        }
        // remove result
        results.remove(result);
    }

    /**
     * Calculate the number of points for position in stage.
     * Segments are not considered in this funciton
     * 
     * @param rank position rider finished in segment
     * @return points the rider gained for finishing position in stage
     */
    public int pointsForRank(int rank) {

        // return the points aquired for a riders given rank
        // switch the stage type and return the appriotiate points based on the rank and stage type
        switch (this.type) {
            case FLAT:
                return pointsForFlat(rank);
            case HIGH_MOUNTAIN:
                return pointsForHMTTIT(rank);
            case MEDIUM_MOUNTAIN:
                return pointsForMediumMountain(rank);
            case TT:
                return pointsForHMTTIT(rank);
            default:
                // if the stage type is not as above, no points were aquered and return zero
                return 0;
        }
    }

    /**
     * calculate the points for the intermiedete sprints in stage for a given rider.
     * Not including mountain points
     * 
     * @param rider rider to calulcate points for
     * @return the points the rider accumulated over the stage
     */
    public int pointsForIntermediateSprints(Rider rider) {
        // initalize points to zero
        int points = 0;

        // loop through all the segments in the stage
        for (int i = 0; i < segments.size(); i++) {
            // check if the segment is a sprint segment
            if (segments.get(i).isSprint()) {
                // create an array for all the results
                Results[] rankedResults = new Results[getResults().size()];

                // loop through all the results in the stage
                for (int x = 0; x < rankedResults.length; x++) {
                    // add the result to the results array
                    rankedResults[x] = getResults().get(x);
                }

                // sort the results array based on the elapsed time to the point
                // sort using custom comparitor ResultsSegmentTimeCompatitor
                Arrays.sort(rankedResults, new ResultsSegmentTimeCompatitor(i+1));

                // loop through all the RANKED results
                for (int x = 0; x < rankedResults.length; x++) {
                    // if the result belongs to the rider
                    if (rankedResults[x].getRider() == rider) {
                        // add the intermediat points to the sum
                        points += pointsForHMTTIT(x+1);
                        continue;
                    }
                }
            }
        }

        // return the points aquered
        return points;
    }

    /**
     * Calculate the points for the mountain segments
     * 
     * @param rider the rider to calculate the points for
     * @return the points the rider accumulated over the stage
     */
    public int pointsForMountainClassification(Rider rider) {

        // initalize points to zero
        int points = 0;

        // loop through all the segments in the stage
        for (int i = 0; i < segments.size(); i++) {
            // check if the segment is a climb
            if (segments.get(i).isClimb()) {
                // if the segment is a climb, it is safe to upcase the segment to a climbsegment
                ClimbSegment segment = (ClimbSegment)segments.get(i);

                // create a new array to store all the results in the stage
                Results[] rankedResults = new Results[getResults().size()];

                // loop through each result in the stage
                for (int x = 0; x < rankedResults.length; x++) {
                    // add the result to the list of results
                    rankedResults[x] = getResults().get(x);
                }

                // sore the ranked results using custom compatiror ResultsMountainTimeCompatoror to sort based of 
                // of the time at which the riders reached the segmenent finish
                Arrays.sort(rankedResults, new ResultsMountainTimeCompatoror(i+1));

                // loop through all the ranked results
                for (int x = 0; x < rankedResults.length; x++) {
                    // if the result belongs to the rider
                    if (rankedResults[x].getRider() == rider) {
                        // add the mountian points for that segment to the riders sum
                        points += segment.mountainPoints(x+1);
                        continue;
                    }
                }
            }
        }

        // return the points aquered
        return points;
    }

    /**
     * Calculates the points for flat finish stage
     * Data from Figure 1 in coursework spesification
     * 
     * @param rank the rank of the rider
     * @return the points the rider gets for the given rank
     */
    static public int pointsForFlat(int rank) {
        // return the points aquered for the rank and if the stage type is flat
        // add data is taken from Figure 1 in coursework spec
        switch (rank) {
        case 1:
            return 50;
        case 2:
            return 30;
        case 3:
            return 20;
        case 4:
            return 18;
        case 5:
            return 16;
        case 6:
            return 14;
        case 7:
            return 12;
        case 8:
            return 10;
        case 9:
            return 8;
        case 10:
            return 7;
        case 11:
            return 6;
        case 12:
            return 5;
        case 13:
            return 4;
        case 14:
            return 3;
        case 15:
            return 2;
        default:
            return 0;
        }
    }

    /**
     * Calculates the points for Medium Mountain finish stage
     * Data from Figure 1 in coursework spesification
     * 
     * @param rank the rank of the rider
     * @return the points the rider gets for the given rank
     */
    static public int pointsForMediumMountain(int rank) {
        // return the points aquered for the rank and if the stage type is medium
        // add data is taken from Figure 1 in coursework spec
        switch (rank) {
        case 1:
            return 30;
        case 2:
            return 25;
        case 3:
            return 22;
        case 4:
            return 19;
        case 5:
            return 17;
        case 6:
            return 15;
        case 7:
            return 13;
        case 8:
            return 11;
        case 9:
            return 9;
        case 10:
            return 7;
        case 11:
            return 6;
        case 12:
            return 5;
        case 13:
            return 4;
        case 14:
            return 3;
        case 15:
            return 2;
        default:
            return 0;
        }
    }

    /**
     * Calculates the points for High Mountain, Time Trail, Individual Trial stage
     * Data from Figure 1 in coursework spesification
     * 
     * @param rank the rank of the rider
     * @return the points the rider gets for the given rank
     */
    static public int pointsForHMTTIT(int rank) {
        // return the points aquered for the rank and if the stage type is high mountain, time trial or individual
        // trail
        // add data is taken from Figure 1 in coursework spec
        switch (rank) {
        case 1:
            return 20;
        case 2:
            return 17;
        case 3:
            return 15;
        case 4:
            return 13;
        case 5:
            return 11;
        case 6:
            return 10;
        case 7:
            return 9;
        case 8:
            return 8;
        case 9:
            return 7;
        case 10:
            return 6;
        case 11:
            return 5;
        case 12:
            return 4;
        case 13:
            return 3;
        case 14:
            return 2;
        case 15:
            return 1;
        default:
            return 0;
        }
    }

    /**
     * Rest the static counter to set the ids
     */
    public static void resetCounter() {
        // reset static counter of stage count
        stageCount = 0;
    }

}
