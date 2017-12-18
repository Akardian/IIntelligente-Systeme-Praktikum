package agent;

import environment.State;
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

	final double eGreedy;
	final double learningRate;
	final double discountFactor;

	public Agent() throws WorldToSmall {
		world = new World(5, 4);

		this.moveReward = -1;
		episodeReward = 0;
		state = new State(0, 4 - 1);

		learningRate = 0.2;
		discountFactor = 0.8;
		eGreedy = 0.1;

		startState = new State(0, 3);
	}
	
	abstract public int episode();
	abstract public int doMan(Direction direction);
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
			// terminate = true;
			state = startState;
			//episodeReward = 0;
		}
		return terminate;
	}

	public void print() {
		world.print(state);
	}

	public void printC() {
		world.printPolicy(state);

	}
}
