package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Game {
	static ArrayList<Block> board;

	/*
	 * Heuristic (Kind of): This method will just give us an initial indicator
	 * of which blocks may be valuable choices. After this we will use alpha
	 * beta pruning to determine which option is the best.
	 */
	public static void updatePriority(ArrayList<Block> board) {
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

	public static void advancedPriority(ArrayList<Block> board) {
		updatePriority(board);
		for (Block b : board) {
			if (b.isSelected())
				return;
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
			for (int i = 0; i < 7; i++) {
				if (buffer.get(i) == 2) b.addPriority(3);
				if (buffer.get(i) == 1) b.addPriority(2);
			}
			for (int i = 7; i < 14; i++) {
				if (buffer.get(i) == 2) b.addPriority(2);
				if (buffer.get(i) == 1) b.addPriority(1);
			}
		}
	}

	public static Block selectBlock() {
		ArrayList<Block> buffer = new ArrayList<Block>();
		ArrayList<Block> topPriority = new ArrayList<Block>();
		updatePriority(board);

		buffer = Util.findLargest(board);
		if (Util.mustDo(buffer) != -1)
			return buffer.get(Util.mustDo(buffer));

		int tPS = 10; // top priority size
		if (buffer.size() < 10)
			tPS = buffer.size();
		for (int i = 0; i < tPS; i++) {
			topPriority.add(buffer.get(i));
		}

		ArrayList<Leaf> tree = new ArrayList<Leaf>();
		Util.copyBoard(board, buffer);
		for (int i = 0; i < tPS; i++) {
			tree.add(new Leaf(buffer, null, -100000, 100000, 0, buffer.get(Util
					.findBlock(topPriority.get(i).getX(), topPriority.get(i)
							.getY(), topPriority.get(i).getZ()))));
		}
		// At this point, all initial stems are generated.

		ArrayList<Leaf> minimizer = new ArrayList<Leaf>();
		for (int i = 0; i < tree.size(); i++) {
			Leaf cL = tree.get(i);
			buffer = Util.findLargest(cL.board);
			if (Util.mustDo(buffer) != -1) {
				System.out.println("MUST");
				return cL.b;
			}
			minimizer.add(new Leaf(cL.board, tree.get(i), cL.getAlpha(), cL
					.getBeta(), 1, buffer.get(0)));
			if (buffer.size() > 1)
				minimizer.add(new Leaf(cL.board, tree.get(i), cL.getAlpha(), cL
						.getBeta(), 1, buffer.get(1)));
			for (int j = 0; j < minimizer.size(); j++) {
				Leaf tip = minimizer.get(j);
				advancedPriority(minimizer.get(j).board);
				buffer = Util.findLargest(tip.board);
				if (buffer.get(0).getPriority() < tip.getBeta()) {
					tip.setBeta(buffer.get(0).getPriority());
					if (buffer.get(0).getPriority() > tip.parent.getAlpha()) {
						if (buffer.size() > 1) {
							if (buffer.get(1).getPriority() < tip.getBeta()) {
								tip.setBeta(buffer.get(1).getPriority());
							}
						}
					}
				}
				minimizer.get(j).setBeta(tip.getBeta());
				if (tip.getBeta() < cL.getAlpha())
					tree.get(i).setAlpha(tip.getBeta());
			}
		}

		Collections.sort(tree, new Comparator<Leaf>() {
			public int compare(Leaf o1, Leaf o2) {
				return o2.getAlpha() - o1.getAlpha();
			}
		});
		return tree.get(0).b;
	}

	public static void main(String args[]) {
		board = new ArrayList<Block>();
		Util.populateBoard(board);
		Util.printBoard(board);

		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		while (running) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int z = scanner.nextInt();
			Util.placeBlock(board, x, y, z, 2);
			Block b = selectBlock();
			Util.placeBlock(board, b.getX(), b.getY(), b.getZ(), 1);

			Util.printBlockXYZ("Selected: ", b);
			Util.printBoard(board);
		}
		scanner.close();
	}
}
