package cycling;

import java.util.Comparator;

/**
 * Results class compatotor.
 * Used to compare 2 results based on the time to segment
 * 
 * @author Ethan Hofton
 * @author Jon Tao
 * @version 1.0
 */
public class ResultsSegmentTimeCompatitor implements Comparator<Results> {
 
    private int pos;

    /**
     * Constructor for class
     * 
     * @param pos the position the segment is in the checkpoint times
     */
    public ResultsSegmentTimeCompatitor(int pos) {
        // set class attrivutes
        this.pos = pos;
    }

    /**
     * Compare 2 reuslts using {@code LocalTime.compareTo}
     * 
     * @param result1 first result to compare
     * @param result2 second result to copmare
     * @return the value of result1 - result2
     */
    @Override
    public int compare(Results result1, Results result2) {
        // compare 2 results at a cetrain position using LocalTime.compareTo and Result.calculateTimeToSegment
        return result1.calculateTimeToSegment(pos).compareTo(result2.calculateTimeToSegment(pos));
    }
}
