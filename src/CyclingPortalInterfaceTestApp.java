import java.util.Arrays;

import cycling.CyclingPortal;
import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidNameException;

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
	public static void main(String[] args) throws IllegalNameException, InvalidNameException, IllegalArgumentException, IDNotRecognisedException {
		System.out.println("The system compiled and started the execution...");

        CyclingPortal portal = new CyclingPortal();

        int team = portal.createTeam("ej", "Ethan and Jon");
        int ethan = portal.createRider(team, "Ethan", 2003);
        int jon = portal.createRider(team, "Jon", 2003);
        portal.removeRider(ethan);
        portal.removeRider(jon);
        System.out.println(Arrays.toString(portal.getTeamRiders(team)));

        int team2 = portal.createTeam("2", "2");
        portal.createRider(team2, "Tim", 2003);
        portal.createRider(team2, "Anna", 2003);
        System.out.println(Arrays.toString(portal.getTeamRiders(team2)));

		// assert (portal.getRaceIds().length == 0)
		// 		: "Innitial SocialMediaPlatform not empty as required or not returning an empty array.";

	}

}
