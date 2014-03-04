package game;

public class Block {
	/*
	 * Coordinates are going to work as: x positive is going right y positive is
	 * going down z positive is going away from us
	 */
	private int x, y, z; // Coordinates of the space
	private boolean selected; // Whether this has been selected yet
	private int priority; // How we decide which block to choose
	private int team; // Which team is in control.
						// 1 for us, 2 for them, 3 for unoccupied
	private int alpha, beta; // Going to be used for alpha beta pruning

	Block(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		selected = false;
		team = 3;
		alpha = 100000;
		beta = -100000;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void addPriority(int increase) {
		this.priority += increase;
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
