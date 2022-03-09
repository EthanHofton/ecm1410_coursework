package cycling;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * Class to store result data
 * 
 * @author Ethan Hofton
 * @atuher Jon Tao
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
     * Calculate result elapsed time
     * 
     * @return elapsed time of the result
     */
    public LocalTime calculateElapsedTime() {
        return calculateTimeToSegment(times.length - 1);
    }

    /**
     * Calculate result adjusted elapsed time
     * 
     * @return the adjusted elspaed time of the result
     */
    public LocalTime calculateAdjustedElapsedTime() {
        
        Results[] elapsedTimes = new Results[stage.getResults().size()];
        for (int i = 0; i < stage.getResults().size(); i++) {
            elapsedTimes[i] = stage.getResults().get(i);
        }

        Arrays.sort(elapsedTimes, new ResultsElapsedTimeComparator());

        LocalTime base = elapsedTimes[0].calculateElapsedTime();
        for (int i = 0; i < elapsedTimes.length; i++) {

            if (elapsedTimes[i] == this) {
                return base;
            }

            Duration diff = Duration.between(elapsedTimes[i].calculateElapsedTime(), elapsedTimes[i+1].calculateElapsedTime());
            if (diff.toMillis() > 1e+3) {
                base = elapsedTimes[i+1].calculateElapsedTime();
            }
        }

        return null;
    }

    /**
     * calculates the time to given sigement
     * 
     * @param index the index of the segment in {@code times} array
     * @return the {@code LocalTime} duration between the two times
     */
    LocalTime calculateTimeToSegment(int index) {
        Duration eplapsedTime = Duration.between(times[0], times[index]);
        return LocalTime.ofNanoOfDay(eplapsedTime.toNanos());
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
