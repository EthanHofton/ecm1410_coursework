package cycling;

import java.io.Serializable;

/**
 * Segment class. Stores information common to both 
 * climb segemnts and sprint segments
 * 
 * @auther Ethan Hofton
 * @auther Jon Tao
 * @version 1.0
 */
public class Segment implements Serializable {
    protected static int segmentCount;
    protected int segmentId;
    protected Stage stage;
    protected double location;
    protected SegmentType type;

    /**
     * Segment constructor
     * 
     * @param stage the stage the segment belongs to
     * @param location the location of the segment within the stage
     * @param type the type of the segment
     * @see cycling.Stage
     * @see cycling.SegmentType
     */
    public Segment(Stage stage, double location, SegmentType type){
        this.segmentId = segmentCount++;
        this.stage = stage;
        this.location = location;
        this.type = type;
    }

    /**
     * Getter for {@code this.segmentId}
     * 
     * @return the id for the segment
     */
    public int getSegmentId() {
        return segmentId;
    }

    /**
     * Getter for {@code this.stage}
     * 
     * @return the stage the segment belongs to
     * @see cycling.Stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Getter for {@code this.location}
     * 
     * @return location of the segment within the stage
     */
    public double getLocation() {
        return location;
    }

    /**
     * Getter for {@code this.type}
     * 
     * @return the type of segment
     * @see cycling.SegmentType
     */
    public SegmentType getType() {
        return type;
    }

    /**
     * Check wither the segment is a climb or not
     * 
     * @return boolean of wether the segment is a climb or not
     */
    boolean isClimb() {
        return !isSprint();
    }

    /**
     * Check wither the segment is a sprint or not
     * 
     * @return boolean of wether the segment is a sprint or not
     */
    boolean isSprint() {
        return type == SegmentType.SPRINT;
    }

    /**
     * Rest the static counter to set the ids
     */
    public static void resetCounter() {
        segmentCount = 0;
    }

    /**
     * toString of Segment
     * 
     * @return formatted string containg relavent segment data
     */
    public String toString() {
        return "Segment[stage="+stage+",location="+location+",type="+type+"]";
    }
}
