package Entity;

import java.util.ArrayList;

import Main.ID;
import Main.Level;
import Shop.Armor;
import Shop.Item;
import Shop.Weapon;

public class Player extends GameObject {
    private String type;
    private ArrayList<Item> inventory;
    private int x, y;
    private int gold;

    public Player(String name, ID id, String type) {
	super(name, id);
	this.x = 0;
	this.y = 0;
	this.type = type;
	this.hitChance = 5;

	this.gold = 0;
	this.inventory = new ArrayList<Item>();
    }
    
    public void setGold(int amount) {
	this.gold = amount;
    }
    
    public int getGold() {
	return this.gold;
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

    public void addItem(Item item) {
	this.inventory.add(item);
	if (item instanceof Armor) {
	    this.defence += ((Armor) item).getDefence();
	} else if (item instanceof Weapon) {
	    this.damage += ((Weapon) item).getDamage();
	}
    }

    public ArrayList<Item> getItems() {
	return inventory;
    }

    private void drawLine() {
	for (int i = 0; i < 50; i++)
	    System.out.print("-");
    }

    public void printDetails() {
	System.out.println();
	drawLine();
	System.out.printf("\n%20s %-30s\n", "Name:", this.name);
	System.out.printf("%20s %-30s\n", "Type:", this.type);
	System.out.printf("%20s %-30s\n", "HP:", this.HP);
	System.out.printf("%20s %-30s\n", "MP:", this.MP);
	System.out.printf("%20s %-30s\n", "Attack:", this.damage);
	System.out.printf("%20s %-30s\n", "Defence:", this.defence);
	drawLine();
	System.out.println("\nInventory\n");
	drawLine();
	System.out.println("\nArmor");
	System.out.printf("%-40s%-15s\n", "Name", "Defence Points");
	for (Item items : inventory) {
	    if (items instanceof Armor)
		System.out.printf("%-40s%-15d\n", items.getName(), ((Armor) items).getDefence());
	}
	drawLine();
	System.out.println("\nWeapons");
	System.out.printf("%-40s%-15s\n", "Name", "Attack Points");
	for (Item items : inventory) {
	    if (items instanceof Weapon)
		System.out.printf("%-40s%-15d\n", items.getName(), ((Weapon) items).getDamage());
	}
    }

}
