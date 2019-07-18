
public class Player extends GameObject {
    private String type;
    private int x,y;
    public Player(String name, ID id,String type) {
	super(name, id);	
	this.x = 0;
	this.y = 0;
	this.hitChance = 5;
    }
    
    public boolean move(String direction, Level lvl) {
	switch (direction.toUpperCase()) {
	case "NORTH":
	    if (this.y > 0) {
		this.y--;
		System.out.println("new player position: " + this.x + "," + this.y);
	    } else {
		System.out.println("cant go that way");
	    }
	    break;
	case "SOUTH":
	    if (this.y < lvl.getBoard().length - 1) {
		this.y++;
		System.out.println("new player position: " + this.x + "," + this.y);
	    } else {
		System.out.println("cant go that way");
	    }
	    break;
	case "EAST":
	    if (this.x < lvl.getBoard().length - 1) {
		this.x++;
		System.out.println("new player position: " + this.x + "," + this.y);
	    } else {
		System.out.println("cant go that way");
	    }
	    break;
	case "WEST":
	    if (this.x > 0) {
		this.x--;
		System.out.println("new player position: " + this.x + "," + this.y);
	    } else {
		System.out.println("cant go that way");
	    }
	    break;
	}
	return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void printDetails() {
	System.out.println();
	for (int i = 0; i < 50; i++)
	    System.out.print("-");
	System.out.printf("\n%20s %-30s\n", "Name:", this.name);
	System.out.printf("%20s %-30s\n", "Type:",this.type); // TODO: prints null? needs fixing
	System.out.printf("%20s %-30s\n", "HP:", this.HP);
	System.out.printf("%20s %-30s\n", "MP:", this.MP);
	System.out.printf("%20s %-30s\n", "Attack:", this.damage);
	System.out.printf("%20s %-30s\n", "Defence:", this.defence);
	for (int i = 0; i < 50; i++)
	    System.out.print("-");
    }

}
