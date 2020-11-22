package it.unibo.oop.lab04.robot.components;

public class Component implements IComponent {

	protected ComponibleRobot master;
	
	public Component() {
		this.master = null;
	}
	
	public Component(ComponibleRobot master) {
		this.master = master;
	}

	public String getMasterName(){
		return master.toString();
	}
	
	public Boolean isAttached() {
		if(this.master == null) return false;
		else return true;
	}

	protected void attachToRobot(ComponibleRobot master) {
		
		this.master = master;
		
	}
	
	protected void detachFromRobot() {
		this.master=null;
		
	}

	protected ComponibleRobot getMaster() {
		return this.master;
	}


}
