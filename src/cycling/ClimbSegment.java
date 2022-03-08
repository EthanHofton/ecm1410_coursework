package cycling;

/**
 * Class for ClimbSegemt extents {@link Segment}. Stores additional details requted if the segment is a
 * climbing segment.
 * 
 * @author Ethan Hofton
 * @atuher Jon Tau
 * @version 1.0
 * 
 */
public class ClimbSegment extends Segment {

    private Double averageGradient;
    private Double length;

    /**
     * The constructor for climb segment.
     * 
     * @param stage the stage the segment is in
     * @param location the location of the segment within the stage
     * @param type the type of segment
     * @param averageGradient average gradient of segment
     * @param length length of segment
     */
    public ClimbSegment(Stage stage, double location, SegmentType type, Double averageGradient, Double length) {
        super(stage, location, type);
        this.averageGradient = averageGradient;
        this.length = length;
    }

    /**
     * Getter for {@code this.averageGradient}
     * 
     * @return the average gradient
     */
    public Double getAverageGradient() {
        return this.averageGradient;
    }

    /**
     * Getter for {@code this.length}
     * 
     * @return the average gradient
     */
    public Double getLength() {
        return this.length;
    }

    /**
     * Returns if the segment is a climb segment.
     * Overrides {@link cycling.Segment.isClimb}
     * 
     * @return wether the segment is a climb or not
     */
    @Override
    boolean isClimb() {
        return true;
    }

    /**
     * Returns if the segment is a sprint segment.
     * Overrides {@link cycling.Segment.isSprint}
     * 
     * @return wether the segment is a sprint or not
     */
    @Override
    boolean isSprint() {
        return false;
    }

    /**
     * Class toString method
     * 
     * @return segment details in formatted string
     */
    @Override
    public String toString() {
        return "ClimbSegment[stage="+stage+",location="+location+",type="+type+",averageGradient="+averageGradient+",length="+length+"]";
    }
}
