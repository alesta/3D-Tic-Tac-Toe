package game;

import java.util.ArrayList;

public class Leaf {
	ArrayList<Block> board;
	private int alpha, beta;
	int type; // 0 for maximizer, 1 for minimizer
	Leaf parent; // If it has a parent
	Block b; // Placed block

	public Leaf(ArrayList<Block> board, Leaf parent, int alpha, int beta, int type, Block b) {
		this.board = board;
		this.alpha = alpha;
		this.beta = beta;
		this.b = b;
		Util.resetPriority(this.board);
		if (parent != null) this.parent = parent;
	}

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public int getBeta() {
		return beta;
	}

	public void setBeta(int beta) {
		this.beta = beta;
	}
}
