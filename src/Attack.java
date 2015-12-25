// Attack.java

import java.util.*;

public class Attack {
	final private String name;
	final private int cost;
	private int damage;
	final private Special special;
	private Random atkRnd = new Random();

	public Attack(String name, int cost, int damage, String special) {
		this.name = name;
		this.cost = cost;
		this.damage = damage;
		this.special = Special.toSpec(special);
	}

	public void execute(Pokemon target, Pokemon self) {
		/* Perform attack on target Pokemon */
		String copmare = Special.toString(this.special);  // String of enum to compare

		if (this.special.equals(Special.toString(Special.NONE))) {
			target.takeDamage(this.damage);
		}

		if (this.special.equals(Special.toString(Special.STUN)) && this.atkRnd.nextInt(9) % 2 == 0) {  // 50% chance of STUN happening
			target.stun();
		}

		if (this.special.equals(Special.toString(Special.WILDCARD))) {
			if (this.atkRnd.nextInt(9) % 2 == 0) {
				target.takeDamage(this.damage);
			}
			else {
				Text.pokePrint(this.name + " missed!");
			}
		}

		if (this.special.equals(Special.toString(Special.WILDSTORM))) {
			if (this.atkRnd.nextInt(9) % 2 == 0) {
				target.takeDamage(this.damage);
				this.execute(target, self);
			}
			else {
				Text.pokePrint(this.name + " missed!");
			}
		}

		if (this.special.equals(Special.toString(Special.DISABLE))) {
			target.disable();
		}

		if (this.special.equals(Special.toString(Special.RECHARGE))) {
			self.rechargeEnergy(20);
		}

		// Use pokemon energy
		self.rechargeEnergy(-this.cost);
	}

	/*-------- set Methods --------*/
	public void reducedDamage(int reduction) {
		this.damage -= reduction;
		if (this.damage < 0) {
			this.damage = 0;
		}
	}

	/*-------- Get Methods --------*/
	public String getName() {
		return this.name;
	}

	public int getEnergy() {
		return this.cost;
	}

	public int getDamage() {
		return this.damage;
	}

	public Special getSpecial() {
		return this.special;
	}
}