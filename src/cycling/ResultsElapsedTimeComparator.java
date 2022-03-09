package cycling;

import java.util.Comparator;

/**
 * compatoror for results class compare by elasped time
 * 
 * @author Ethan Hofton
 * @author Jon Tao
 * @version 1.0
 */
public class ResultsElapsedTimeComparator implements Comparator<Results> {
    
    /**
     * Compare 2 reuslts using {@code LocalTime.compareTo}
     * 
     * @param result1 first result to compare
     * @param result2 second result to copmare
     * @return the value of result1 - result2
     */
    @Override
    public int compare(Results result1, Results result2) {
        return result1.calculateElapsedTime().compareTo(result2.calculateElapsedTime());
    }
}