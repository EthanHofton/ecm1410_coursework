package cycling;

import java.time.LocalTime;

/**
 * Class to store result data
 * 
 * @author Ethan Hofton
 * @atuher Jon Tau
 * @version 1.0
 */
public class Results {
    private Stage stage;
    private Rider rider;
    private LocalTime[] times;

    /**
     * Constructor for Result Class
     * 
     * @param stage the stage the result is from
     * @param rider the rider the result is for
     * @param times a list of varargs for the times the rider passed each checkpoint in stage
     * @see cycling.Stage
     * @see cycling.Rider 
     */
    public Results(Stage stage, Rider rider, LocalTime... times) {
        this.stage = stage;
        this.rider = rider;
        this.times = new LocalTime[times.length];

        for (int i = 0; i < this.times.length; i++) {
            this.times[i] = times[i];
        }
    }

    /**
     * Getter for {@code this.stage}
     * 
     * @return the stage the result is for
     * @see cycling.Stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Getter for {@code this.rider}
     * 
     * @return the rider the result is for
     * @see cycling.Rider
     */
    public Rider getRider() {
        return rider;
    }

    /**
     * Getter for the times of the result
     * 
     * @return a list of times that the rider passed each checkpoint in the stage
     */
    public LocalTime[] getTimes() {
        return times;
    }
}
