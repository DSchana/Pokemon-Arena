// Main.java

import java.io.*;
import java.util.*;

public class Main {
	/*------- Variables ----------*/
	static Scanner stdin = new Scanner(System.in);
	static ProfOak prof = new ProfOak();

	static ArrayList<Pokemon> user_poke = new ArrayList<Pokemon>();
	static Pokemon choice;  // Holds user's pokemon choice while program determines if it is valid
	static Pokemon rocket;  // Bad guys pokemon (team rocket)

	static String userIn;  // Hold all of the user's inputs for analysis
	static int userInt;  // Hold all of the user's integers for analysis
	/*----------------------------*/

	public static void showPokemon() {
		/* Display pokemon the user has selected and whether they are able to fight */

		for (int i = 0; i < user_poke.size(); i++) {
			String out = i+1 + ". " + user_poke.get(i).getName();
			if (!user_poke.get(i).isOut()) {
				out = out + " (Ready)";
			}
			else {
				out = out + " (KO)";
			}
			Text.pokePrint(out);
		}
	}

	public static void main(String[] args) {
		/*-------- Setup shutdown procedure --------------*/
		Main runtimeCheck = new Main();
		runtimeCheck.attachShutDownHook();

		/*------------------ Cool Intro ---------------------*/
		Text.clear();
		Text.run("color E");  // Change colour to yellow for Pokemon. Only temperary
		GraphicsManager.drawASCII("logo");

		Text.pokePrint("\nPress the enter key to continue...");
		stdin.nextLine();

		Text.clear();
		// End cool intro

		Text.pokePrint("Welcome to Pokemon arena \nHere you will fight with Pokemon!\n\nEnter to continue...");
		stdin.nextLine();
		Text.clear();

		/*--------- Player chooses Pokemon ------------*/
		Text.pokePrint("Choose four Pokemon by number\n");
		while (user_poke.size() < 4) {
			Text.pokePrint("Choose a Pokemon:\n");
			Text.quickPokePrint(prof.toString());
			try {
				choice = prof.choose(stdin.nextInt()-1);
			}
			catch (InputMismatchException e) {
				stdin.nextLine();  // Advance Scanner to next line, readying it for new input
				// The message will be displayed during the validity check
			}
			Text.clear();

			// determine if choice is valid
			if (choice != null) {
				// Is valid
				Text.pokePrint("You've chosen:\n");
				choice.displayStats();
				Text.pokePrint("Are you sure? [y/n]");

				stdin.nextLine();  // Reset stdin to take string after int. Doesn't work without
				userIn = stdin.nextLine();
				if (userIn.toLowerCase().equals("y")) {
					user_poke.add(choice);
					Text.pokePrint("Selection complete!");
					prof.remove(choice);
					Text.sleep(1000);  // Give user time to read
				}
				else {
					// Do nothing
				}
			}
			else {
				// Is not valid
				Text.pokePrint("Not a valid Pokemon, choose again");
				Text.sleep(1000);
			}
			Text.clear();
		}

		Text.pokePrint("Here is your team!\n");
		showPokemon();

		Text.pokePrint("\nPress the enter key to battle...");
		stdin.nextLine();

		/*------------- Battle Setup -------------------*/
		while (!prof.areAllOut(user_poke) && !prof.areAllOut(prof.getPokedex())) {
			choice = null;
			rocket = prof.randomPokemon();

			Text.pokePrint("Team rocket chooses:\n\n");
			rocket.displayStats();

			while (choice == null) {
				Text.pokePrint("Choose your Pokemon (stat # for Pokemon statistics):\n");
				showPokemon();

				userIn = stdin.nextLine();
				try {
					userInt = Integer.parseInt(userIn) - 1;
				}
				catch (NumberFormatException e) {
					if (userIn.contains("stat ") && userIn.length() == 6) {
						userInt = Character.getNumericValue(userIn.charAt(5)) + 3;
					}
					else {
						userInt = Integer.MAX_VALUE;
						// The error message will be displayed in the if statement
					}
				}

				Text.clear();

				if (userInt < 4 && !user_poke.get(userInt).isOut()) {
					choice = user_poke.get(userInt);
				}
				else if (userInt >= 4 && userInt < 8) {
					Text.pokePrint(rocket.getName() + ":\n");
					rocket.writeStats();

					Text.pokePrint("\n" +  user_poke.get(userInt-4).getName() + ":\n");
					user_poke.get(userInt-4).writeStats();
				}
				else {
					Text.pokePrint("Invalid choice, try again");
					Text.sleep(1000);
				}
			}
			Text.clear();
			Text.pokePrint(choice.getName() + ", I choose you!");
		}

		/* ------------ Clean Up -----------------*/
		Text.run("color F");  // Reset cmd color to white

		System.exit(0);
	}

	public void attachShutDownHook(){
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
	    		Text.pokePrint("Pokemon Arena is cleaning up and shutting down...");
	    		Text.run("color F");
	   		}
	  	});
	 }
}
