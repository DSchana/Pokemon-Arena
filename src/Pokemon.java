// Dilpreet Chana
// Pokemon.java
// Class Pokemon Blueprint for a Pokemon

import java.util.*;

public class Pokemon {
	private boolean koed;
	private boolean isStunned;
	private boolean wasDisabled;
	private String name;
	private Type type;
	private Type resistance;
	private Type weakness;
	private int hp;
	private int energy;
	private ArrayList<Attack> attacks = new ArrayList<Attack>();

	public Pokemon(String name, int hp, String type, String resistance, String weakness, ArrayList<Attack> attacks) {
		this.koed = false;
		this.isStunned = false;
		this.wasDisabled = false;
		this.name = name;
		this.hp = hp;
		this.resistance = Type.toType(resistance);
		this.weakness = Type.toType(weakness);
		this.type = Type.toType(type);
		this.attacks = attacks;
		this.energy = 50;
	}

	public void displayStats() {
		/* Write stats of Pokemon and draw ASCII picture of Pokemon */
		GraphicsManager.drawASCII(this.name);
		this.writeStats();
	}

	public void writeStats() {
        /* Display pokemon stats without ASCII art */
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

	public void displayEssentials() {
		Text.quickPokePrint("Name: " + this.name + "\n" +
                            "HP: " + this.hp + "\n" +
                            "Energy: " + this.energy + "\n");
	}

	public void displayAttacks() {
		for (int i = 0; i < this.attacks.size(); i++) {
            Text.quickPokePrint("\t" + (i+1) + ".\n" +
            					"\tName: " + this.attacks.get(i).getName() + "\n" +
                                "\tEnergy Cost: " + this.attacks.get(i).getEnergy() + "\n" +
                                "\tDamage: " + this.attacks.get(i).getDamage() + "\n" +
                                "\tSpecial: " + this.attacks.get(i).getSpecial() + "\n");
        }
	}

	public int attack(int attackNum, Pokemon target) {
		if (this.canDo(this.attacks.get(attackNum))) {
			this.attacks.get(attackNum).execute(target, this);
			return 1;  // Attack was successful
		}
		else {
			return -1;  // Not enough energy for the attack
		}
	}

	public boolean canDo(Attack attack) {
		return this.energy >= attack.getEnergy();
	}

	/*------- Set Methods -----------*/
	public void resetTurn() {
		this.rechargeEnergy(10);
	}

	public void rechargeEnergy(int energy) {
		this.energy += energy;
		if (this.energy > 50) {
			this.energy = 50;
		}
		else if (this.energy < 0) {
			this.energy = 0;
		}
	}

	public void takeDamage(int damage) {
		this.hp -= damage;
		if (this.hp <= 0) {
			this.hp = 0;
			this.koed = true;
		}
	}

	public void stun() {
		this.isStunned = true;
	}

	public void disable() {
		if (!this.wasDisabled) {
			for (Attack atk : this.attacks) {
				atk.reducedDamage(10);
			}
			this.wasDisabled = true;
		}
	}

	public void unstun() {
		this.isStunned = false;
	}

	/*------- Get Methods -----------*/
	public boolean isOut() {
		return this.koed;
	}

	public String getName() {
		return this.name;
	}

	public Type getType() {
		return this.type;
	}

	public Type getResistance() {
		return this.resistance;
	}

	public Type getWeakness() {
		return this.weakness;
	}

	public int getNumAttacks() {
		return this.attacks.size();
	}

	public String getAttackName(int atkNum) {
		return this.attacks.get(atkNum).getName();
	}

	public boolean getStun() {
		return this.isStunned;
	}

	public boolean getDisabled() {
		return this.wasDisabled;
	}

	public boolean hasValidAttacks() {
		/* Returns true if Pokemon has at least one valid attack */
		for (Attack atk : attacks) {
			if (this.canDo(atk)) {
				return true;
			}
		}
		return false;
	}
}
