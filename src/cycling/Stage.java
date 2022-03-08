package cycling;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Stage class to store stage id and data related to stage
 * 
 * @author Ethan Hofton
 * @author Jon Tau
 * @version 1.0
 */
public class Stage {
    private static int stageCount = 0;
    private int stageId;
    private Race race;
    private String stageName;
    private String description;
    private double length; // in KM
    private LocalDateTime startTime;
    private StageType type;
    private StageState stageState;

    ArrayList<Segment> segments;
    
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
        this.stageId = stageCount++;
        this.race = race;
        this.stageName = stageName;
        this.description = description;
        this.length = length;
        this.startTime = startTime;
        this.type = type;
        this.stageState = StageState.STAGE_PREPERATION;

        segments = new ArrayList<>();
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
        this.segments.add(segment);
    }

    /**
     * Remove a segment from the stage
     * 
     * @param segment the segment to be removed from the stage
     * @see cycling.Segment
     */
    public void removeSegment(Segment segment) {
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
        if (this.stageState == StageState.WAITING_FOR_RESULTS) {
            throw new InvalidStageStateException("Stage is allready waiting for results");
        }

        this.stageState = StageState.WAITING_FOR_RESULTS;
    }
}
