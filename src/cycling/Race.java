package cycling;

import java.util.ArrayList;

/**
 * Race class to store the race id and addtional details relevent
 * to the race
 * 
 * @author Ethan Hofton
 * @atuher Jon Tau
 * @version 1.0
 */
public class Race {
    private static int raceCount = 0;

    private int raceId;
    private String name;
    private String description;
    private ArrayList<Stage> stages;

    private ArrayList<Results> results;
    
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
        this.results = new ArrayList<>();
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
     * add result to race
     * 
     * @param result the result to be added
     * @see cycling.Results
     */
    public void addResults(Results result) {
        results.add(result);
    }

    /**
     * getter for {@code this.results}
     * 
     * @return a list of results the race contains
     * @see cycling.Results
     */
    public ArrayList<Results> getResults() {
        return results;
    }

    /**
     * remove result from race
     * 
     * @param result result to be removed
     * @throws IDNotRecognisedException if the result is not in the race
     * @see cycling.Results
     */
    public void removeResults(Results result) throws IDNotRecognisedException {
        if (!results.contains(result)) {
            throw new IDNotRecognisedException("result does not exist in race with Id '"+raceId+"'");
        }
        results.remove(result);
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
