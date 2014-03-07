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
	public static void updatePriority() {
		int index = 0;
		Util.resetPriority(board);
		for (Block b : board) {
			if (b.isSelected()) {
				Util.linearUpdate(board, b);
				if (Util.contains(Block.edge, index)) {
					int x = b.getX();
					int y = b.getY();
					int z = b.getZ();
					switch (y % 3) {
					case 0:
						if (x % 3 == 0)
							Util.sameZDiagonal(board, b);
						else
							Util.sameXDiagonal(board, b);
						break;
					default:
						Util.sameYDiagonal(board, b);
						break;
					}
				} else if (Util.contains(Block.outerCenter, index)) {
					int x = b.getX();
					int y = b.getY();
					int z = b.getZ();
					switch (y % 3) {
					case 0:
						Util.sameYDiagonal(board, b);
						break;
					default:
						if (x % 3 == 0)
							Util.sameXDiagonal(board, b);
						else
							Util.sameZDiagonal(board, b);
						break;
					}
				} else {
					Util.sameXDiagonal(board, b);
					Util.sameYDiagonal(board, b);
					Util.sameZDiagonal(board, b);
					Util.cornerDiagonal(board, b);
				}
			}
			index++;
		}
	}

	public static void main(String args[]) {
		board = new ArrayList<Block>();
		Util.populateBoard(board);
		board.get(21).setSelected(true);
		updatePriority();
		Util.printPriorities(board);
		/*
		 * boolean running = true; while (running) {
		 * 
		 * }
		 */
	}
}
