package game;

public class Block {
	/*
	 * Coordinates are going to work as: x positive is going right y positive is
	 * going down z positive is going away from us
	 */
	public static int corners[] = { 0, 3, 12, 15, 48, 51, 60, 63 };
	public static int edge[] = { 1, 2, 4, 7, 8, 11, 13, 14, 16, 19, 28, 31, 32,
			35, 44, 47, 49, 50, 52, 55, 56, 59, 61, 62 };
	public static int outerCenter[] = { 5, 6, 9, 10, 17, 18, 20, 23, 24, 27,
			29, 30, 33, 34, 36, 39, 40, 43, 45, 46, 53, 54, 57, 58 };
	public static int innerCenter[] = { 21, 22, 25, 26, 37, 38, 41, 42 };

	private int x, y, z; // Coordinates of the space
	private boolean selected; // Whether this has been selected yet
	private int priority; // How we decide which block to choose
	private int team; // Which team is in control.
						// 1 for us, 2 for them, 3 for unoccupied
	private int additionalPriority; // Based on "must-do" moves

	public int eX = 0, eY = 0, eZ = 0, edX = 0, edY = 0, edZ = 0, eD = 0;
	public int fX = 0, fY = 0, fZ = 0, fdX = 0, fdY = 0, fdZ = 0, fD = 0;

	Block(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		selected = false;
		team = 3;
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

	public int getAdditionalPriority() {
		return additionalPriority;
	}

	public void setAdditionalPriority(int additionalPriority) {
		this.additionalPriority = additionalPriority;
	}

}
