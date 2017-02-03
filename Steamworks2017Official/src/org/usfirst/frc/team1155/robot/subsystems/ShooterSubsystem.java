package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class ShooterSubsystem extends Subsystem {

	public Servo leftShootServo, leftAgitatorServo;
	public Servo rightShootServo, rightAgitatorServo;
	
	public CANTalon leftShootTalon, rightShootTalon;
			
	public enum ServoPosition {
		POSITION_1 (0/180), POSITION_2 (180/180);

		private final double value;
		
		ServoPosition(double value) {
			this.value = value;
		}
	}
	
	public ShooterSubsystem() {
				
		//****************LEFT SIDE*******************//

		leftShootServo = new Servo(PortMap.LEFT_SHOOT_SERVO);
		setServoPosition(ServoPosition.POSITION_1);
		
		leftShootTalon = new CANTalon(PortMap.LEFT_SHOOT_TALON);
		leftShootTalon.set(0);
		
		leftAgitatorServo = new Servo(PortMap.LEFT_AGITATOR_SERVO);
		
		//****************RIGHT SIDE*******************//

		rightShootServo = new Servo(PortMap.RIGHT_SHOOT_SERVO);
		setServoPosition(ServoPosition.POSITION_1);
		
		rightShootTalon = new CANTalon(PortMap.RIGHT_SHOOT_SERVO);
		rightShootTalon.set(0);
		
		rightAgitatorServo = new Servo(PortMap.RIGHT_AGITATOR_SERVO);
		
	}
	
	public void setServoPosition(ServoPosition servoPos) {
		leftShootServo.setPosition(servoPos.value);
		rightShootServo.setPosition(servoPos.value);
	}

	public void setLeftShooter(double shootSpeed, double agitateSpeed) {
		leftShootTalon.set(shootSpeed);
		leftAgitatorServo.set(agitateSpeed);
	}
	
	public void setRightShooter(double shootSpeed, double agitateSpeed) {
		rightShootTalon.set(shootSpeed);
		rightAgitatorServo.set(agitateSpeed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

