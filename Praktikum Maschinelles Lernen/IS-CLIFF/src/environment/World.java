package environment;

import java.util.Arrays;
import java.util.Random;

import exceptions.MoveToFar;
import exceptions.WorldToSmall;

public class World {

	private Tile[][] tile;	
	private Position startPossition;
	//0 > Sum < 100  
	private final int moveForward = 100;
	private final int moveRight = 0;
	private final int moveLeft = 0;
	private final int moveBackwards = 0;
	//
	private final int achseX;
	private final int achseY;
	
	public World(int achseX, int achseY, int rewardBad, int rewardGood) throws WorldToSmall {
		if (achseX >= 2 && achseY >= 1) {
			this.achseX = achseX;
			this.achseY = achseY;

			createWorld(rewardBad, rewardGood);
			startPossition = new Position(0, achseY-1);
		} else {
			throw new WorldToSmall("ERROR: World to small.");
		}
	}

	private void createWorld( int rewardBad, int rewardGood) {
		tile = new Tile[achseX][achseY];

		tile[0][achseY - 1] = new Tile(0, achseY - 1, TileType.START, 0);
		tile[achseX - 1][achseY - 1] = new Tile(achseX - 1, achseY - 1, TileType.END, rewardGood);

		for (int i = 1; i < achseX - 1; i++) {
			tile[i][achseY - 1] = new Tile(i, achseY - 1, TileType.CLIFF, rewardBad);
		}

		for (int i = 0; i < achseX; i++) {
			for (int j = 0; j < achseY - 1; j++) {
				tile[i][j] = new Tile(i, j, TileType.EMPTY, 0);
			}
		}
	}

	public void print(Position position) {
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
	
	private Position moveRandom(Position oldPosition,Position newPosition) {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		
		Position position;
		
		if(randomInt < moveForward) {
			position = newPosition;
		} else if (randomInt < moveForward + moveRight) {
			position = new Turn(oldPosition,newPosition).right();
		} else if(randomInt < moveForward + moveRight +moveLeft) {
			position = new Turn(oldPosition,newPosition).left();
		} else if(randomInt < moveForward + moveRight +moveLeft +moveBackwards){
			position = new Turn(oldPosition,newPosition).backwards();
		} else {
			position = null;
			System.out.println("ERROR: moveRandom");
		}
		
		return position;
	}
	
	public Position move(Action action, Position oldPosition) throws MoveToFar {
		Position newPosition = oldPosition.move(action.getDirection());
		
		if(Math.abs(newPosition.getX() - oldPosition.getX()) == 1 || Math.abs(newPosition.getY() - oldPosition.getY()) == 1) {
			Position moveToPosition = moveRandom(oldPosition, newPosition);
			Position position = oldPosition;
			
			if(moveToPosition.inBounds(achseX, achseY) ) {				
				position = moveToPosition;
				if(tile[moveToPosition.getX()][moveToPosition.getY()].getType() == TileType.CLIFF) {
					//position = startPossition;
				}			}
			
			return position;
		} else {
			throw new MoveToFar("ERROR: Moved more than 1 Tile."); 
		}
	}

	public int getReward(Position position) {
		return tile[position.getX()][position.getY()].getReward();
	}
	
	public Tile[][] getTile() {
		return tile;
	}

	@Override
	public String toString() {
		return "World [world=" + Arrays.toString(tile) + ", width=" + achseX + ", height=" + achseY + "]";
	}
}
