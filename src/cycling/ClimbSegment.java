package cycling;

public class ClimbSegment extends Segment {

    private Double averageGradient;
    private Double length;

    public ClimbSegment(Stage stage, double location, SegmentType type, Double averageGradient, Double length) {
        super(stage, location, type);
        this.averageGradient = averageGradient;
        this.length = length;
    }

    public Double getAverageGradient() {
        return this.averageGradient;
    }

    public Double getLength() {
        return this.length;
    }

    @Override
    boolean isClimb() {
        return true;
    }

    @Override
    boolean isSprint() {
        return false;
    }

    @Override
    public String toString() {
        return "ClimbSegment[stage="+stage+",location="+location+",type="+type+",averageGradient="+averageGradient+",length="+length+"]";
    }
}
