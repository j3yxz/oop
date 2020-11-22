package it.unibo.oop.lab04.robot.components;

import it.unibo.oop.lab04.robot.base.BaseRobot;

public class AtomicBattery extends Component {

	private String type = "Atomic Battery";
	
	public AtomicBattery() {
		super();
	}
	public AtomicBattery(ComponibleRobot master) {
		super(master);
	}
	
	protected void atomicRecharge() {
		if(this.isAttached()) {
			if(100*this.getMaster().getBatteryLevel() < 50*BaseRobot.BATTERY_FULL) {
				this.getMaster().recharge();
			}
		}
	}
	
	
	public String getType() {
		return type;
	}
	
	
	
}
