package environment;

import enums.Direction;

public class State {

	private final int x;
	private final int y;

	public State(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean inBounds(int achseX, int achseY) {
		boolean inBounds = false;

		if (x >= 0 && x < achseX && y >= 0 && y < achseY) {
			inBounds = true;
		}

		return inBounds;
	}

	public State move(Direction direction) {
		State position;
		if (direction == Direction.NORTH) { // Move East
			position = new State(x, y - 1);
		} else if (direction == Direction.EAST_) { // Move South
			position = new State(x + 1, y);
		} else if (direction == Direction.SOUTH) { // Move West
			position = new State(x, y + 1);
		} else if (direction == Direction.WEST_) { // Move North
			position = new State(x - 1, y);
		} else {
			position = null;
			System.out.println("ERROR: right()");
		}
		return position;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

}
