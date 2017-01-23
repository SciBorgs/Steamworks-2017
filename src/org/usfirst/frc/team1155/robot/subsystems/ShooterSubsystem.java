package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem; 
/**
 *
 */


public class ShooterSubsystem extends Subsystem {
//	enum ValveState{
//		CLOSED_STATE,
//		OPEN_STATE; 
//	}
	public CANTalon shooterTalon;
	public Servo shooterServo;
	
	public ShooterSubsystem(){
		shooterTalon = new CANTalon(PortMap.SHOOTER_TALON);
		shooterServo = new Servo(PortMap.SHOOTER_SERVO);
	}
	
	public void setSpeedOfTheWheelThatShootsTheBalls(double gottaGoFast){
		shooterTalon.set(gottaGoFast);
		
	}
	
	/**
	 * 
	 */
	public void toggleRelease(){
		shooterServo.set(.5);
	}
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

