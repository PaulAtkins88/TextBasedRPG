import java.util.Random;

public class Attack {

    Random rand = new Random();
    private GameObject attacker, defender;

    public Attack(GameObject attacker, GameObject defender) {

	this.attacker = attacker;
	this.defender = defender;

	int hitChance = this.attacker.hitChance;
	int defense = this.defender.hitChance;

	if (rand.nextInt(100) < (hitChance / (hitChance + defense))) {

	    this.defender.HP -= this.attacker.damage;

	}

    }

}