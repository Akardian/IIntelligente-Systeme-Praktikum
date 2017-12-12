package agent;

import environment.Position;

import environment.Action;
import environment.World;
import exceptions.MoveToFar;
import exceptions.WorldToSmall;

public class Agent {
	private World world;
	private Position position;
	private int moveReward;
	private final double learningRate;
	private final double discountFactor;
	
	public Agent(int moveReward) throws WorldToSmall {
		world = new World(5,4, -100, -1);
		
		this.moveReward = moveReward;
		position = new Position(0, 4 -1);
		
		learningRate = 0.2;
		discountFactor = 0.8;
	}
	
	public void QLearningStep() {
		try {
			Action action1 = world.getTile()[position.getX()][position.getY()].getMaxQValue();
			Position newPosition = world.move(action1, position);
			int reward = moveReward - world.getReward(newPosition);
			
			Action action2 = world.getTile()[newPosition.getX()][newPosition.getY()].getMaxQValue();
			
			double qValue = action1.getQValue() + learningRate * (reward + discountFactor * action2.getQValue() - action1.getQValue());
			
			 world.getTile()[position.getX()][position.getY()].setQValue(action1.getDirection(), qValue);
			position = newPosition;
		} catch (MoveToFar e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void print() {
		world.print(position);
	}
}
