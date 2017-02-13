package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class ShooterSubsystem extends Subsystem {
	// enum ValveState{
	// CLOSED_STATE,
	// OPEN_STATE;
	// }
	public CANTalon shooterTalon;
	public Servo shooterServo, agitatorServo1, agitatorServo2;

	private double servoToggleSpeed = 0.5;

	public ShooterSubsystem() {
		shooterTalon = new CANTalon(PortMap.SHOOTER_TALON);
		shooterServo = new Servo(PortMap.SHOOTER_SERVO);
		agitatorServo1 = new Servo(PortMap.AGITATOR_SERVO_ONE);
		agitatorServo2 = new Servo(PortMap.AGITATOR_SERVO_TWO);
	}

	public void setWheelSpeed(double speed) {
		// shooterTalon.set(speed);

	}

	/**
	 * 
	 */
	public void toggleRelease() {
		// shooterServo.set(servoToggleSpeed);
		
	}
	
	public void setAgitatorServo(int position){
		agitatorServo1.set(position);
		agitatorServo2.set(position);
	}
	
	public double getAgitatorServo(){
			return agitatorServo1.get();
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
