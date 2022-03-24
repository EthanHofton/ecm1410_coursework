package cycling;

import java.util.Comparator;

/**
 * compatoror for results class compare by elasped time
 * 
 * @author Ethan Hofton
 * @author Jon Tao
 * @version 1.0
 */
public class ResultsMountainTimeCompatoror implements Comparator<Results> {
    
    private int pos;

    /**
     * Constructor for class
     * 
     * @param pos the position the segment is in the checkpoint times
     */
    public ResultsMountainTimeCompatoror(int pos) {
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
        // compare 2 results at a cetrain position using LocalTime.compareTo
        return result1.getTimes()[pos].compareTo(result2.getTimes()[pos]);
    }
}