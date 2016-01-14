/* Dilpreet Chana
 * Attack.java
 * Class Attack: Blueprint for n attack of the Pokemon
 */

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

	/*
	 * Deal attack damage while taking into account the the types
	 * @param target Pokemon to take damage
	 * @param self	 Pokemon dealing damage
	 */
	public void execute(Pokemon target, Pokemon self) {
		/* Perform attack on target Pokemon */
		int realDamage = this.damage;  // Actual damage to set back after modifiers have taken place

		// Modify damage based on types
		if (target.getWeakness() == self.getType()) {
			this.damage *= 2;
		}
		else if (target.getResistance() == self.getType()) {
			this.damage /= 2;
		}

		if (this.special == Special.NONE) {
			target.takeDamage(this.damage);
		}

		if (this.special == Special.STUN) {
			target.takeDamage(this.damage);
			if (this.atkRnd.nextInt(2) == 0) {  // 50% chance of STUN happening
				target.stun();
				Text.pokePrint(target.getName() + " is stunned!");
			}
			else {
				Text.pokePrint(target.getName() + " was not affected!");
			}
		}

		if (this.special == Special.WILDCARD) {
			if (this.atkRnd.nextInt(2) == 0) {
				Text.pokePrint(self.getName() + " is feeling lucky!");
				target.takeDamage(this.damage);
			}
			else {
				Text.pokePrint(this.name + " missed!");
			}
		}

		if (this.special == Special.WILDSTORM) {
			if (this.atkRnd.nextInt(2) == 0) {
				Text.pokePrint(self.getName() + " is going wild!");
				target.takeDamage(this.damage);
				this.execute(target, self);
			}
			else {
				Text.pokePrint(this.name + " missed!");
			}
		}

		if (this.special == Special.DISABLE) {
			target.takeDamage(this.damage);
			if (target.getDisabled()) {
				Text.pokePrint(target.getName() + " was already disabled!");
			}
			else {
				target.disable();
				Text.pokePrint(target.getName() + " is now disabled!");
			}
		}

		if (this.special == Special.RECHARGE) {
			self.rechargeEnergy(20);
			Text.pokePrint(self.getName() + "'s energy recharged!");
			Text.sleep(3000);
		}

		// Use pokemon energy
		self.rechargeEnergy(-this.cost);
		this.damage = realDamage;
	}

	/*-------- set Methods --------*/
	public void reducedDamage(int reduction) {
		/* Reduce damage delt by attack when disabled */
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