package environment;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Tile {

	private final String name;
	private final Position position;
	private final int reward;

	private Map<Direction, Action> actionMap;
	private TileType type;

	public Tile(int x, int y, TileType type, int reward) {
		position = new Position(x, y);
		this.type = type;
		this.reward = reward;

		name = "Tile_X" + x + "_Y" + y;

		actionMap = new HashMap<>();
		actionMap.put(Direction.NORTH, new Action(Direction.NORTH));
		actionMap.put(Direction.EAST_, new Action(Direction.EAST_));
		actionMap.put(Direction.SOUTH, new Action(Direction.SOUTH));
		actionMap.put(Direction.WEST_, new Action(Direction.WEST_));
	}

	public Action getMaxQValue() {
		Action maxQValue = null;
		for (Entry<Direction, Action> entry : actionMap.entrySet()) {
			if (maxQValue == null) {
				maxQValue = entry.getValue();
			} else if (entry.getValue().getQValue() > maxQValue.getQValue()) {
				maxQValue = entry.getValue();
			}
		}
		return maxQValue;
	}

	public void setQValue(Direction direction, double qValue) {
		actionMap.get(direction).setQValue(qValue);
	}

	public void print() {
		if (type == TileType.EMPTY) {
			System.out.printf("{[O][X%d][Y%d]N(%+06.2f)E(%+06.2f)S(%+06.2f)W(%+06.2f)} ",
					position.getX(), position.getY(),
					actionMap.get(Direction.NORTH).getQValue(), actionMap.get(Direction.EAST_).getQValue(),
					actionMap.get(Direction.SOUTH).getQValue(), actionMap.get(Direction.WEST_).getQValue());
		} else if (type == TileType.CLIFF) {
			System.out.printf("[(O)---------------------CLIFF------------------] ");
		} else if (type == TileType.START) {
			System.out.printf("{[O][X%d][Y%d]N(%+06.2f)E(%+06.2f)S(%+06.2f)W(%+06.2f)} ",
					position.getX(), position.getY(),
					actionMap.get(Direction.NORTH).getQValue(), actionMap.get(Direction.EAST_).getQValue(),
					actionMap.get(Direction.SOUTH).getQValue(), actionMap.get(Direction.WEST_).getQValue());
		} else if (type == TileType.END) {
			System.out.printf("[(O)----------------------END-------------------] ");
		} else {
			System.out.println("ERROR: No Tile Type.");
		}
	}

	public void printf() {
		if (type == TileType.EMPTY) {
			System.out.printf("{[X][X%d][Y%d]N(%+06.2f)E(%+06.2f)S(%+06.2f)W(%+06.2f)} ",
					position.getX(), position.getY(),
					actionMap.get(Direction.NORTH).getQValue(), actionMap.get(Direction.EAST_).getQValue(),
					actionMap.get(Direction.SOUTH).getQValue(), actionMap.get(Direction.WEST_).getQValue());
		} else if (type == TileType.CLIFF) {
			System.out.printf("[(X)---------------------CLIFF------------------] ");
		} else if (type == TileType.START) {
			System.out.printf("{[X][X%d][Y%d]N(%+06.2f)E(%+06.2f)S(%+06.2f)W(%+06.2f)} ",
					position.getX(), position.getY(),
					actionMap.get(Direction.NORTH).getQValue(), actionMap.get(Direction.EAST_).getQValue(),
					actionMap.get(Direction.SOUTH).getQValue(), actionMap.get(Direction.WEST_).getQValue());
		} else if (type == TileType.END) {
			System.out.printf("[(X)----------------------END-------------------] ");
		} else {
			System.out.println("ERROR: No Tile Type.");
		}
	}

	@Override
	public String toString() {
		return "Tile [name=" + name + ", position=" + position + ", reward=" + reward + ", actionList=" + actionMap
				+ ", type=" + type + "]";
	}

	public TileType getType() {
		return type;
	}

	public int getReward() {
		return reward;
	}

	public Action getAction(Direction direction) {
		return actionMap.get(direction);
	}

	public void printC() {
		if (type == TileType.EMPTY) {
			System.out.printf("{[X%d][Y%d]Q("+ getMaxQValue().getDirection() +")(%+06.2f)} ",
					position.getX(), position.getY(),getMaxQValue().getQValue());
		} else if (type == TileType.CLIFF) {
			System.out.printf("{[--------CLIFF---------]} ");
		} else if (type == TileType.START) {
			System.out.printf("{[X%d][Y%d]Q("+ getMaxQValue().getDirection() +")(%+06.2f)} ",
					position.getX(), position.getY(), getMaxQValue().getQValue());
		} else if (type == TileType.END) {
			System.out.printf("{[----------END---------]} ");
		} else {
			System.out.println("ERROR: No Tile Type.");
		}
		
	}
}
