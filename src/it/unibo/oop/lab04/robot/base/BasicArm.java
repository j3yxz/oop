package it.unibo.oop.lab04.robot.base;

public class BasicArm {

	private static final double LIFT_ENERGY = 0.5;
	private static final double DROP_ENERGY = 0.1;
	
	private final String name;
	private boolean status = false;
	
	public BasicArm(final String name) {
		this.name = name;
	}
	
	protected boolean isGrabbing() {
		return this.status;
	}
	
	protected void setStatus(boolean status) {
		this.status = status;
	}
	
	protected String getName() {
		return name;
	}
	
	public void pickUp() {
		if(isGrabbing()) {
			
		} else this.setStatus(true);
			
	}
	
	public void dropDown() {
		if(isGrabbing()) {
			this.setStatus(false);
		}
	}
	
	public double getCunsuptionForPickUp() {
		return LIFT_ENERGY;
	}

	public double getCunsuptionForDropDown() {
		return DROP_ENERGY;
	}
	
	public String toString() {
		return "Arm:" + getName() + "GrabbingStatus:" + isGrabbing();
	}
}
