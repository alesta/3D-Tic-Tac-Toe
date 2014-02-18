package game;

import java.util.ArrayList;

public class Util {
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
