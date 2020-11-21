package it.unibo.oop.lab04.robot.base;


public class RobotWithTwoArms  extends BaseRobot implements RobotWithArms{
	private int itemsCarried;
	final private BasicArm sxArm;
	final private BasicArm dxArm;
	
	public RobotWithTwoArms(final String name) {
		super(name);
		sxArm = new BasicArm("sx");
		dxArm = new BasicArm("dx");
		this.itemsCarried=0;
	}
    protected double getBatteryRequirementForMovement() {
        return MOVEMENT_DELTA_CONSUMPTION + this.getItemsCarried();
    }

	public boolean pickUp() {
		if(!dxArm.isGrabbing()) {
			dxArm.pickUp();
			this.consumeBattery(dxArm.getCunsuptionForPickUp());
			this.incCountItems();
		} else if(!sxArm.isGrabbing()) {
			sxArm.pickUp();
			this.consumeBattery(sxArm.getCunsuptionForPickUp());
			this.incCountItems();
		} else return false;
		
		return true;
	}

	private void decCountItems() {
		this.itemsCarried--;
	}
	private void incCountItems() {
		this.itemsCarried++;
	}

	
	public boolean dropDown() {
		if(dxArm.isGrabbing()) {
			dxArm.dropDown();
			this.consumeBattery(dxArm.getCunsuptionForDropDown());
			this.decCountItems();
		} else if(sxArm.isGrabbing()) {
			sxArm.dropDown();
			this.consumeBattery(sxArm.getCunsuptionForDropDown());
			this.decCountItems();
		} else return false;
		
		return true;
	}

	public int getCarriedItemsCount() {
		
		return this.getItemsCarried();
	}


	public int getItemsCarried() {
		
		return this.itemsCarried;
	}

}
