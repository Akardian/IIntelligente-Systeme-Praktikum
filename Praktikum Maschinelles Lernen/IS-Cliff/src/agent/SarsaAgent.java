package agent;

import environment.QValue;
import environment.State;
import exceptions.WorldToSmall;

public class SarsaAgent extends Agent {
	private QValue lastAction;

	public SarsaAgent() throws WorldToSmall {
		super();
	}

	@Override
	public int episode() {
		int reward = 0;
		boolean terminate = false;

		QValue action1 = chooseAction(state);
		do {
			action1 = calcStep(action1);
			reward = episodeReward;
			terminate = endEpisode();
		} while (!terminate);
		return reward;
	}

	@Override
	public int doStep() {
		if (state.equals(startState)) {
			lastAction = chooseAction(state);
		}
		lastAction = calcStep(lastAction);
		int reward = episodeReward;
		endEpisode();

		return reward;
	}

	QValue calcStep(QValue action1) {
		State newState = world.move(action1.getAction(), state);

		int reward = moveReward + world.getReward(newState);
		episodeReward += reward;

		QValue action2 = chooseAction(newState);

		double qValue1 = action1.getQValue();
		double qValue2 = action2.getQValue();
		double newQValue = qValue1 + learningRate * (reward + discountFactor * qValue2 - qValue1);

		action1.setQValue(newQValue);

		state = newState;
		return action2;
	}
}
