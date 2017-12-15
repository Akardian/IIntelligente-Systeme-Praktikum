package environment;

import java.util.Arrays;

import enums.Direction;
import enums.TileType;
import exceptions.WorldToSmall;

public class World {

	private Tile[][] tile;

	private final int achseX;
	private final int achseY;

	public World(int achseX, int achseY) throws WorldToSmall {
		if (achseX >= 2 && achseY >= 1) {
			this.achseX = achseX;
			this.achseY = achseY;

			//Create World
			tile = new Tile[achseX][achseY];

			tile[0][achseY - 1] = new Tile(0, achseY - 1, TileType.START, 0);
			tile[achseX - 1][achseY - 1] = new Tile(achseX - 1, achseY - 1, TileType.END, 0);

			for (int i = 1; i < achseX - 1; i++) {
				tile[i][achseY - 1] = new Tile(i, achseY - 1, TileType.CLIFF, -100);
			}

			for (int i = 0; i < achseX; i++) {
				for (int j = 0; j < achseY - 1; j++) {
					tile[i][j] = new Tile(i, j, TileType.EMPTY, 0);
				}
			}
		} else {
			throw new WorldToSmall("ERROR: World to small.");
		}
	}

	public void print(State position) {
		for (int i = 0; i < achseY; i++) {
			for (int j = 0; j < achseX; j++) {
				if (i == position.getY() && j == position.getX()) {
					tile[j][i].printf();
				} else {
					tile[j][i].print();
				}
			}
			System.out.println();
		}
	}

	public void printPolicy(State position) {
		for (int i = 0; i < achseY; i++) {
			for (int j = 0; j < achseX; j++) {
				if (i == position.getY() && j == position.getX()) {
					tile[j][i].printfPolicy();
				} else {
					tile[j][i].printPolicy();
				}
			}
			System.out.println();
		}
	}

	public State move(Direction action, State oldPosition) {
		State newPosition = oldPosition.move(action);
		State position = oldPosition;

		if (newPosition.inBounds(achseX, achseY)) {
			position = newPosition;
		}

		return position;
	}

	public int getReward(State position) {
		return getTile(position).getReward();
	}

	public Tile[][] getTile() {
		return tile;
	}

	public Tile getTile(State position) {
		return tile[position.getX()][position.getY()];
	}

	@Override
	public String toString() {
		return "World [world=" + Arrays.toString(tile) + ", width=" + achseX + ", height=" + achseY + "]";
	}
}
