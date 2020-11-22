package it.unibo.oop.lab04.robot.components;

public class MechArm extends Component {
	
	private boolean held;
	private String type = "Mechanic Arm";
	
	
	public MechArm() {
		super();
		this.held = false;
	}
	public MechArm(ComponibleRobot master) {
		super(master);
		this.held = false;
	}
	protected void pick() {
		if(this.isAttached()) {
			if(!this.heldStatus()) {
				this.setHeld(true);
				this.getMaster().setItemsCarried(this.getMaster().getCarriedItemsCount()+1);
			}
		}
	}
	
	protected void drop() {
		if(this.isAttached()) {
			if(this.heldStatus()) {
				this.setHeld(false);
				this.getMaster().setItemsCarried(this.getMaster().getCarriedItemsCount()-1);
			}
		}
	}
	
	protected boolean heldStatus() {
		return held;
	}


	protected void setHeld(boolean held) {
		this.held = held;
	}


	
	public String getType() {
		return type;
	}
	
}
