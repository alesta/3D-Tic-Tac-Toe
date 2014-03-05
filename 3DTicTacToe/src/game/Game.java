package game;

import java.util.ArrayList;

public class Game {
	static ArrayList<Block> board;
	static ArrayList<Block> tree;

	/*
	 * Heuristic (Kind of): This method will just give us an initial indicator
	 * of which blocks may be valuable choices. After this we will use alpha
	 * beta pruning to determine which option is the best.
	 */
	void updatePriority() {
		int index = 0;
		Util.resetPriority(board);
		for (Block b : board) {
			if (b.isSelected()) {
				for (int i = b.getX(); i < 4; i++) {

				}
				for (int i = b.getY(); i < 4; i++) {

				}
				for (int i = b.getZ(); i < 4; i++) {

				}
				if (index == 0 || index == 3 || index == 12 || index == 15
						|| index == 48 || index == 51 || index == 60
						|| index == 63) {
					
				}
					index++;
			}
		}
	}

	public static void main(String args[]) {
		board = new ArrayList<Block>();
		Util.populateBoard(board);
		board.get(Util.findBlock(1, 1, 1)).setTeam(1);
		Util.printBoard(board);
		boolean running = true;
		while (running) {

		}
	}
}
