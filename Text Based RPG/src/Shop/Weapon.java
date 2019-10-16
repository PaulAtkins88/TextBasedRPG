package Shop;

public class Weapon extends Item {
    private int damage;
    public Weapon(String name, int cost, int damage) {
	super(name,cost);
	this.damage = damage;
    }
    
    public int getDamage() {
	return this.damage;
    }
}
