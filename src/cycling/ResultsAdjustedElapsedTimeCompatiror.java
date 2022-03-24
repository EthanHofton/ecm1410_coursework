package cycling;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.Map;

/**
 * compatoror for results class compare by adjusted elasped time
 * 
 * @author Ethan Hofton
 * @author Jon Tao
 * @version 1.0
 */
public class ResultsAdjustedElapsedTimeCompatiror implements Comparator<Map.Entry<Rider,LocalTime>> {
    /**
     * Compare 2 reuslts using {@code LocalTime.compareTo}
     * 
     * @param result1 first result to compare
     * @param result2 second result to copmare
     * @return the value of result1 - result2
     */
    @Override
    public int compare(Map.Entry<Rider,LocalTime> result1, Map.Entry<Rider,LocalTime> result2) {
        // compare the value of each entry using LocalTime.compareTo
        return result1.getValue().compareTo(result2.getValue());
    }
}
