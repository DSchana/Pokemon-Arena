// Attack.java

public class Attack {
	final private String name;
	final private int cost, damage;
	final private Special special;

	public Attack(String name, int cost, int damage, String special) {
		this.name = name;
		this.cost = cost;
		this.damage = damage;
		this.special = Special.toSpec(special);
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