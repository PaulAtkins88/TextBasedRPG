import java.util.Random;

public class Level {
    private int[][] board;
    Random rand;

    public Level() {
	// create a 10x10 board and randomise whats in each element between 0-3
	rand = new Random();
	board = new int[10][10];
	
	// 0,0 is TOP LEFT!
	
	for (int y = 1; y < 10; y++) {
	    for (int x = 1; x < 10; x++) {
		board[y][x] = rand.nextInt(3);
	    }
	}	
    }

    public int[][] getBoard() {
	return board;
    }
    
    public int position(int x, int y) {
	return board[y][x];
    }

    public void printBoard() {
	for (int y = 0; y < 10; y++) {
	    for (int x = 0; x < 10; x++) {
		System.out.print(board[y][x] + " ");
	    }
	    System.out.println();
	}
    }
}
