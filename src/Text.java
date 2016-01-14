// Dilpreet Chana
// Text.java
// Class Text Modification of the cmd output

import java.io.*;

public class Text {
	public static void clear() {
		try {
			if (System.getProperty("os.name").startsWith("Windows")) {
				// Windows
				Text.run("cls");
			}
			else {
				// Unix
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		}
		catch (Exception e) {
			// Do nothing
		}
	}

	public static void run(String cmd) {
		/* Run command in the comand prompt. Used for seting up theme */
		try {
			if (System.getProperty("os.name").startsWith("Windows")) {
				// Windows
				new ProcessBuilder("cmd", "/c", cmd).inheritIO().start().waitFor();
				Runtime.getRuntime().exec(cmd);
			}

		}
		catch (Exception e) {
			// Do nothing
		}
	}

	public static void sleep(int milliseconds) {
		/*Pause the program for time and catch any errors*/
		try {
			Thread.sleep(milliseconds);
		}
		catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void pokePrint(String str) {
		/*Side scroll text display like in pokemon*/
		for (char c : str.toCharArray()) {
			System.out.print(c);
			Text.sleep(15);
		}

		System.out.print("\n");  // Move text object to next line
	}

	public static void quickPokePrint(String str) {
		/*Faster print for large string*/
		for (char c : str.toCharArray()) {
			System.out.print(c);
			Text.sleep(1);  // Allows for smoother printing without taking much time
		}

		System.out.print("\n");  // Move text object to next line
	}
}
