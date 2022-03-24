package cycling;

/**
 * extends {@link cycling.Segment}
 * A special case of {@code Segment} where the type is {@code SegmentType.SPRINT}
 * 
 * @author Ethan Hofton
 * @author Jon Tao
 * @version 1.0
 * @see cycling.Segment
 * 
 */
public class SprintSegment extends Segment {

    /**
     * SprintSegment Constructor. call super construor explisitly passing {@code type} as {@code SegmentType.SPRINT}
     * 
     * @param stage the stage the segment belongs to
     * @param location the location of the segment in the stage
     * @see cycling.Stage
     */
    public SprintSegment(Stage stage, double location) {
        // call segment constructor
        super(stage, location, SegmentType.SPRINT);
    }

    /**
     * Override of {@link cycling.Segment.isClimb} where the value is explisitly defined
     * 
     * @return false
     * @see cycling.Segment.isClimb
     */
    @Override
    boolean isClimb() {
        return false;
    }

    /**
     * Override of {@link cycling.Segment.isSprint} where the value is explisitly defined
     * 
     * @return true
     * @see cycling.Segment.isSprint
     */
    @Override
    boolean isSprint() {
        return true;
    }
}
