package agent;

import environment.State;
import environment.QValue;

import enums.Direction;
import exceptions.WorldToSmall;

public class QLearningAgent extends Agent {
	public QLearningAgent() throws WorldToSmall {
		super();
	}

	@Override
	public int doMan(Direction direction) {
		calcStep(world.getTile(state).getAction(direction));
		int reward = episodeReward;
		endEpisode();

		return reward;
	}

	@Override
	public int doStep() {
		calcStep(chooseAction(state));
		int reward = episodeReward;
		endEpisode();

		return reward;
	}

	@Override
	public int episode() {
		int reward = 0;
		boolean terminate = false;
		do {
			calcStep(chooseAction(state));
			reward = episodeReward;
			terminate = endEpisode();
		} while (!terminate);

		return reward;
	}

	protected void calcStep(QValue qValue1) {
		State newState = world.move(qValue1.getAction(), state);

		int reward = moveReward + world.getReward(newState);
		episodeReward += reward;

		QValue qValue2 = world.getTile(newState).getMaxQValue();

		double newQValue = qValue1.getQValue()
				+ learningRate * (reward + discountFactor * qValue2.getQValue() - qValue1.getQValue());

		qValue1.setQValue(newQValue);
		state = newState;
	}
}
