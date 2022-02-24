package cycling;

import cycling.Race;
import cycling.StageType;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This enum is used to represent the state of the stage.
 * 
 * @author Ethan & Jon
 * @version 1.0
 *
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

    public int getStageId() {
        return stageId;
    }

    public Race getRace() {
        return race;
    }

    public String getStageName() {
        return stageName;
    }

    public String getDescriptiom() {
        return description;
    }

    public double getLength() {
        return length;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public StageType getType() {
        return type;
    }

    public ArrayList<Segment> getSegments() {
        return this.segments;
    }

    public void addSegment(Segment segment) {
        this.segments.add(segment);
    }

    public void removeSegment(Segment segment) {
        this.segments.remove(segment);
    }

    public StageState getStageState() {
        return this.stageState;
    }

    public void concludeStagePreparation() throws InvalidStageStateException {
        if (this.stageState == StageState.WAITING_FOR_RESULTS) {
            throw new InvalidStageStateException("Stage is allready waiting for results");
        }

        this.stageState = StageState.WAITING_FOR_RESULTS;
    }
}
