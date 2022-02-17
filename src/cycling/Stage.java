package cycling;

import cycling.Race;
import cycling.StageType;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Stage {
    private static int stageCount = 0;
    private int stageId;
    private Race race;
    private String stageName;
    private String description;
    private double length; // in KM
    private LocalDateTime startTime;
    private StageType type;
    
    public Stage(Race race, String stageName, String description, double length, LocalDateTime startTime, StageType type) {
        this.stageId = stageCount++;
        this.race = race;
        this.stageName = stageName;
        this.description = description;
        this.length = length;
        this.startTime = startTime;
        this.type = type;
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

}
