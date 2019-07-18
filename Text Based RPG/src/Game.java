import java.util.Random;
import java.util.Scanner;

public class Game {
    Scanner sc = new Scanner(System.in);
    Level lvl;
    Player player;

    Random rand = new Random();

    private final String[] SMALL_MONSTER = { "goblin", "ewok", "gremlin" };
    private final String[] BIG_MONSTER = { "Dragon", "Thanos", "Mr Squiggle" };

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
	String decision, monster;
	do {
	    System.out.println("GO which way? 'X' to exit:");
	    decision = sc.nextLine();

	    // first if statement checks the input was valid
	    if (validDirection(decision.toUpperCase())) {

		// if the input was valid move the player
		player.move(decision, lvl);

		// if the input is not 'X' tell the player what is at the level position
		if (decision.toUpperCase().charAt(0) != 'X') {
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
		    default:
			System.out.println("Exiting to Main Menu");
		    }
		}
	    } else
		System.out.println("Error, Enter North, South, East, West or X");

	} while (decision.toUpperCase().charAt(0) != 'X');
    }

    private boolean validDirection(String direction) {
	if (direction.equals("NORTH") || direction.equals("SOUTH") || direction.equals("EAST")
		|| direction.equals("WEST") || direction.charAt(0) == 'X')
	    return true;
	else
	    return false;
    }

    private void battle(int i, String name) {
	// what type of monster to create based on what is passed to the method
	// 1 for small monster, 2 for big monster

	// VERY MUCH A WORK IN PROGRESS! FIRST TIME WORKING OUT A RANDOM HIT CHANCE
	// BATTLE
	int action = 0;
	boolean playerDead = false;
	boolean monsterDead = false;

	switch (i) {
	case 1:
	    SmallMonster sm = new SmallMonster(name, ID.SmallMonster);
	    do {
		if (rand.nextInt(sm.hitChance) < sm.hitChance) {
		    int damage = sm.damage *= (sm.damage - player.defence);
		    if (damage < 0)
			damage = 0;
		    System.out.println(sm.getName() + " attacks for " + damage);
		    player.HP -= damage;
		    System.out.println(player.name + " HP = " + player.HP);
		}
		System.out.println("What do you want to do?\n " + "1 - Attack!\n" + "3 - Run!");
		action = sc.nextInt();
		sc.nextLine();
		if (action == 1) {
		    if (rand.nextInt(player.hitChance) < player.hitChance) {
			int damage = player.damage *= (player.damage - sm.defence);
			System.out.println(player.getName() + " attacks for " + damage);
			sm.HP -= damage;
			System.out.println(sm.name + " HP = " + sm.HP);
		    }
		}
		if (player.HP <= 0) {
		    playerDead = true;
		    System.out.println(player.getName() + " is Dead.");
		} else if (sm.HP <= 0) {
		    monsterDead = true;
		    System.out.println(sm.getName() + " is Dead.");
		}

	    } while (action != 3 && !playerDead && !monsterDead);
	    break;
	case 2:
	    break;
	default:
	    break;
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
