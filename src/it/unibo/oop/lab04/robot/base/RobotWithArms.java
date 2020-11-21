package it.unibo.oop.lab04.robot.base;

public interface RobotWithArms extends Robot {
	
	public boolean pickUp();
	
	public boolean dropDown();
	
	public int getCarriedItemsCount();
	
	
}
