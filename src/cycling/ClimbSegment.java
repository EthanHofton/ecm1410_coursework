package cycling;

/**
 * Class for ClimbSegemt extents {@link Segment}. Stores additional details requted if the segment is a
 * climbing segment.
 * 
 * @author Ethan Hofton
 * @atuher Jon Tao
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
     * Calculates the points mountain points for the segment
     * Data from Figure 2 in coursework spesification
     * 
     * @param rank the rank of the rider
     * @return the points the rider gets for the given rank
     */
    public int mountainPoints(int rank) {
        switch (type) {
            case C1:
                return pointsFor1C(rank);
            case C2:
                return pointsFor2C(rank);
            case C3:
                return pointsFor3C(rank);
            case C4:
                return pointsFor4C(rank);
            case HC:
                return pointsForHC(rank);
            default:
                return 0;
        }
    }

    /**
     * Calculates the points for HC Mountain segment
     * Data from Figure 2 in coursework spesification
     * 
     * @param rank the rank of the rider
     * @return the points the rider gets for the given rank
     */
    static public int pointsForHC(int rank) {
        switch (rank) {
        case 1:
            return 20;
        case 2:
            return 15;
        case 3:
            return 12;
        case 4:
            return 10;
        case 5:
            return 8;
        case 6:
            return 6;
        case 7:
            return 4;
        case 8:
            return 2;
        default:
            return 0;
        }
    }

    /**
     * Calculates the points for 1C Mountain segment
     * Data from Figure 2 in coursework spesification
     * 
     * @param rank the rank of the rider
     * @return the points the rider gets for the given rank
     */
    static public int pointsFor1C(int rank) {
        switch (rank) {
        case 1:
            return 10;
        case 2:
            return 8;
        case 3:
            return 6;
        case 4:
            return 4;
        case 5:
            return 2;
        case 6:
            return 1;
        default:
            return 0;
        }
    }

    /**
     * Calculates the points for 2C Mountain segment
     * Data from Figure 2 in coursework spesification
     * 
     * @param rank the rank of the rider
     * @return the points the rider gets for the given rank
     */
    static public int pointsFor2C(int rank) {
        switch (rank) {
        case 1:
            return 5;
        case 2:
            return 3;
        case 3:
            return 2;
        case 4:
            return 1;
        default:
            return 0;
        }
    }

    /**
     * Calculates the points for 3C Mountain segment
     * Data from Figure 2 in coursework spesification
     * 
     * @param rank the rank of the rider
     * @return the points the rider gets for the given rank
     */
    static public int pointsFor3C(int rank) {
        switch (rank) {
        case 1:
            return 2;
        case 2:
            return 1;
        default:
            return 0;
        }
    }

    /**
     * Calculates the points for 4C Mountain segment
     * Data from Figure 2 in coursework spesification
     * 
     * @param rank the rank of the rider
     * @return the points the rider gets for the given rank
     */
    static public int pointsFor4C(int rank) {
        switch (rank) {
        case 1:
            return 1;
        default:
            return 0;
        }
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
