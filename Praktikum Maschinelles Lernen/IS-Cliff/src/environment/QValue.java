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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof QValue)) return false;

		QValue qValue = (QValue) o;

		if (Double.compare(qValue.QValue, QValue) != 0) return false;
		return action == qValue.action;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(QValue);
		result = (int) (temp ^ (temp >>> 32));
		result = 31 * result + (action != null ? action.hashCode() : 0);
		return result;
	}
}
