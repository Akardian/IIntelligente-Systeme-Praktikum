package environment;

import enums.Direction;

public class Turn {
	State oldPosition;
	State newPosition;
	
	public Turn(State oldPosition, State newPosition) {
		this.oldPosition = oldPosition;
		this.newPosition = newPosition;
	}
	
	private Direction direction() {
		int directionX = oldPosition.getX() - newPosition.getX();
		int directionY = oldPosition.getY() - newPosition.getY();
		
		Direction direction;
		
		if(directionX == 1 && directionY == 0) {
			direction = Direction.WEST_;
		} else if(directionX == -1 && directionY == 0) {
			direction = Direction.EAST_;
		} else if(directionX == 0 && directionY == 1) {
			direction = Direction.NORTH;
		}else if(directionX == 0 && directionY == -1) {
			direction = Direction.SOUTH;
		}else {
			direction = null;
			System.out.println("ERROR: direction()");
		}
		
		return direction;
	}
	
	public State right() {
		Direction direction = direction();
		
		State position;
		
		if(direction == Direction.NORTH) { //Move East
			position = oldPosition.move(Direction.EAST_);
		} else if(direction == Direction.EAST_) { //Move South
			position = oldPosition.move(Direction.SOUTH);
		} else if(direction == Direction.SOUTH) { //Move West
			position = oldPosition.move(Direction.WEST_);
		}else if(direction == Direction.WEST_) { //Move North
			position = oldPosition.move(Direction.NORTH);
		} else {
			position = null;
			System.out.println("ERROR: right()");
		}
		
		return position;
	}
	
	public State left() {
		Direction direction = direction();
		
		State position;
		
		if(direction == Direction.NORTH) {// Move West
			position = oldPosition.move(Direction.WEST_);
		} else if(direction == Direction.EAST_) {//Move North
			position = oldPosition.move(Direction.NORTH);
		} else if(direction == Direction.SOUTH) {//Move East
			position = oldPosition.move(Direction.EAST_);
		}else if(direction == Direction.WEST_) {//Move South
			position = oldPosition.move(Direction.SOUTH);
		} else {
			position = null;
			System.out.println("ERROR: right()");
		}
		
		return position;
	}
	
	public State backwards() {
		Direction direction = direction();
		
		State position;
		
		if(direction == Direction.NORTH) {// Move South
			position = oldPosition.move(Direction.SOUTH);
		} else if(direction == Direction.EAST_) {//Move West
			position = oldPosition.move(Direction.WEST_);
		} else if(direction == Direction.SOUTH) {//Move North
			position = oldPosition.move(Direction.NORTH);
		}else if(direction == Direction.WEST_) {//Move East
			position = oldPosition.move(Direction.EAST_);
		} else {
			position = null;
			System.out.println("ERROR: right()");
		}
		
		return position;
	}
}
