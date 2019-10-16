package Shop;

public class Item {

    private String name;
    private int cost;
    
    public Item(String name, int cost) {
	this.name = name;
	this.cost = cost;
    }
    
    public String getName() {
	return this.name;
    }
    
    public int getCost() {
	return this.cost;
    }
}
