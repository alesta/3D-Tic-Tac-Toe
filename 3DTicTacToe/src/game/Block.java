package game;

public class Block {
	/*Coordinates are going to work as:
	x positive is going right
	y positive is going up
	z positive is going away from us
	*/
	private int x, y, z; // Coordinates of the space
	private boolean selected; // Whether this has been selected yet
	private int priority; // How we decide which block to choose
	private int team; // Which team is in control.
					  // 1 for us, 2 for them, 3 for unoccupied
	
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
	
	
}
