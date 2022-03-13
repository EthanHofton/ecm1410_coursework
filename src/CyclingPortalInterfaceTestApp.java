import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import cycling.CyclingPortal;
import cycling.DuplicatedResultException;
import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidCheckpointsException;
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
	 * @throws InvalidCheckpointsException
	 * @throws DuplicatedResultException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IllegalNameException, InvalidNameException, IllegalArgumentException, IDNotRecognisedException, InvalidLengthException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException, DuplicatedResultException, InvalidCheckpointsException, IOException, ClassNotFoundException {
		System.out.println("The system compiled and started the execution...");

        CyclingPortal portal = new CyclingPortal();
        int race1 = portal.createRace("race1", "description");

        int stage1 = portal.addStageToRace(race1, "Stage1", "Stage1", 23.1, LocalDateTime.now(), StageType.FLAT);
        int stage2 = portal.addStageToRace(race1, "Stage2", "Stage2", 23.1, LocalDateTime.now(), StageType.FLAT);
        int team1 = portal.createTeam("team1", "Test team");
        int rider1 = portal.createRider(team1, "Ethan", 2003);
        int rider2 = portal.createRider(team1, "Ethan", 2003);
        int rider3 = portal.createRider(team1, "Ethan", 2003);
        int rider4 = portal.createRider(team1, "Ethan", 2003);
        int rider5 = portal.createRider(team1, "Ethan", 2003);

        int segment1 = portal.addCategorizedClimbToStage(stage1, Double.valueOf(16), SegmentType.HC, Double.valueOf(18), Double.valueOf(5));
        int segment2 = portal.addCategorizedClimbToStage(stage1, Double.valueOf(16), SegmentType.HC, Double.valueOf(18), Double.valueOf(5));

        portal.concludeStagePreparation(stage1);
        portal.concludeStagePreparation(stage2);

        portal.registerRiderResultsInStage(stage1, rider1, LocalTime.parse("06:00:00"), LocalTime.parse("06:30:00"), LocalTime.parse("07:30:00"), LocalTime.parse("08:00:00.00"));
        portal.registerRiderResultsInStage(stage1, rider2, LocalTime.parse("06:00:00"), LocalTime.parse("06:35:00"), LocalTime.parse("07:35:00"), LocalTime.parse("08:00:00.05"));
        portal.registerRiderResultsInStage(stage1, rider3, LocalTime.parse("06:00:00"), LocalTime.parse("06:40:00"), LocalTime.parse("07:40:00"), LocalTime.parse("08:00:00.95"));
        portal.registerRiderResultsInStage(stage1, rider4, LocalTime.parse("06:00:00"), LocalTime.parse("06:45:00"), LocalTime.parse("07:45:00"), LocalTime.parse("08:00:02.00"));
        portal.registerRiderResultsInStage(stage1, rider5, LocalTime.parse("06:00:00"), LocalTime.parse("06:50:00"), LocalTime.parse("07:20:00"), LocalTime.parse("08:00:06.00"));

        portal.registerRiderResultsInStage(stage2, rider1, LocalTime.parse("06:00:00"), LocalTime.parse("08:00:10.00"));
        portal.registerRiderResultsInStage(stage2, rider2, LocalTime.parse("06:00:00"), LocalTime.parse("08:00:00.00"));
        portal.registerRiderResultsInStage(stage2, rider3, LocalTime.parse("06:00:00"), LocalTime.parse("08:00:00.95"));
        portal.registerRiderResultsInStage(stage2, rider4, LocalTime.parse("06:00:00"), LocalTime.parse("08:00:02.00"));
        portal.registerRiderResultsInStage(stage2, rider5, LocalTime.parse("06:00:00"), LocalTime.parse("08:00:06.00"));

        System.out.println(Arrays.toString(portal.getRidersRankInStage(stage2)));
        System.out.println(Arrays.toString(portal.getRankedAdjustedElapsedTimesInStage(stage2)));
        
        System.out.println(Arrays.toString(portal.getRidersGeneralClassificationRank(race1)));
	}

}
