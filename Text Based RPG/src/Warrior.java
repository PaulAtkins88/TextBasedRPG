
public class Warrior extends Player {

    public Warrior(String name, ID id, String type) {
	super(name, id, type);
	this.HP = 200;
	this.MP = 0;
	this.attack = 50;
	this.defence = 50;
	this.setTaunt("I am the strongest!");
    }

}
