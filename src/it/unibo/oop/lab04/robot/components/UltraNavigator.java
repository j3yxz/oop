package it.unibo.oop.lab04.robot.components;

import it.unibo.oop.lab04.robot.base.*;

public class UltraNavigator extends Component {

	private Direction lastMovement = Direction.NONE;
	//private RobotEnvironment environment;
	private Position2D masterPosition;
	final private String type = "Automatic navigator";
	
	public UltraNavigator() {
		super();
		
	}

	public UltraNavigator(ComponibleRobot master) {
		super(master);
		setMasterPosition(new RobotPosition(this.getMaster().getPosition().getX() , this.getMaster().getPosition().getY()));
	}

	protected void attachToRobot(ComponibleRobot master) {	
		this.master = master;

	}
	
	protected void detachFromRobot() {
		this.master = null;

		
	}
	protected void guide() {
		if(this.isAttached()) {
			
			while(this.getMaster().getBatteryLevel() > this.getMaster().getBatteryRequirementForMovement()) {
				setLastMovement(calculateDirection(this.getLastMovement()));
				move(this.getLastMovement());
			}	
		}
	}
	
	private void move(Direction last) {
		switch(last) {
		case UP:
			this.getMaster().moveUp();
			break;
		case DOWN:
			this.getMaster().moveDown();
			break;
		case LEFT:
			this.getMaster().moveLeft();
			break;
		case RIGHT:
			this.getMaster().moveRight();
			break;
		case NONE:
			break;
		default:
			break;
		
		}
		
	}

	protected Position2D getMasterPosition() {
		return masterPosition;
	}

	protected void setMasterPosition(Position2D masterPosition) {
		this.masterPosition = masterPosition;
	}

	protected Direction getLastMovement() {
		return lastMovement;
	}

	protected void setLastMovement(Direction lastMovement) {
		this.lastMovement = lastMovement;
	}

	private Direction calculateDirection(Direction lastDirection) {
		if(lastDirection == Direction.NONE) {
			
			switch(getBorderType(getMasterPosition())) {
			case X_0:
				return Direction.RIGHT;
			case X_0_Y_MAX:
				return Direction.RIGHT;
			case X_MAX:
				return Direction.LEFT;
			case X_MAX_Y_0:
				return Direction.LEFT;
			case X_Y_0:
				return Direction.UP;
			case X_Y_MAX:
				return Direction.DOWN;
			case Y_0:
				return Direction.UP;
			case Y_MAX:
				return Direction.DOWN;
			default:
				return Direction.LEFT;
			}
			
		} else {
			switch(lastDirection) {
			case DOWN:
				if(this.isWithinWorld(this.getMaster().getPosition().sumVector(0, -1))) {
					return Direction.DOWN;
				} else {
					getNextDirection(this.getBorderType(getMasterPosition()),Direction.DOWN);
				}
				
			case LEFT:
				if(this.isWithinWorld(this.getMaster().getPosition().sumVector(-1, 0))) {
					return Direction.LEFT;
				} else {
					getNextDirection(this.getBorderType(getMasterPosition()),Direction.LEFT);
				}
			case RIGHT:
				if(this.isWithinWorld(this.getMaster().getPosition().sumVector(+1, 0))) {
					return Direction.RIGHT;
				} else {
					getNextDirection(this.getBorderType(getMasterPosition()),Direction.RIGHT);
				}
			case UP:
				if(this.isWithinWorld(this.getMaster().getPosition().sumVector(0, +1))) {
					return Direction.LEFT;
				} else {
					getNextDirection(this.getBorderType(getMasterPosition()),Direction.LEFT);
				}
			default:
				//THIS HAS TO NOT HAPPEN
				return Direction.NONE;
			
			}
			
		}

		
	}
	
	
	public String getType() {
		return type;
	}
	
    protected boolean isWithinWorld(final Position2D p) {
        final var x = p.getX();
        final var y = p.getY();
        return x >= RobotEnvironment.X_LOWER_LIMIT && x <= RobotEnvironment.X_UPPER_LIMIT && y >= RobotEnvironment.Y_LOWER_LIMIT && y <= RobotEnvironment.Y_UPPER_LIMIT;
    }
    private BorderType getBorderType(Position2D pos) {
    	if(pos == new RobotPosition(RobotEnvironment.X_LOWER_LIMIT, RobotEnvironment.Y_LOWER_LIMIT)) return BorderType.X_Y_0;
    	else if(pos == new RobotPosition(RobotEnvironment.X_LOWER_LIMIT, RobotEnvironment.Y_UPPER_LIMIT)) return BorderType.X_0_Y_MAX;
    	else if(pos == new RobotPosition(RobotEnvironment.X_UPPER_LIMIT, RobotEnvironment.Y_LOWER_LIMIT)) return BorderType.X_MAX_Y_0;
    	else if(pos == new RobotPosition(RobotEnvironment.X_UPPER_LIMIT, RobotEnvironment.Y_UPPER_LIMIT)) return BorderType.X_Y_MAX;
    	else if(pos.getX() == RobotEnvironment.X_LOWER_LIMIT) return BorderType.X_0;
    	else if(pos.getX() == RobotEnvironment.X_UPPER_LIMIT) return BorderType.X_MAX;
    	else if(pos.getY() == RobotEnvironment.Y_LOWER_LIMIT) return BorderType.Y_0;
    	else if(pos.getY() == RobotEnvironment.Y_UPPER_LIMIT) return BorderType.Y_MAX;
    	else return BorderType.NO_BORDER;
    }
    private Direction getNextDirection(BorderType actual,Direction last) {
    	switch(actual) {
		case NO_BORDER:
			return last;
		case X_0:
			if(last == Direction.LEFT) {
				return Direction.UP;
			} else {
				return last;
			}
		case X_0_Y_MAX:
			if(last == Direction.LEFT) {
				return Direction.DOWN;
			} else {
				return Direction.RIGHT;
			}
		case X_MAX:
			if(last == Direction.RIGHT) {
				return Direction.DOWN;
			} else {
				return last;
			}
		case X_MAX_Y_0:
			if(last == Direction.RIGHT) {
				return Direction.UP;
			} else {
				return Direction.LEFT;
			}
		case X_Y_0:
			if(last == Direction.LEFT) {
				return Direction.UP;
			} else {
				return Direction.RIGHT;
			}
		case X_Y_MAX:
			if(last == Direction.RIGHT) {
				return Direction.DOWN;
			} else {
				return Direction.LEFT;
			}
		case Y_0:
			if(last == Direction.DOWN) {
				return Direction.LEFT;
			} else {
				return last;
			}
		case Y_MAX:
			if(last == Direction.UP) {
				return Direction.RIGHT;
			} else {
				return last;
			}
			default:
				//i can Throw an exception here
				return last;
    	}
    }
}
