package Shop;
public class Armor extends Item {

    private int defence;
    public Armor(String name, int defence) {
	super(name);
	this.defence = defence;
    }
    
    public int getDefence() {
	return this.defence;
    }

}
