import java.time.LocalDateTime;
import java.util.Arrays;

import cycling.CyclingPortal;
import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidLengthException;
import cycling.InvalidLocationException;
import cycling.InvalidNameException;
import cycling.InvalidStageStateException;
import cycling.InvalidStageTypeException;
import cycling.SegmentType;
import cycling.StageType;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the CyclingPortalInterface interface -- note you
 * will want to increase these checks, and run it on your CyclingPortal class
 * (not the BadCyclingPortal class).
 *
 * 
 * @author Diogo Pacheco
 * @version 1.0
 */
public class CyclingPortalInterfaceTestApp {

	/**
	 * Test method.
	 * 
	 * @param args not used
	 * @throws InvalidNameException
	 * @throws IllegalNameException
	 * @throws IDNotRecognisedException
	 * @throws IllegalArgumentException
	 * @throws InvalidStageTypeException
	 * @throws InvalidStageStateException
	 * @throws InvalidLocationException
	 */
	public static void main(String[] args) throws IllegalNameException, InvalidNameException, IllegalArgumentException, IDNotRecognisedException, InvalidLengthException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		System.out.println("The system compiled and started the execution...");

        CyclingPortal portal = new CyclingPortal();
        int race1 = portal.createRace("race1", "description");

        int stage1 = portal.addStageToRace(race1, "Stage1", "Stage1", 23.1, LocalDateTime.now(), StageType.FLAT);

        int segment1 = portal.addCategorizedClimbToStage(stage1, Double.valueOf(12), SegmentType.C1, Double.valueOf(24), Double.valueOf(12));


	}

}
