package environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import enums.Direction;
import enums.TileType;

public class Tile {

	private final String name;
	private final int reward;

	private final State position;
	private final TileType type;

	private Map<Direction, QValue> actionMap;

	public Tile(int x, int y, TileType type, int reward) {
		position = new State(x, y);
		this.type = type;
		this.reward = reward;

		name = "Tile_X" + x + "_Y" + y;

		actionMap = new HashMap<>();
		actionMap.put(Direction.NORTH, new QValue(Direction.NORTH, position));
		actionMap.put(Direction.EAST_, new QValue(Direction.EAST_, position));
		actionMap.put(Direction.SOUTH, new QValue(Direction.SOUTH, position));
		actionMap.put(Direction.WEST_, new QValue(Direction.WEST_, position));
	}

	/**
	 * Search for Max QValue
	 * 
	 * @return QValue
	 */
	public QValue getMaxQValue() {
		QValue maxQValue = null;
		for (Entry<Direction, QValue> entry : actionMap.entrySet()) {
			if (maxQValue == null) {
				maxQValue = entry.getValue();
			} else if (entry.getValue().getQValue() > maxQValue.getQValue()) {
				maxQValue = entry.getValue();
			}
		}
		return maxQValue;
	}

	/**
	 * Search for Random QValue
	 * 
	 * @return
	 */
	public QValue getRadomQValue() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(actionMap.size());
		QValue result = null;
		switch (randomInt) {
		case 0:
			result = actionMap.get(Direction.NORTH);
			break;
		case 1:
			result = actionMap.get(Direction.SOUTH);
			break;
		case 2:
			result = actionMap.get(Direction.WEST_);
			break;
		case 3:
			result = actionMap.get(Direction.EAST_);
			break;

		}
		return result;
		// return new ArrayList<QValue>(actionMap.values()).get(randomInt);
	}

	public void setQValue(Direction direction, double qValue) {
		actionMap.get(direction).setQValue(qValue);
	}

	public TileType getType() {
		return type;
	}

	public int getReward() {
		return reward;
	}

	public QValue getAction(Direction direction) {
		return actionMap.get(direction);
	}

	@Override
	public String toString() {
		return "Tile [name=" + name + ", position=" + position + ", reward=" + reward + ", actionList=" + actionMap
				+ ", type=" + type + "]";
	}

	private void printString() {
		System.out.printf("{[O][X%d][Y%d]N(%+06.2f)E(%+06.2f)S(%+06.2f)W(%+06.2f)} ", position.getX(), position.getY(),
				actionMap.get(Direction.NORTH).getQValue(), actionMap.get(Direction.EAST_).getQValue(),
				actionMap.get(Direction.SOUTH).getQValue(), actionMap.get(Direction.WEST_).getQValue());
	}

	private void printfString() {
		System.out.printf("{[X][X%d][Y%d]N(%+06.2f)E(%+06.2f)S(%+06.2f)W(%+06.2f)} ", position.getX(), position.getY(),
				actionMap.get(Direction.NORTH).getQValue(), actionMap.get(Direction.EAST_).getQValue(),
				actionMap.get(Direction.SOUTH).getQValue(), actionMap.get(Direction.WEST_).getQValue());
	}

	private void printStringPolicy() {
		System.out.printf("{[O][X%d][Y%d]Q(" + getMaxQValue().getAction() + ")} ", position.getX(), position.getY(),
				getMaxQValue().getQValue());
	}

	private void printfStringPolicy() {
		System.out.printf("{[X][X%d][Y%d]Q(" + getMaxQValue().getAction() + ")} ", position.getX(), position.getY(),
				getMaxQValue().getQValue());
	}

	public void printPolicy() {
		if (type == TileType.EMPTY) {
			printStringPolicy();
		} else if (type == TileType.CLIFF) {
			System.out.printf("{[----CLIFF-----]} ");
		} else if (type == TileType.START) {
			printStringPolicy();
		} else if (type == TileType.END) {
			System.out.printf("{[------END-----]} ");
		} else {
			System.out.println("ERROR: No Tile Type.");
		}
	}

	public void printfPolicy() {
		if (type == TileType.EMPTY) {
			printfStringPolicy();
		} else if (type == TileType.CLIFF) {
			System.out.printf("{[----CLIFF-----]} ");
		} else if (type == TileType.START) {
			printfStringPolicy();
		} else if (type == TileType.END) {
			System.out.printf("{[------END-----]} ");
		} else {
			System.out.println("ERROR: No Tile Type.");
		}
	}

	public void print() {
		if (type == TileType.EMPTY) {
			printString();
		} else if (type == TileType.CLIFF) {
			System.out.printf("[(O)---------------------CLIFF------------------] ");
		} else if (type == TileType.START) {
			printString();
		} else if (type == TileType.END) {
			System.out.printf("[(O)----------------------END-------------------] ");
		} else {
			System.out.println("ERROR: No Tile Type.");
		}
	}

	public void printf() {
		if (type == TileType.EMPTY) {
			printfString();
		} else if (type == TileType.CLIFF) {
			System.out.printf("[(X)---------------------CLIFF------------------] ");
		} else if (type == TileType.START) {
			printfString();
		} else if (type == TileType.END) {
			System.out.printf("[(X)----------------------END-------------------] ");
		} else {
			System.out.println("ERROR: No Tile Type.");
		}
	}
}