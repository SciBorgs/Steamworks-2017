package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;

import com.ctre.CanTalon;

import edu.wpi.first.wpilibj.Servo; 
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
	public void rev(boolean buttonPressed){
		
		shooterTalon = new CANTalon(PortMap.SHOOTER_TALON);
		if(buttonPressed){
			shooterTalon.set(1.0);
		}
		
	}
	
	/**
	 * 
	 */
	public void toggleRelease(boolean buttonPressed){
		shooterServo = new Servo(PortMap.SHOOTER_SERVO);
		if(buttonPressed){
			shooterServo.set(1.0);
		} else {
			shooterServo.set(0.0);
		}	
	}
	
	public void stopWheel(){
		shooterTalon.set(0.0);
	}
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

