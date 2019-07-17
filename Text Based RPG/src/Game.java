import java.util.Scanner;

public class Game {
    Scanner sc = new Scanner(System.in);
    Level lvl;
    Player player;

    public Game() {
	char selection = 0;
	boolean playerCreated = false;
	lvl = new Level();
	System.out.println("It was a a dark an eery night....");
	do {
	    selection = menu();
	    switch (selection) {
	    case 'S':
		if (playerCreated) {
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

	    case 'X':
		System.out.printf("\n%31s\n%40s", "Bye!", "Thanks for playing.");
		break;

	    default:
		System.out.println("Invalid Choice!");
		break;
	    }

	} while (selection != 'X');
    }

    private void startGame() {
	String decision;
	do {
	    System.out.println("GO which way? 'X' to exit:");
	    decision = sc.nextLine();
	    player.move(decision, lvl);
	    switch (lvl.position(player.getX(), player.getY())) {
	    case 0:
		System.out.println("Nothing here.");
		break;
	    case 1:
		System.out.println("Small monster here");
		break;
	    case 2:
		System.out.println("Big monster here");
		break;
	    }
	} while (decision.toUpperCase().charAt(0) != 'X');

    }

    private boolean createPlayer() {
	String name, type;
	System.out.println("Enter player name");
	name = sc.nextLine();
	do {

	    System.out.println("What type of Character?\n" + "W - Warrior\n" + "M - Mage");
	    type = sc.nextLine().toUpperCase();
	    if (type.charAt(0) == 'W')
		player = new Warrior(name, ID.Player,"Warrior");
	    else if (type.charAt(0) == 'M')
		player = new Mage(name, ID.Player,"Mage");
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
