package Shop;
public class Armor extends Item {

    private int defence;
    public Armor(String name,int cost, int defence) {
	super(name,cost);
	this.defence = defence;
    }
    
    public int getDefence() {
	return this.defence;
    }

}
