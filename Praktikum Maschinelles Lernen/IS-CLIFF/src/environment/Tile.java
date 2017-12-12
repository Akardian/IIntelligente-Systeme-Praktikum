package environment;

import java.util.ArrayList;
import java.util.Iterator;

public class Tile {

	private final String name;
	private final Position position;
	private final int reward;

	private ArrayList<Action> actionList;
	private TileType type;

	public Tile(int x, int y, TileType type, int reward) {
		position = new Position(x, y);
		this.type = type;
		this.reward = reward;

		name = "Tile_X" + x + "_Y" + y;

		actionList = new ArrayList<>();
		actionList.add(new Action(Direction.NORTH));
		actionList.add(new Action(Direction.EAST));
		actionList.add(new Action(Direction.SOUTH));
		actionList.add(new Action(Direction.WEST));
	}

	public Action getMaxQValue() {
		Action maxQValue = null;
		for (int i = 0; i < actionList.size(); i++) {
			if (maxQValue == null) {
				maxQValue = actionList.get(i);
			} else if (actionList.get(i).getQValue() > maxQValue.getQValue()) {
				maxQValue = actionList.get(i);
			}
		}

		return maxQValue;
	}
	
	public void setQValue(Direction direction, double qValue) {
		for (int i = 0; i < actionList.size(); i++) {
			if (actionList.get(i).getDirection() == direction) {
				actionList.get(i).setQValue(qValue);
			}
		}
	}

	public void print() {
		if (type == TileType.EMPTY) {
			System.out.printf("[(O)" + name + " N(%+.2f) E(%+.2f) S(%+.2f) W(%+.2f)] ", actionList.get(0).getQValue(),
					actionList.get(1).getQValue(), actionList.get(2).getQValue(), actionList.get(3).getQValue());
		} else if (type == TileType.CLIFF) {
			System.out.printf("[(O)-------------------CLIFF-----------------] ");
		} else if (type == TileType.START) {
			System.out.printf("[(O)" + name + " N(%+.2f) E(%+.2f) S(%+.2f) W(%+.2f)] ", actionList.get(0).getQValue(),
					actionList.get(1).getQValue(), actionList.get(2).getQValue(), actionList.get(3).getQValue());
		} else if (type == TileType.END) {
			System.out.printf("[(O)--------------------END------------------] ");
		} else {
			System.out.println("ERROR: No Tile Type.");
		}
	}

	public void printf() {
		if (type == TileType.EMPTY) {
			System.out.printf("[(X)" + name + " N(%+.2f) E(%+.2f) S(%+.2f) W(%+.2f)] ", actionList.get(0).getQValue(),
					actionList.get(1).getQValue(), actionList.get(2).getQValue(), actionList.get(3).getQValue());
		} else if (type == TileType.CLIFF) {
			System.out.printf("[(X)-------------------CLIFF-----------------] ");
		} else if (type == TileType.START) {
			System.out.printf("[(X)" + name + " N(%+.2f) E(%+.2f) S(%+.2f) W(%+.2f)] ", actionList.get(0).getQValue(),
					actionList.get(1).getQValue(), actionList.get(2).getQValue(), actionList.get(3).getQValue());
		} else if (type == TileType.END) {
			System.out.printf("[(X)--------------------END------------------] ");
		} else {
			System.out.println("ERROR: No Tile Type.");
		}
	}

	@Override
	public String toString() {
		return "Tile [name=" + name + ", position=" + position + ", reward=" + reward + ", actionList=" + actionList
				+ ", type=" + type + "]";
	}

	public TileType getType() {
		return type;
	}

	public int getReward() {
		return reward;
	}
}
