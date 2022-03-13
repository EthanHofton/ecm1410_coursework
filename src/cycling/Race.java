package cycling;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Race class to store the race id and addtional details relevent
 * to the race
 * 
 * @author Ethan Hofton
 * @atuher Jon Tao
 * @version 1.0
 */
public class Race implements Serializable {
    private static int raceCount = 0;

    private int raceId;
    private String name;
    private String description;
    private ArrayList<Stage> stages;
    
    /**
     * Race class constructor
     * 
     * @param name the name of the race
     * @param description the description of the race
     */
    public Race(String name, String description) { 
        this.raceId = raceCount++;
        this.name = name;
        this.description = description;
        this.stages = new ArrayList<>();
    }

    /**
     * getter for {@code this.raceId}
     * 
     * @return the id of the race
     */
    public int getRaceId() {
        return raceId;
    }

    /**
     * getter for {@code this.name}
     * 
     * @return the name of the race
     */
    public String getName() {
        return name;
    }

    /**
     * getter for {@code this.description}
     * 
     * @return the description of the race
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter for {@code this.stages}
     * 
     * @return the list of stages in the race
     * @see cycling.Stage
     */
    public ArrayList<Stage> getStages() {
        return stages;
    }

    /**
     * adds a stage to the race
     * 
     * @param stage the stage class to be added to the race
     * @see cycling.Stage
     */
    public void addStage(Stage stage) {
        stages.add(stage);
    }

    /**
     * remove stage from race
     * 
     * @param stage the stage class to be removed from the race
     * @throws IDNotRecognisedException if the stage is not in the race
     * @see cycling.Stage
     */
    public void removeStage(Stage stage) throws IDNotRecognisedException {
        if (!stages.contains(stage)) {
            throw new IDNotRecognisedException("stage does not exist in race with Id '"+raceId+"'");
        }
        stages.remove(stage);
    }

    /**
     * check if the race contains a given stage
     * 
     * @param stage the stage to be checked
     * @return boolean wether the race contains the stage
     * @see cycling.Stage
     */
    public boolean containsStage(Stage stage) {
        return stages.contains(stage);
    }

    /**
     * Rest the static counter to set the ids
     */
    public static void resetCounter() {
        raceCount = 0;
    }

    /**
     * Class toString
     * 
     * @return a formatted string with class detials
     */
    public String toString() {
        return "Race[raceId="+raceId+"name="+name+",description="+description+"]";
    }
}
