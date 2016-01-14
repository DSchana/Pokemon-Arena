// Dilpreet Chana
// ProfOak.java
// Class ProfOak Holds "knowledge" of all the available Pokemon

import java.util.*;
import java.io.*;

public class ProfOak {
	private int num_pokemon;
	private ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
	private Random rnd_poke;

	public ProfOak() {
		try {
			rnd_poke = new Random();
			Scanner inFile = new Scanner(new BufferedReader(new FileReader("Resources/pokemon.txt")));
			num_pokemon = inFile.nextInt();
			Text.pokePrint(inFile.nextLine());  // Move to next line

			// Store pokemon into pokemon array
			for (int i = 0; i < num_pokemon; i++) {
				String[] line = inFile.nextLine().split(",", -1);
				ArrayList<Attack> attacks = new ArrayList<Attack>();

				// Load attacks from file
				for (int j = 0; j < Integer.parseInt(line[5]); j++) {
					attacks.add(new Attack(line[6 + 4 * j], Integer.parseInt(line[7 + 4 * j]), Integer.parseInt(line[8 + 4 * j]), line[9 + 4 * j]));
				}

				pokedex.add(new Pokemon(line[0], Integer.parseInt(line[1]), line[2], line[3], line[4], attacks));
			}
		}
		catch (IOException e) {
			Text.pokePrint("File not found");
		}
	}

	public Pokemon choose(int pos) {
		if (pos > this.pokedex.size()-1) {
			return null;
		}
		else {
			return pokedex.get(pos);
		}
	}

	public void remove(int pos) {
		this.remove(pokedex.get(pos));
	}

	public void remove(Pokemon poke) {
		try {
			pokedex.remove(poke);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			Text.pokePrint("No pokemon found");
		}
	}

	public Pokemon randomPokemon() {
		return pokedex.get(rnd_poke.nextInt(this.pokedex.size()));
	}

	public boolean areAllOut(ArrayList<Pokemon> pokemon) {
		/* check if group of pokemon are KOed */
		for (Pokemon poke : pokemon) {
			if (!poke.isOut()) {
				return false;
			}
		}
		return true;

	}

	public ArrayList<Pokemon> getPokedex() {
		return this.pokedex;
	}

	public String toString() {
		String ret = "";

		for (int i = 0; i < pokedex.size(); i++) {
			ret = ret + String.format("%d. %s\n", i + 1, pokedex.get(i).getName());
		}

		return ret;
	}
}