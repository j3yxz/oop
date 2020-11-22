package it.unibo.oop.lab04.robot.components;

import it.unibo.oop.lab04.robot.base.*;

public class ComponibleRobot extends BaseRobot implements RobotWithComponents {

	
	private int numAttachedComponents;
	private int itemsCarried;
	
	public ComponibleRobot(String robotName) {
		super(robotName);
		this.setNumAttachedComponents(0);
		this.setItemsCarried(0);
	}

	protected double getBatteryRequirementForMovement() {
		return BaseRobot.MOVEMENT_DELTA_CONSUMPTION + 0.5*this.getItemsCarried() + 0.8*this.getNumAttachedComponents();
	}
	
	public int getItemsCarried() {
		return itemsCarried;
	}

	public void setItemsCarried(int itemsCarried) {
		this.itemsCarried = itemsCarried;
	}

	public void attachNewComponent(Component newComp) {
		newComp.attachToRobot(this);
		this.numAttachedComponents++;
		
	}

	public void detachComponent(Component toDetach) {
		toDetach.detachFromRobot();
		this.numAttachedComponents--;
		
	}

	public int getNumAttachedComponents() {
		return numAttachedComponents;
	}

	public void setNumAttachedComponents(int numAttachedComponents) {
		this.numAttachedComponents = numAttachedComponents;
	}


	public int getCarriedItemsCount() {
		return this.itemsCarried;
	}

}
