// Pokemon.java

import java.util.*;

public class Pokemon {
	private boolean koed;
	private String name;
	private Type type;
	private Type resistance;
	private Type weakness;
	private int hp;
	private int energy;
	private ArrayList<Attack> attacks = new ArrayList<Attack>();

	public Pokemon(String name, int hp, String type, String resistance, String weakness, ArrayList<Attack> attacks) {
		this.koed = false;
		this.name = name;
		this.hp = hp;
		this.resistance = Type.toType(resistance);
		this.weakness = Type.toType(weakness);
		this.type = Type.toType(type);
		this.attacks = attacks;
		this.energy = 50;
	}

	public void displayStats() {
		/*Write stats of Pokemon and draw ASCII picture of Pokemon*/
		GraphicsManager.drawASCII(this.name);
		this.writeStats();
	}

	public void writeStats() {
                /*Display pokemon stats without ASCII art*/
                Text.quickPokePrint("Name: " + this.name + "\n" +
                                                        "HP: " + this.hp + "\n" +
                                                        "Type: " + this.type + "\n" +
                                                        "Resistance: " + this.resistance + "\n" +
                                                        "Weakness: " + this.weakness + "\n" +
                                                        "Attacks: " + "\n");
                for (Attack atk : this.attacks) {
                        Text.quickPokePrint("\tName: " + atk.getName() + "\n" +
                                                                "\tEnergy Cost: " + atk.getEnergy() + "\n" +
                                                                "\tDamage: " + atk.getDamage() + "\n" +
                                                                "\tSpecial: " + atk.getSpecial() + "\n");
                }
        }

	public void rechargeEnergy(int energy) {
		this.energy += energy;
		if (this.energy > 50) {
			this.energy = 50;
		}
	}

	public boolean isOut() {
		return this.koed;
	}

	public String getName() {
		return this.name;
	}
}
