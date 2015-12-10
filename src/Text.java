// Text.java

import java.io.*;

public class Text {
	public static void clear() {
		try {
			if (System.getProperty("os.name").startsWith("Windows")) {
				// Windows
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				Runtime.getRuntime().exec("cls");
			}
			else {
				// Unix based system
				final String ANSI_CLS = "\u001b[2j";
				final String ANSI_HOME = "\u001b[H";
				System.out.print(ANSI_CLS + ANSI_HOME);
				System.out.flush();
			}
		}
		catch (IOException e) {
			// Do nothing
		}
		catch (InterruptedException e2) {
			Text.pokePrint("Process terminated");
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
		catch (IOException e) {
			// Do nothing
		}
		catch (InterruptedException e2) {
			Text.pokePrint("Process terminated");
		}
	}

	public static void sleep(int milliseconds) {
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
			sleep(10);
		}
		sleep(100);

		System.out.print("\n");
	}

	public static void quickPokePrint(String str) {
		/*Faster print for large string*/
		for (char c : str.toCharArray()) {
			System.out.print(c);
			sleep(1);
		}
		sleep(100);

		System.out.print("\n");
	}
}