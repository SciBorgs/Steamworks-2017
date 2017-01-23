package org.usfirst.frc.team1155.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

	public Servo shootServo;
	
	public enum ServoMode {
		MODE1, MODE2;
	}
	
	public ShooterSubsystem() {
		shootServo = new Servo(0);
		shootServo.setPosition(0);
	}
	
	public void setPosition(double position) {
		shootServo.setPosition(position);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

