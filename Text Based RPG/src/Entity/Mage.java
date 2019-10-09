package Entity;
import Main.ID;

public class Mage extends Player {

    public Mage(String name, ID id, String type) {
	super(name, id, type);
	this.HP = 100;
	this.MP = 200;
	this.damage = 75;
	this.defence = 25;
	this.hitChance = 25;
	this.setTaunt("Dormanu, I've come to bargin.");
    }

}
