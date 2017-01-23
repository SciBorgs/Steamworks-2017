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

	public static DoubleSolenoid doubleSolenoid;
	public static Ultrasonic ultrasonic;
	
	//Min and max distances for the wall and the gear when it is in the robot 
	public static final double FARTHEST_FROM_WALL = 0.0;
	public static final double CLOSEST_TO_WALL = 0.0;
	public static final double GEAR_CLOSEST = 0.0;
	public static final double GEAR_FARTHEST = 0.0;
	
	public GearSubsystem(){
		doubleSolenoid = new DoubleSolenoid(PortMap.GEAR_SOLENOID_FWD, PortMap.GEAR_SOLENOID_REV);
		ultrasonic = new Ultrasonic(PortMap.ULTRASONIC_PING, PortMap.ULTRASONIC_ECHO);//, Ultrasonic.Unit.kInches);
	}
	
	//Checks whether the robot is in a suitable distance from the wall
	public boolean validDistFromWall(){
		return (us.getRangeInches() <= FARTHEST_FROM_WALL && us.getRangeInches() >= CLOSEST_TO_WALL);
	
	//Uses ultrasonic to detect wether the gear is in the robot
	public boolean hasGear(){
		return (us.getRangeInches() <= GEAR_FARTHEST && us.getRangeInches() >= GEAR_CLOSEST);
	}
	
	//Opens solenoid
	public void open(){
		doubleSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	//Closes solenoid
	public void close(){
		doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

