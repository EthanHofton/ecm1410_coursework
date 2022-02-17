import java.time.LocalDateTime;
import java.util.Arrays;

import cycling.CyclingPortal;
import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidLengthException;
import cycling.InvalidNameException;
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
	 */
	public static void main(String[] args) throws IllegalNameException, InvalidNameException, IllegalArgumentException, IDNotRecognisedException, InvalidLengthException {
		System.out.println("The system compiled and started the execution...");

        CyclingPortal portal = new CyclingPortal();
        int race1 = portal.createRace("race1", "description");

        int stage1 = portal.addStageToRace(race1, "Stage1", "Stage1", 23.1, LocalDateTime.now(), StageType.FLAT);
        int stage2 = portal.addStageToRace(race1, "Stage2", "Stage2", 19.3, LocalDateTime.now(), StageType.HIGH_MOUNTAIN);

        System.out.println(portal.viewRaceDetails(race1));

		// assert (portal.getRaceIds().length == 0)
		// 		: "Innitial SocialMediaPlatform not empty as required or not returning an empty array.";

	}

}
