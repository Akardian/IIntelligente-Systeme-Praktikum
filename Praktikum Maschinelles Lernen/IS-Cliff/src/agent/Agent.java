package agent;

import environment.Position;
import environment.TileType;
import environment.Action;
import environment.Direction;
import environment.World;
import exceptions.MoveToFar;
import exceptions.WorldToSmall;

public class Agent {
	private World world;
	private Position state;
	private Position startState;
	private int moveReward;
	private final double learningRate;
	private final double discountFactor;

	public Agent() throws WorldToSmall {
		world = new World(5, 4, -100, -1);

		this.moveReward = -1;
		state = new Position(0, 4 - 1);

		learningRate = 0.2;
		discountFactor = 0.8;

		startState = new Position(0, 3);
	}

	public Agent(World world, Position state, Position startState, int moveReward, double learningRate,
			double discountFactor) {
		this.world = world;
		this.state = state;
		this.startState = startState;
		this.moveReward = moveReward;
		this.learningRate = learningRate;
		this.discountFactor = discountFactor;
	}

	public void qLearningMan(Direction direction) {
		Action action1 = world.getTile(state).getAction(direction);
		qLearning(action1);
	}

	public void qLearningStep() {
		Action action1 = world.getTile(state).getMaxQValue();
		qLearning(action1);
	}

	public void qLearningEpisode() {
		boolean terminate = false;
		do {
			Action action1 = world.getTile(state).getMaxQValue();
			terminate = qLearning(action1);
		} while (!terminate);
	}

	private boolean qLearning(Action action1) {
		boolean terminate = false;

		try {
			Position newState = world.move(action1, state);
			int reward = moveReward + world.getReward(newState);

			Action action2 = world.getTile()[newState.getX()][newState.getY()].getMaxQValue();

			double qValue = action1.getQValue()
					+ learningRate * (reward + discountFactor * action2.getQValue() - action1.getQValue());

			world.getTile()[state.getX()][state.getY()].setQValue(action1.getDirection(), qValue);
			state = newState;

			if (world.getTile(state).getType() == TileType.END) {
				terminate = true;
				state = startState;
			}else if ( world.getTile(state).getType() == TileType.CLIFF) {
				state = startState;
			}

		} catch (MoveToFar e) {
			e.printStackTrace();
		}
		return terminate;
	}

	public void print() {
		world.print(state);
	}

	public void printC() {
		world.printc();
		
	}
}
