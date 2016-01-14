// Dilpreet Chana
// Battle.java
// Class Battle Handle battle interactions

import java.util.*;
import java.io.*;

public class Battle {
	private static Random rnd = new Random();
	private static Scanner batin = new Scanner(System.in);

	private static final boolean PLAYER = true;
	private static final boolean ENEMY = false;

	// attacked variables are true if both Pokemon have completed their attack phases and is used to determine when to reset variables like isStunned
	private static boolean playerAttacked = false;
	private static boolean enemyAttacked = false;

	private static int choice = -1;
	private static int atk_choice = -1;

	public static boolean turn = rnd.nextBoolean();

	// Present selections for next action
	public static void choose() {
		while (Battle.choice == -1) {
			Text.clear();

			Text.pokePrint("Choose an action: ");
			Text.pokePrint("1. Attack\n2. Retreat\n3. Pass\n");
			try {
				Battle.choice = Integer.parseInt(batin.nextLine());

				if (choice < 1 && choice > 3) {
					Text.pokePrint("Invalid choice, try again.");
					Text.sleep(2000);
					Text.clear();
				}
				else {
					Text.clear();
					// Valid choice has been made
				}
			}
			catch (NumberFormatException e) {
				Text.pokePrint("Invalid choice, try again.");
				Text.sleep(2000);
				Text.clear();
			}
		}
	}

	public static void execute(Pokemon player, Pokemon enemy) {
		// Player attack phase
		while (Battle.turn == Battle.PLAYER && !player.isOut() && !enemy.isOut()) {
			if (player.getStun()) {
				Text.pokePrint(player.getName() + " is stunned and cannot do anything!");
				Text.sleep(3000);
				Battle.turn = Battle.ENEMY;
				Battle.playerAttacked = true;
				Battle.choice = -1;
				break;
			}
			Battle.choose();
			if (Battle.choice == 1) {
				// Attack
				int attackSuccess = -1;  // used to determine if attack was successful
				while (attackSuccess == -1) {
					Battle.atk_choice = -1;
					Text.clear();
					while (Battle.atk_choice == -1) {
						player.displayEssentials();
						enemy.displayEssentials();

						Text.pokePrint("Choose an attack:");
						Text.pokePrint("\t0. Go Back\n");
						player.displayAttacks();
						try {
							Battle.atk_choice = Integer.parseInt(batin.nextLine());
							if (Battle.atk_choice < 0 || Battle.atk_choice > player.getNumAttacks()) {
								Battle.atk_choice = -1;
								Text.pokePrint("Invalid choice. Try again.");
								Text.sleep(2000);
							}
						}
						catch (NumberFormatException e) {
							Text.pokePrint("Invalid choice. Try again.");
							Text.sleep(2000);
						}
						Text.clear();
					}
					Battle.atk_choice--;

					// Back
					if (Battle.atk_choice == -1) {
						Battle.choice = -1;
						break;
					}
					attackSuccess = player.attack(Battle.atk_choice, enemy);

					if (attackSuccess == 1) {
						Text.pokePrint(player.getName() + " used " + player.getAttackName(Battle.atk_choice));
						Text.sleep(3000);
					}
					else {
						Text.pokePrint(player.getName() + " cannot use " + player.getAttackName(Battle.atk_choice) + "\nChoose again.");
						Text.sleep(2000);
					}
				}
				if (Battle.atk_choice == -1) {
					break;
				}
			}
			else if (Battle.choice == 2) {
				// Retreat
				Main.chooseBattlePokemon();
			}
			else if (Battle.choice == 3) {
				// Pass
				Text.pokePrint(player.getName() + " does nothing.\n");
				Text.sleep(3000);
			}
			if (Battle.atk_choice != -1) {
				Battle.turn = Battle.ENEMY;
				Battle.playerAttacked = true;
			}
			Battle.choice = -1;
		}
		player.unstun();

		// Enemy attack phase
		if (Battle.turn == Battle.ENEMY && !player.isOut() && !enemy.isOut()) {
			int attackNum, attackSuccess = -1;
			while (attackSuccess == -1) {
				Text.clear();
				if (enemy.getStun()) {
					Text.pokePrint(enemy.getName() + " is stunned!");
					Text.sleep(3000);
					break;
				}
				else if (!enemy.hasValidAttacks()) {  // Pass
					Text.pokePrint(enemy.getName() + " did nothing!");
					Text.sleep(3000);
					break;
				}
				attackNum = rnd.nextInt(enemy.getNumAttacks());
				attackSuccess = enemy.attack(attackNum, player);

				if (attackSuccess == 1) {
					Text.pokePrint(enemy.getName() + " used " + enemy.getAttackName(attackNum));
					Text.sleep(3000);
				}
			}
			Battle.turn = Battle.PLAYER;
			Battle.enemyAttacked = true;
		}
		enemy.unstun;

		// Reset Pokemon variables and recharge energy
		if (Battle.playerAttacked && Battle.enemyAttacked) {
			Main.resetTurns();
			enemy.resetTurn();

			Battle.playerAttacked = false;
			Battle.enemyAttacked = false;
		}
	}
}