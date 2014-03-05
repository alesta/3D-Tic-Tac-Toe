package game;

import java.util.ArrayList;

public class Util {
	public static void resetPriority(ArrayList<Block> board) {
		for (Block b : board)
			b.setPriority(0);
	}

	/*
	 * Compares Priorities Had to do this since Java can't let you edit
	 * operators such as >, <, and ==
	 */
	public static boolean higherPriority(Block a, Block b) {
		if (a.getPriority() > b.getPriority())
			return true;
		return false;
	}

	// Initially Populates the Board
	public static void populateBoard(ArrayList<Block> board) {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				for (int z = 0; z < 4; z++) {
					board.add(new Block(x, y, z));
				}
			}
		}
	}

	// Finds index of block on the board
	public static int findBlock(int x, int y, int z) {
		return z + (4 * x) + (16 * y);
	}

	// Copies a board of Blocks
	public static void copyBoard(ArrayList<Block> board1,
			ArrayList<Block> board2) {
		board2.clear();
		for (Block b : board1)
			board2.add(b);
	}

	// Prints Out Our Board
	public static void printBoard(ArrayList<Block> board) {
		String row1 = "", row2 = "", row3 = "", row4 = "";
		for (Block b : board) {
			if (b.getX() == 0 && b.getZ() == 0) {
				System.out.println(row4);
				System.out.println(row3);
				System.out.println(row2);
				System.out.println(row1);
				System.out.println("");
				row4 = "";
				row3 = "";
				row2 = "";
				row1 = "";
			}
			switch (b.getZ()) {
			case 0:
				row1 += b.getTeam() + "";
				break;
			case 1:
				row2 += b.getTeam() + "";
				break;
			case 2:
				row3 += b.getTeam() + "";
				break;
			case 3:
				row4 += b.getTeam() + "";
				break;
			}
		}
		System.out.println(row4);
		System.out.println(row3);
		System.out.println(row2);
		System.out.println(row1);
		System.out.println("");
		row4 = "";
		row3 = "";
		row2 = "";
		row1 = "";
	}
}
