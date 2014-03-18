package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Util {
	public static ArrayList<Block> findLargest(ArrayList<Block> board) {
		ArrayList<Block> topChoices = new ArrayList<Block>();
		ArrayList<Block> output = new ArrayList<Block>();
		for (int i = 0; i < board.size(); i++) {
			if (!board.get(i).isSelected())
				topChoices.add(board.get(i));
		}
		Collections.sort(topChoices, new Comparator<Block>() {
			public int compare(Block o1, Block o2) {
				return o2.getPriority() - o1.getPriority();
			}
		});
		for (int i = 0; i < topChoices.size(); i++) {
			if (topChoices.get(i).getPriority() >= 3) {
				output.add(topChoices.get(i));
				topChoices.remove(i);
			}
		}
		if (output.size() < 10)
			for (int i = 0; i < 10 - output.size(); i++) {
				output.add(topChoices.get(i));
			}
		return output;
	}

	public static int mustDo(ArrayList<Block> priority) {
		for (int i = 0; i < priority.size(); i++) {
			Block b = priority.get(i);
			if (b.fX == 3 || b.fY == 3 || b.fZ == 3 || b.fdX == 3 || b.fdY == 3
					|| b.fdZ == 3 || b.fD == 3) {
				System.out.println("WIN");
				return i;
			}
			if (b.eX == 3 || b.eY == 3 || b.eZ == 3 || b.edX == 3 || b.edY == 3
					|| b.edZ == 3 || b.eD == 3) {
				System.out.println("LOSE");
				return i;
			}
			ArrayList<Integer> buffer = new ArrayList<Integer>();
			buffer.add(b.fX);
			buffer.add(b.fY);
			buffer.add(b.fZ);
			buffer.add(b.fdX);
			buffer.add(b.fdY);
			buffer.add(b.fdZ);
			buffer.add(b.fD);
			buffer.add(b.eX);
			buffer.add(b.eY);
			buffer.add(b.eZ);
			buffer.add(b.edX);
			buffer.add(b.edY);
			buffer.add(b.edZ);
			buffer.add(b.eD);
			int counter = 0;
			for (int j = 0; j < 7; j++) {
				if (buffer.get(j) == 2) {
					if (buffer.get(j + 6) == 0)
						counter++;
				}
				if (counter == 2) {
					System.out.println("Double WIN");
					return i;
				}
			}
		}
		if (priority.size() == 1) {
			System.out.println("TROUBLE");
			return 0;
		}
		return -1;
	}

	public static void placeBlock(ArrayList<Block> board, int x, int y, int z,
			int team) {
		board.get(findBlock(x, y, z)).setTeam(team);
		board.get(findBlock(x, y, z)).setSelected(true);
	}

	public static boolean contains(int[] array, int value) {
		for (int i : array) {
			if (i == value)
				return true;
		}
		return false;
	}

	public static void resetPriority(ArrayList<Block> board) {
		for (Block b : board) {
			b.setPriority(0);
			b.fX = 0;
			b.fY = 0;
			b.fZ = 0;
			b.fdX = 0;
			b.fdY = 0;
			b.fdZ = 0;
			b.fD = 0;
			b.eX = 0;
			b.eY = 0;
			b.eZ = 0;
			b.edX = 0;
			b.edY = 0;
			b.edZ = 0;
			b.eD = 0;
		}
	}

	public static void linearUpdate(ArrayList<Block> board, Block b) {
		int x = b.getX(), y = b.getY(), z = b.getZ();
		for (int i = 0; i < 4; i++) {
			if (x != i) {
				board.get(Util.findBlock(i, b.getY(), b.getZ())).addPriority(1);
				switch (b.getTeam()) {
				case 1:
					board.get(Util.findBlock(i, b.getY(), b.getZ())).fX++;
					break;
				case 2:
					board.get(Util.findBlock(i, b.getY(), b.getZ())).eX++;
					break;
				}
			}
			if (y != i) {
				board.get(Util.findBlock(b.getX(), i, b.getZ())).addPriority(1);
				switch (b.getTeam()) {
				case 1:
					board.get(Util.findBlock(b.getX(), i, b.getZ())).fY++;
					break;
				case 2:
					board.get(Util.findBlock(b.getX(), i, b.getZ())).eY++;
					break;
				}
			}
			if (z != i) {
				board.get(Util.findBlock(b.getX(), b.getY(), i)).addPriority(1);
				switch (b.getTeam()) {
				case 1:
					board.get(Util.findBlock(b.getX(), b.getY(), i)).fZ++;
					break;
				case 2:
					board.get(Util.findBlock(b.getX(), b.getY(), i)).eZ++;
					break;
				}
			}
		}
	}

	public static void sameXDiagonal(ArrayList<Block> board, Block b) {
		int x = b.getX(), y = b.getY(), z = b.getZ();
		for (int i = 0; i < 4; i++) {
			if (y == z) {
				if (i != z) {
					board.get(Util.findBlock(x, i, i)).addPriority(1);
					switch (b.getTeam()) {
					case 1:
						board.get(Util.findBlock(x, i, i)).fdX++;
						break;
					case 2:
						board.get(Util.findBlock(x, i, i)).edX++;
						break;
					}
				}
			} else {
				if (i != z) {
					board.get(Util.findBlock(x, 3 - i, i)).addPriority(1);
					switch (b.getTeam()) {
					case 1:
						board.get(Util.findBlock(x, 3 - i, i)).fdX++;
						break;
					case 2:
						board.get(Util.findBlock(x, 3 - i, i)).edX++;
						break;
					}
				}
			}
		}
	}

	public static void sameYDiagonal(ArrayList<Block> board, Block b) {
		int x = b.getX(), y = b.getY(), z = b.getZ();
		for (int i = 0; i < 4; i++) {
			if (x == z) {
				if (i != x) {
					board.get(Util.findBlock(i, y, i)).addPriority(1);
					switch (b.getTeam()) {
					case 1:
						board.get(Util.findBlock(i, y, i)).fdY++;
						break;
					case 2:
						board.get(Util.findBlock(i, y, i)).edY++;
						break;
					}
				}
			} else {
				if (i != x) {
					board.get(Util.findBlock(i, y, 3 - i)).addPriority(1);
					switch (b.getTeam()) {
					case 1:
						board.get(Util.findBlock(i, y, 3 - i)).fdY++;
						break;
					case 2:
						board.get(Util.findBlock(i, y, 3 - i)).edY++;
						break;
					}
				}
			}
		}
	}

	public static void sameZDiagonal(ArrayList<Block> board, Block b) {
		int x = b.getX(), y = b.getY(), z = b.getZ();
		for (int i = 0; i < 4; i++) {
			if (x == y) {
				if (i != x) {
					board.get(Util.findBlock(i, i, z)).addPriority(1);
					switch (b.getTeam()) {
					case 1:
						board.get(Util.findBlock(i, i, z)).fdZ++;
						break;
					case 2:
						board.get(Util.findBlock(i, i, z)).edZ++;
						break;
					}
				}
			} else {
				if (i != x) {
					board.get(Util.findBlock(i, 3 - i, z)).addPriority(1);
					switch (b.getTeam()) {
					case 1:
						board.get(Util.findBlock(i, 3 - i, z)).fdZ++;
						break;
					case 2:
						board.get(Util.findBlock(i, 3 - i, z)).edY++;
						break;
					}
				}
			}
		}
	}

	public static void cornerDiagonal(ArrayList<Block> board, Block b) {
		int x = b.getX(), y = b.getY(), z = b.getZ();
		if (y == x && y == z) {
			for (int i = 0; i < 4; i++) {
				board.get(Util.findBlock(i, i, i)).addPriority(1);
				switch (b.getTeam()) {
				case 1:
					board.get(Util.findBlock(i, i, i)).fD++;
					break;
				case 2:
					board.get(Util.findBlock(i, i, i)).eD++;
					break;
				}
			}
		} else if (y == x && y != z) {
			for (int i = 0; i < 4; i++) {
				board.get(Util.findBlock(i, i, 3 - i)).addPriority(1);
				switch (b.getTeam()) {
				case 1:
					board.get(Util.findBlock(i, i, 3 - i)).fD++;
					break;
				case 2:
					board.get(Util.findBlock(i, i, 3 - i)).eD++;
					break;
				}
			}
		} else if (y == z && x != z) {
			for (int i = 0; i < 4; i++) {
				board.get(Util.findBlock(3 - i, i, i)).addPriority(1);
				switch (b.getTeam()) {
				case 1:
					board.get(Util.findBlock(3 - i, i, i)).fD++;
					break;
				case 2:
					board.get(Util.findBlock(3 - i, i, i)).eD++;
					break;
				}
			}
		} else if (x == z && y != z) {
			for (int i = 0; i < 4; i++) {
				board.get(Util.findBlock(3 - i, i, 3 - i)).addPriority(1);
				switch (b.getTeam()) {
				case 1:
					board.get(Util.findBlock(3 - i, i, 3 - i)).fD++;
					break;
				case 2:
					board.get(Util.findBlock(3 - i, i, 3 - i)).eD++;
					break;
				}
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
			board2.add(new Block(b.getX(), b.getY(), b.getZ()));
	}

	// Prints Out Our Board
	public static void printBoard(ArrayList<Block> board) {
		System.out.println("Board");
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
		System.out.println("Priorities");
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

	public static void printBlockXYZ(String identifier, Block b) {
		System.out.println(identifier + b.getX() + ", " + b.getY() + ", "
				+ b.getZ());
	}

	public static void printBlockList(ArrayList<Block> list) {
		for (int i = 0; i < list.size(); i++)
			printBlockXYZ(i + ": ", list.get(i));
	}

	public static boolean checkGameOver(ArrayList<Block> board) {
		for (Block b : board) {
			if (b.fX == 4 || b.fY == 4 || b.fZ == 4 || b.fdX == 4 || b.fdY == 4
					|| b.fdZ == 4 || b.fD == 4 || b.eX == 4 || b.eY == 4
					|| b.eZ == 4 || b.edX == 4 || b.edY == 4 || b.edZ == 4
					|| b.eD == 4)
				return true;
		}
		return false;
	}
}
