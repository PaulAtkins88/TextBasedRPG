package Main;
import java.util.Random;
import java.util.Scanner;

import Entity.Attack;
import Entity.BigMonster;
import Entity.GameObject;
import Entity.Mage;
import Entity.Player;
import Entity.SmallMonster;
import Entity.Warrior;
import Shop.Shop;

public class Game {
    Scanner sc = new Scanner(System.in);
    Level lvl;
    Player player;

    Random rand = new Random();

    private final String[] SMALL_MONSTER = { "goblin", "ewok", "gremlin" };
    private final String[] BIG_MONSTER = { "Dragon", "Thanos", "Mr Squiggle" };
    private static Shop shop = new Shop();
    
    public static final int WIDTH = 80, HEIGHT = 40;

    public Game() {
	char selection = 0;
	boolean playerCreated = false;
	
	System.out.println("It was a a dark an eery night....");
	do {
	    selection = menu();
	    switch (selection) {
	    case 'S':
		if (playerCreated) {
		    createBoard(); // ask the player what size board to play on
		    startGame();
		} else {
		    System.out.println("Create a character first.");
		}
		break;

	    case 'C':
		playerCreated = createPlayer();
		if (playerCreated) {
		    System.out.println(player.taunt());
		} else
		    System.out.println("Something went wrong, try again.");		
		break;

	    case 'V':
		if (playerCreated)
		    player.printDetails();
		else
		    System.out.println("Create a character first.");
		break;
	    case '!':
		lvl.printBoard();
		printBoardStats();
		break;
	    case 'X':
		System.out.printf("\n%31s\n%40s", "Bye!", "Thanks for playing.");
		break;

	    default:
		System.out.println("Invalid Choice!");
		break;
	    }

	} while (selection != 'X');
    }

    private void createBoard() {
	int width = 0, height = 0;

	System.out.println("\nEnter the width of the board: ");
	width = sc.nextInt();
	sc.nextLine();
	System.out.println("\nEnter the height of the board: ");
	height = sc.nextInt();
	sc.nextLine();
	lvl = new Level(width, height);

    }

    private void printBoardStats() {
	int sCount = 0, bCount = 0, nCount = 0, check;

	for (int y = 0; y < lvl.getHeight(); y++) {
	    for (int x = 0; x < lvl.getWidth(); x++) {
		check = lvl.position(y, x);
		if (check == 0)
		    nCount++;
		else if (check == 1)
		    sCount++;
		else if (check == 2)
		    bCount++;
	    }
	}

	System.out.printf("\n%-30s%10s %10s", "Small monsters on board", ":", sCount);
	System.out.printf("\n%-30s%10s %10s", "Big monsters on board", ":", bCount);
	System.out.printf("\n%-30s%10s %10s", "Black spaces on board", ":", nCount);

    }

    private void startGame() {
	String decision, monster;

	do {
	    System.out.println("GO which way? 'D' to display location, 'X' to exit:\n");
	    decision = sc.nextLine();
	    if (decision.toUpperCase().charAt(0) == 'D')
		System.out.printf("%s's location is %d, %d\n", player.getName(), player.getX(), player.getY());

	    // first if statement checks the input was valid
	    if (validDirection(decision.toUpperCase())) {

		// if the input was valid move the player
		player.move(decision, lvl);

		// if the input is not 'X' tell the player what is at the level position
		if (decision.toUpperCase().charAt(0) != 'X' && decision.toUpperCase().charAt(0) != 'D') {
		    switch (lvl.position(player.getX(), player.getY())) {
		    case 0:
			System.out.println("You have walked " + decision + " but there is nothing here...");
			break;
		    case 1:
			monster = SMALL_MONSTER[rand.nextInt(3)];
			System.out.println("You have walked " + decision + " and before you stands a " + monster);
			battle(1, monster);
			break;
		    case 2:
			monster = BIG_MONSTER[rand.nextInt(3)];
			System.out.println("You have walked " + decision + " and before you stands a " + monster);
			battle(2, monster);
			break;
		    case 9:
			shop.runShop(player);
		    default:
			System.out.println("Exiting to Main Menu");
		    }
		}
	    } else
		System.out.println("Error, Enter North, South, East, West or X");

	} while (decision.toUpperCase().charAt(0) != 'X' && !player.isDead);
    }

    private boolean validDirection(String direction) {
	if (direction.equals("NORTH") || direction.equals("SOUTH") || direction.equals("EAST")
		|| direction.equals("WEST") || direction.charAt(0) == 'X' || direction.charAt(0) == 'D')
	    return true;
	else
	    return false;
    }

    private void battle(int i, String name) {
	// what type of monster to create based on what is passed to the method
	// 1 for small monster, 2 for big monster

	// VERY MUCH A WORK IN PROGRESS! FIRST TIME WORKING OUT A RANDOM HIT CHANCE
	// BATTLE
	Attack attack;
	int action = 0;
	GameObject monster = null;

	switch (i) {
	case 1:
	    monster = new SmallMonster(name, ID.SmallMonster);
	    break;
	case 2:
	    monster = new BigMonster(name, ID.BigMonster);
	    break;
	default:
	    break;
	}
	if (monster != null) {
	    do {

		attack = new Attack(monster, player);

		System.out.println("What do you want to do?\n" + "1 - Attack\n" + "3 - Run!\n");
		action = sc.nextInt();
		sc.nextLine();
		if (action == 1) {
		    attack = new Attack(player, monster);
		}

		if (player.getHP() <= 0) {
		    player.isDead = true;
		    System.out.println(player.getName() + " is Dead.\n\nGAME OVER!");
		} else if (monster.getHP() <= 0) {
		    monster.isDead = true;
		    System.out.println(monster.getName() + " is Dead.");
		    lvl.clearMonster(player.getX(), player.getY());
		}

	    } while (action != 3 && !player.isDead && !monster.isDead);
	}
    }

    private boolean createPlayer() {
	String name, type;
	System.out.println("Enter player name");
	name = sc.nextLine();
	do {

	    System.out.println("What type of Character?\n" + "W - Warrior\n" + "M - Mage");
	    type = sc.nextLine().toUpperCase();
	    if (type.charAt(0) == 'W')
		player = new Warrior(name, ID.Player, "Warrior");
	    else if (type.charAt(0) == 'M')
		player = new Mage(name, ID.Player, "Mage");
	    else
		System.out.println("Error - Select W/M: ");
	} while (player == null);

	return true;
    }

    private char menu() {
	char selection = 0;
	System.out.printf("\n\n%30s\n", "Menu");
	for (int i = 0; i < 50; i++)
	    System.out.print("-");
	System.out.printf("\n%15s%15s%-20s\n\n", "S", " ", "Start Game");
	System.out.printf("%15s%15s%-20s\n\n", "C", " ", "Create Character");
	System.out.printf("%15s%15s%-20s\n\n", "V", " ", "View Character");
	System.out.printf("%15s%15s%-20s\n\n", "!", " ", "View Board Stats");
	System.out.printf("%15s%15s%-20s\n\n", "X", " ", "Quit Game");
	for (int i = 0; i < 50; i++)
	    System.out.print("-");
	System.out.printf("\n%30s:", "Enter choice");
	selection = sc.nextLine().toUpperCase().charAt(0);
	return selection;
    }

    public static void main(String[] args) {
	new Game();
    }

}
