package game;

import java.util.ArrayList;

public class Game {
	static ArrayList<Block> board;
	
	void updatePriority() {
		for (Block b : board) {
			
		}
	}
	
	public static void main(String args[]) {
		board = new ArrayList<Block>();
		Util.populateBoard(board);
		board.get(0).setTeam(1);
		Util.printBoard(board);
		/*boolean running = true;
		while (running) {
			
		}*/
	}
}
