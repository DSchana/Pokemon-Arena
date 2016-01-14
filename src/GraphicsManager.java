// Dilpreet Chana
// GraphicsManager.java
// Class GraphicsManager Used to "render" ASCII images

import java.util.*;
import java.io.*;

public class GraphicsManager {
	static private Scanner inFile;

	public static void drawASCII(String name) {
		try {
			inFile = new Scanner(new BufferedReader(new FileReader("Resources/art/" + name + ".txt")));
			String pFile = "";
			while (inFile.hasNextLine()) {
				pFile = pFile + inFile.nextLine() + "\n";
			}
			Text.quickPokePrint(pFile);
			inFile.close();
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}