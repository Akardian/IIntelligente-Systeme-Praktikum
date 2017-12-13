package environment;

public class Position {

	private int x;
	private int y;

	public Position(int x, int y) {
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Position move(Direction direction) {
		Position position;
		if (direction == Direction.NORTH) { // Move East
			position = new Position(x, y - 1);
		} else if (direction == Direction.EAST_) { // Move South
			position = new Position(x + 1, y);
		} else if (direction == Direction.SOUTH) { // Move West
			position = new Position(x, y + 1);
		} else if (direction == Direction.WEST_) { // Move North
			position = new Position(x - 1, y);
		} else {
			position = null;
			System.out.println("ERROR: right()");
		}
		return position;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

}
