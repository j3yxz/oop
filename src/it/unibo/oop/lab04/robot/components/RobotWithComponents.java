package it.unibo.oop.lab04.robot.components;

import it.unibo.oop.lab04.robot.base.*;

public interface RobotWithComponents extends Robot {
	
	public void attachNewComponent(Component newComp);
	
	public void detachComponent(Component toDetach);
	
	public int getCarriedItemsCount();
	
}
