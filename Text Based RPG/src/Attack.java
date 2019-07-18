import java.util.Random;

public class Attack {

    Random rand = new Random();
    private GameObject attacker, defender;

    public Attack(GameObject attacker, GameObject defender) {

	this.attacker = attacker;
	this.defender = defender;

	int hitChance = this.attacker.hitChance;
	int defense = this.defender.defence;
	System.out.println(defender.getName() + " HP = " + defender.getHP() + "\n");
	if (rand.nextInt(100) < ((hitChance + defense) - hitChance)) {

	    this.defender.HP -= this.attacker.damage;
	    System.out.println(this.defender.getName() + " was hit for " + this.attacker.damage);

	}
    }
}