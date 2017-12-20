package agent;

import environment.State;
import environment.Tile;
import environment.QValue;

import java.util.Random;

import enums.Direction;
import enums.TileType;
import environment.World;
import exceptions.WorldToSmall;

public abstract class Agent {
	World world;
	State state;
	State startState;

	int moveReward;
	int episodeReward;

	boolean changedQValue = false;
	
	final double eGreedy;
	final double learningRate;
	final double discountFactor;
	private final int achseX;
	private final int achseY;

	public Agent() throws WorldToSmall {
		achseX = 5;
		achseY = 6;
		world = new World(achseX, achseY);

		this.moveReward = -1;
		episodeReward = 0;
		state = new State(0, 4 - 1);

		learningRate = 0.2;
		discountFactor = 0.95;
		eGreedy = 0.1;

		startState = new State(0, 3);
	}
	
	abstract public int episode();
	abstract public int doStep();

	protected QValue chooseAction(State state) {
		Random randomGenerator = new Random();
		double randomDouble = randomGenerator.nextDouble();

		QValue action;
		if (randomDouble < eGreedy) {
			action = world.getTile(state).getRadomQValue();
		} else {
			action = world.getTile(state).getMaxQValue();
		}
		return action;
	}

	protected boolean endEpisode() {
		boolean terminate = false;
		if (world.getTile(state).getType() == TileType.END) {
			terminate = true;
			state = startState;
			episodeReward = 0;
		} else if (world.getTile(state).getType() == TileType.CLIFF) {
			terminate = true;
			state = startState;
			episodeReward = 0;
		}
		return terminate;
	}

	public Direction[][] cloneTileDirection() {
		Direction[][] newTile = new Direction[achseY][achseX];
		Tile[][] tile = world.getTile();
		
		for (int i = 0; i < achseY; i++) {
			for (int j = 0; j < achseX; j++) {
				newTile[i][j] = tile[j][i].getMaxQValue().getAction();
			}
		}
		return newTile;
	}
	
	public boolean checkChange(Direction[][] lastWorld) {
		Tile[][] tile = world.getTile();
		for (int i = 0; i < achseY; i++) {
			for (int j = 0; j < achseX; j++) {
				if(tile[j][i].getMaxQValue().getAction() != lastWorld[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void print() {
		world.print(state);
	}

	public void printC() {
		world.printPolicy(state);

	} 
}
