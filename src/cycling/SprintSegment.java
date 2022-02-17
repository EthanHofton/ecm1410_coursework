package cycling;

public class SprintSegment extends Segment {

    public SprintSegment(Stage stage, double location) {
        super(stage, location, SegmentType.SPRINT);
    }

    @Override
    boolean isClimb() {
        return false;
    }

    @Override
    boolean isSprint() {
        return true;
    }

    @Override
    public String toString() {
        return "SprintSegment[stage="+stage+",location="+location+",type="+type+"]";
    }
}
