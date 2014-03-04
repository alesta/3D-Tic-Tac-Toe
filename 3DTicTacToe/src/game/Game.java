package game;

import java.util.ArrayList;

public class Game {
	static ArrayList<Block> board;
	static ArrayList<Block> tree;

	/*
	 * This method will just give us an initial indicator of which blocks may be
	 * valuable choices. After this we will use alpha beta pruning to determine
	 * which option is the best.
	 */
	void updatePriority() {
		for (Block b : board) {
			
		}
	}

	public static void main(String args[]) {
		board = new ArrayList<Block>();
		Util.populateBoard(board);
		board.get(Util.findBlock(1, 1, 1)).setTeam(1);
		Util.printBoard(board);
		/*
		 * boolean running = true; while (running) {
		 * 
		 * }
		 */
	}
}
