package environment;

import java.util.Arrays;

public class World {

	private Tile[][] world;
	
	private final int width;
	private final int height;
	
	public World (int width, int height) {
		this.width = width;
		this.height = height;
		
		world = new Tile[width][height];
	}
	
	public void createWorld() {
		world[0][height-1] = new Tile(0, height-1, TileType.START);
		world[width-1][height-1] = new Tile(width-1, height-1, TileType.END);
		
		for (int i = 1; i < width-1; i++) {
			world[i][height-1] = new Tile(i, height-1, TileType.CLIFF);
		}
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height-1; j++) {
				world[i][j] = new Tile(i, j, TileType.EMPTY);
			}
		}
	}
	
	public void print() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				world[j][i].print();
			}
			System.out.println();
		}
	}

	@Override
	public String toString() {
		return "World [world=" + Arrays.toString(world) + ", width=" + width + ", height=" + height + "]";
	}
}

