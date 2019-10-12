package Shop;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Entity.Player;
import Main.Game;

public class Shop {

    private HashMap<Integer, Item> items = new HashMap<Integer, Item>();
    private int[][] stock;
    private int[][] cost;
    private boolean debug = false;
    private int itemID;
    private Scanner sc = new Scanner(System.in);

    public Shop() {
	stock = new int[10][1];
	cost = new int[10][1];
	try {
	    loadItems();
	    if (debug) {
		debug();
	    }
	} catch (ShopException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private void loadItems() throws ShopException {
	try {
	    Scanner filein = new Scanner(new FileReader("res/items.txt"));
	    do {
		String item = filein.nextLine();
		String[] splitItem = item.split(" ");
		// splitItem[0] = type
		// splitItem[1] = name
		// splitItem[2] = points
		// splitItem[3] = amountOfStock
		// splitItem[4] = cost

		if (splitItem[0].equals("Weapon")) {
		    items.put(itemID, new Weapon(splitItem[1], Integer.parseInt(splitItem[2])));
		    stock[itemID][0] = Integer.parseInt(splitItem[3]);
		    cost[itemID][0] = Integer.parseInt(splitItem[4]);
		    itemID++;
		} else if (splitItem[0].equals("Armor")) {
		    items.put(itemID, new Armor(splitItem[1], Integer.parseInt(splitItem[2])));
		    stock[itemID][0] = Integer.parseInt(splitItem[3]);
		    cost[itemID][0] = Integer.parseInt(splitItem[4]);
		    itemID++;
		} else {
		    throw new ShopException("Data corrupt.");
		}

	    } while (filein.hasNextLine());
	    filein.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

    public void runShop(Player player) {
	String choice;
	do {
	    // Title
	    printLine(Game.WIDTH);
	    System.out.printf("%-20s%-15s%-10s%-15s%20s\n", "|", " ", "SHOP", " ", "|");
	    printLine(Game.WIDTH);

	    // Content
	    // itemId Description Attributes Cost

	    // print weapons
	    printLine(Game.WIDTH / 2);
	    System.out.printf("%-15s%-10s%15s\n", "|", "Weapons", "|");
	    printLine(Game.WIDTH / 2);
	    printHeader();
	    for (int i = 0; i < itemID; i++) {
		if (items.get(i) instanceof Weapon) {
		    System.out.printf("%-5s%-15s%-25s%-10s%-10s%10s%5s\n", "|", i, items.get(i).getName(),
			    ((Weapon) items.get(i)).getDamage(), stock[i][0], cost[i][0], "|");
		}
	    }
	    printLine(Game.WIDTH);

	    // print armor
	    printLine(Game.WIDTH / 2);
	    System.out.printf("%-15s%-10s%15s\n", "|", "Armor", "|");
	    printLine(Game.WIDTH / 2);
	    printHeader();
	    for (int i = 0; i < itemID; i++) {
		if (items.get(i) instanceof Armor) {
		    System.out.printf("%-5s%-15s%-25s%-10s%-10s%10s%5s\n", "|", i, items.get(i).getName(),
			    ((Armor) items.get(i)).getDefence(), stock[i][0], cost[i][0], "|");
		}
	    }
	    printLine(Game.WIDTH);

	    // get choice
	    System.out.println("Which item? X to exit to Shop");
	    choice = sc.nextLine();
	    if (validChoice(choice) && choice.toUpperCase().charAt(0) != 'X') {
		// TODO: Purchasing works, needs a coin system now.
		buy(player,choice);
		System.out.println("Press enter to continue.");
		sc.nextLine();
	    }

	} while (choice.toUpperCase().charAt(0) != 'X');
    }

    private void buy(Player player, String choice) {
	Item tmp = items.get(Integer.parseInt(choice));
	System.out.println("Purchasing - " + tmp.getName());
	player.addItem(tmp);	
    }

    private boolean validChoice(String choice) {
	if (choice.toUpperCase().charAt(0) == 'X') {
	    return true;
	} else if (Integer.parseInt(choice) < items.size()) {
	    return true;
	} else
	    return false;
	
    }

    private void debug() {
	for (int i = 0; i < items.size(); i++)
	    System.out.println(items.get(i).getClass().getName() + ", " + items.get(i).getName() + ". in stock = "
		    + stock[i][0] + ". Cost is = " + cost[i][0] + "\n");

    }

    private void printLine(int width) {
	for (int i = 0; i < width; i++)
	    System.out.printf("%s", "=");
	System.out.println();
    }

    private void printHeader() {
	printLine(Game.WIDTH);
	System.out.printf("%-5s%-15s%-25s%-10s%-10s%10s%5s\n", "|", "ItemID", "Description", "Points", "Stock", "Cost",
		"|");
	printLine(Game.WIDTH);

    }

}
