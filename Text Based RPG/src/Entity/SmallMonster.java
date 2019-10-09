package Entity;
import Main.ID;

public class SmallMonster extends GameObject {

    public SmallMonster(String name, ID id) {
	super(name, id);
	this.HP = 10;
	this.damage = 20;
	this.defence = 10;
	this.hitChance = 15;
    }

}
