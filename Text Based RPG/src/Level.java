import java.util.Random;

public class Level {
    private int[][] board;
    private Random rand;
    private int width,height;

    public Level(int width, int height) {
	this.width = width;
	this.height = height;
	// create a 10x10 board and randomise whats in each element between 0-3
	rand = new Random();
	board = new int[height][width];
	
	// 0,0 is TOP LEFT!
	
	for (int y = 1; y < this.height; y++) {
	    for (int x = 1; x < this.width; x++) {
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
    
    public void clearMonster(int x, int y) {
	this.board[y][x] = 0;
    }

    public void printBoard() {
	for (int y = 0; y < this.height; y++) {
	    for (int x = 0; x < this.width; x++) {
		System.out.print(board[y][x] + " ");
	    }
	    System.out.println();
	}
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
