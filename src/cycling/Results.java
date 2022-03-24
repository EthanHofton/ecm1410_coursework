package cycling;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * Class to store result data
 * 
 * @author Ethan Hofton
 * @author Jon Tao
 * @version 1.0
 */
public class Results implements Serializable {
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
        // set class attrbutes
        this.stage = stage;
        this.rider = rider;

        // initalize times array
        this.times = new LocalTime[times.length];

        // loop through all local times in times varargs
        for (int i = 0; i < this.times.length; i++) {
            // set each time in times array
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
        // calculate the time to the last segment (elapsed time)
        return calculateTimeToSegment(times.length - 1);
    }

    /**
     * Calculate result adjusted elapsed time
     * 
     * @return the adjusted elspaed time of the result
     */
    public LocalTime calculateAdjustedElapsedTime() {
        // create an elapsed times array the size of all the result
        Results[] elapsedTimes = new Results[stage.getResults().size()];

        // loop through each result in the stage
        for (int i = 0; i < stage.getResults().size(); i++) {
            // set the result at each index
            elapsedTimes[i] = stage.getResults().get(i);
        }

        // sort array using custtom comparitor ResultsElapsedTimeComparator
        Arrays.sort(elapsedTimes, new ResultsElapsedTimeComparator());

        // create a base to store the result time
        LocalTime base = elapsedTimes[0].calculateElapsedTime();

        // loop through each result
        for (int i = 0; i < elapsedTimes.length; i++) {
            // if the current result is equal to this result, return the base reuslt
            if (elapsedTimes[i] == this) {
                return base;
            }

            // calculate the differance between the current elapsed time and the next
            Duration diff = Duration.between(elapsedTimes[i].calculateElapsedTime(), elapsedTimes[i+1].calculateElapsedTime());
            
            // check if the time differance is grater then a second
            if (diff.toMillis() > 1e+3) {
                // if the differance is grater then a second, set the base to the next elapsed time
                base = elapsedTimes[i+1].calculateElapsedTime();
            }
        }

        // if no base was returned by now, return null
        return null;
    }

    /**
     * calculates the time to given sigement
     * 
     * @param index the index of the segment in {@code times} array
     * @return the {@code LocalTime} duration between the two times
     */
    LocalTime calculateTimeToSegment(int index) {
        // calculate the duration between the first time and the time at the given index
        Duration eplapsedTime = Duration.between(times[0], times[index]);

        // return this value as a local time
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
