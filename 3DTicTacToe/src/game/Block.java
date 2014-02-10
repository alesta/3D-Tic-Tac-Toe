package game;

public class Block {
	private int x, y, z;
	private boolean selected;
	private int team;
	
	Block(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
	
	
}
