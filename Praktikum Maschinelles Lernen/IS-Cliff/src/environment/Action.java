package environment;

public class Action {
	private double QValue;	
	private final Direction action;
	
	public Action(Direction action) {
		this.action = action;
		QValue = 0;
	}

	public double getQValue() {
		return QValue;
	}

	public void setQValue(double qValue) {
		QValue = qValue;
	}

	public Direction getDirection() {
		return action;
	}
	
	@Override
	public String toString() {
		return "Action [QValue=" + QValue + ", action=" + action + "]";
	}
}
