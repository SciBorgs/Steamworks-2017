package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public static DoubleSolenoid ds;
	public static Ultrasonic us;
	
	public static final double FARTHEST_FROM_WALL = 0.0;
	public static final double CLOSEST_TO_WALL = 0.0;
	public static final double GEAR_CLOSEST = 0.0;
	public static final double GEAR_FARTHEST = 0.0;
	
	public GearSubsystem(){
    	ds = new DoubleSolenoid(PortMap.GEAR_SOLENOID_FWD, PortMap.GEAR_SOLENOID_REV);
    	us = new Ultrasonic(PortMap.ULTRASONIC_PING, PortMap.ULTRASONIC_ECHO);//, Ultrasonic.Unit.kInches);
	}
	
	public boolean validDistFromWall(){
		if(us.getRangeInches() <= FARTHEST_FROM_WALL && us.getRangeInches() >= CLOSEST_TO_WALL){
			return true;
		}
		return false;
	}
	
	public boolean hasGear(){
		if(us.getRangeInches() <= GEAR_FARTHEST && us.getRangeInches() >= GEAR_CLOSEST){
			return true;
		}
		return false;
	}
	
	public void open(){
		ds.set(DoubleSolenoid.Value.kForward);
	}
	
	public void close(){
		ds.set(DoubleSolenoid.Value.kForward);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

