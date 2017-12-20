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

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof QValue)) return false;

		QValue qValue = (QValue) o;

		if (Double.compare(qValue.QValue, QValue) != 0) return false;
		return action == qValue.action;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(QValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		return result;
	}
}
