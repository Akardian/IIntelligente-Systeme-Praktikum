package environment;

import enums.Direction;

public class QValue {
	private double QValue;	
	private final Direction action;
	
	public QValue(Direction action, State state) {
		this.action = action;
		QValue = 0;
	}

	public double getQValue() {
		return QValue;
	}

	public void setQValue(double qValue) {
		QValue = qValue;
	}

	public Direction getAction() {
		return action;
	}
	
	@Override
	public String toString() {
		return "Action [QValue=" + QValue + ", action=" + action + "]";
	}
}
