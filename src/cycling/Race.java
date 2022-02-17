package cycling;

import java.util.ArrayList;

public class Race {
    private static int raceCount = 0;

    private int raceId;
    private String name;
    private String description;
    private ArrayList<Stage> stages;
    
    public Race(String name, String description) { 
        this.raceId = raceCount++;
        this.name = name;
        this.description = description;
        this.stages = new ArrayList<>();
    }

    public int getRaceId() {
        return raceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public void addStage(Stage stage) {
        stages.add(stage);
    }

    public void removeStage(Stage stage) throws IDNotRecognisedException {
        if (!stages.contains(stage)) {
            throw new IDNotRecognisedException("stage does not exist in race with Id '"+raceId+"'");
        }
        stages.remove(stage);
    }

    public boolean containsStage(Stage stage) {
        return stages.contains(stage);
    }

    // TODO Add list of stages to the string
    public String toString() {
        return "Race[raceId="+raceId+"name="+name+",description="+description+"]";
    }
}
