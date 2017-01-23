package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

	public Servo shootServo;
	
	public CANTalon shootTalon;
		
	public enum ServoPosition {
		
		POSITION1 (0.9), POSITION2 (1.2);

		private final double speed;
		
		ServoPosition(double speed) {
			this.speed = speed;
		}
	}
	
	public ShooterSubsystem() {
		shootServo = new Servo(PortMap.SHOOT_SERVO);
		setServoPosition(ServoPosition.POSITION1);
		
		shootTalon = new CANTalon(PortMap.SHOOT_TALON);
		shootTalon.set(0);
	}
	
	public void setServoPosition(ServoPosition servoPos) {
		shootServo.setPosition(servoPos.speed);
	}

	public void setShooterSpeed(double speed) {
		shootTalon.set(speed);
	}
	 
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

