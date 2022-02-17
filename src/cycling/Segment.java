package cycling;

public class Segment {
    protected static int segmentCount;
    protected int segmentId;
    protected Stage stage;
    protected double location;
    protected SegmentType type;

    public Segment(Stage stage, double location, SegmentType type){
        this.segmentId = segmentCount++;
        this.stage = stage;
        this.location = location;
        this.type = type;
    }

    public int getSegmentId() {
        return segmentId;
    }

    public Stage getStage() {
        return stage;
    }

    public double getLocation() {
        return location;
    }

    public SegmentType getType() {
        return type;
    }

    boolean isClimb() {
        switch (type) {
            case C1:
            case C2:
            case C3:
            case C4:
            case HC:
                return true;
            case SPRINT:
                return false;
        }
        return false;
    }

    boolean isSprint() {
        return !isClimb();
    }

    public String toString() {
        return "Segment[stage="+stage+",location="+location+",type="+type+"]";
    }
}
