package game;

import java.util.ArrayList;

public class Util {
	public static boolean contains(int[] array, int value) {
		for (int i : array) {
			if (i == value)
				return true;
		}
		return false;
	}

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

	public static void linearUpdate(ArrayList<Block> board, Block b) {
		int x = b.getX(), y = b.getY(), z = b.getZ();
		for (int i = 0; i < 4; i++) {
			if (x != i)
				board.get(Util.findBlock(i, b.getY(), b.getZ())).addPriority(1);
			if (y != i)
				board.get(Util.findBlock(b.getX(), i, b.getZ())).addPriority(1);
			if (z != i)
				board.get(Util.findBlock(b.getX(), b.getY(), i)).addPriority(1);
		}
	}

	public static void sameXDiagonal(ArrayList<Block> board, Block b) {
		int x = b.getX(), y = b.getY(), z = b.getZ();
		for (int i = 0; i < 4; i++) {
			if (y == z) {
				if (i != z)
					board.get(Util.findBlock(x, i, i)).addPriority(1);
			} else {
				if (i != z)
					board.get(Util.findBlock(x, 3 - i, i)).addPriority(1);
			}
		}
	}

	public static void sameYDiagonal(ArrayList<Block> board, Block b) {
		int x = b.getX(), y = b.getY(), z = b.getZ();
		for (int i = 0; i < 4; i++) {
			if (x == z) {
				if (i != x)
					board.get(Util.findBlock(i, y, i)).addPriority(1);
			} else {
				if (i != x)
					board.get(Util.findBlock(i, y, 3 - i)).addPriority(1);
			}
		}
	}

	public static void sameZDiagonal(ArrayList<Block> board, Block b) {
		int x = b.getX(), y = b.getY(), z = b.getZ();
		for (int i = 0; i < 4; i++) {
			if (x == y) {
				if (i != x)
					board.get(Util.findBlock(i, i, z)).addPriority(1);
			} else {
				if (i != x)
					board.get(Util.findBlock(i, 3 - i, z)).addPriority(1);
			}
		}
	}

	public static void cornerDiagonal(ArrayList<Block> board, Block b) {
		int x = b.getX(), y = b.getY(), z = b.getZ();
		if (y == x && y == z) {
			for (int i = 0; i < 4; i++) {
				board.get(Util.findBlock(i, i, i)).addPriority(1);
			}
		} else if (y == x && y != z) {
			for (int i = 0; i < 4; i++) {
				board.get(Util.findBlock(i, i, 3 - i)).addPriority(1);
			}
		} else if (y == z && x != z) {
			for (int i = 0; i < 4; i++) {
				board.get(Util.findBlock(3 - i, i, i)).addPriority(1);
			}
		} else if (x == z && y != z) {
			for (int i = 0; i < 4; i++) {
				board.get(Util.findBlock(3 - i, i, 3 - i)).addPriority(1);
			}
		}
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

	public static void printPriorities(ArrayList<Block> board) {
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
				row1 += b.getPriority() + "";
				break;
			case 1:
				row2 += b.getPriority() + "";
				break;
			case 2:
				row3 += b.getPriority() + "";
				break;
			case 3:
				row4 += b.getPriority() + "";
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
