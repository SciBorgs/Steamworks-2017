package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.commands.MechanumDriveCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSubsystem extends Subsystem {

	public enum DriveMode {
		MECHANUM, TANK, TURN_FRONT, TURN_BACK;
	}

	public CANTalon frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
	public static DoubleSolenoid frontPivots, backPivots;

	public static DriveMode driveMode;
	public static final int WHEEL_RADIUS = 2;
	
	public boolean angleDrive = false;

	public DriveSubsystem() {

		frontLeftMotor = new CANTalon(PortMap.DRIVE_FRONT_LEFT_TALON);
		frontRightMotor = new CANTalon(PortMap.DRIVE_FRONT_RIGHT_TALON);
		backLeftMotor = new CANTalon(PortMap.DRIVE_BACK_LEFT_TALON);
		backRightMotor = new CANTalon(PortMap.DRIVE_BACK_RIGHT_TALON);

		frontPivots = new DoubleSolenoid(PortMap.DRIVE_RIGHT_PISTONS[0], PortMap.DRIVE_RIGHT_PISTONS[1]);
		backPivots = new DoubleSolenoid(PortMap.DRIVE_LEFT_PISTONS[0], PortMap.DRIVE_LEFT_PISTONS[1]);

		driveMode = DriveMode.TANK;
	}

	// call this normally
	// lateralJoy is rightJoystick
	public void setSpeed(Joystick lateralJoy, Joystick rotationalJoy) {
		if(angleDrive) {
			return;
		}
		switch (driveMode) {
		case TANK:
			setTankSpeed(-rotationalJoy.getY(), -lateralJoy.getY());
			break;
		case MECHANUM:
			double yVal = -lateralJoy.getY();
			double xVal = lateralJoy.getX();
			double rotationalVal = rotationalJoy.getX();
			setMechSpeed(xVal, yVal, rotationalVal);
			break;
		default:
			SmartDashboard.putString("Drive Error", "No DriveMode!!!");
		}
	}

	// call this to force tank drive
	public void setTankSpeed(double leftVal, double rightVal) {
		if(angleDrive) {
			return;
		}
		frontRightMotor.set(rightVal);
		frontLeftMotor.set(-leftVal);
		backRightMotor.set(rightVal);
		backLeftMotor.set(-leftVal);
	}

	// call this to force mechanum drive
	public void setMechSpeed(double xVal, double yVal, double rotationalVal) {
		if(angleDrive) {
			return;
		}
		xVal = xVal;
		// yVal = -yVal;
		rotationalVal = -rotationalVal;

		frontLeftMotor.set(-xVal - yVal + rotationalVal);
		frontRightMotor.set(-xVal + yVal + rotationalVal);
		backLeftMotor.set(xVal - yVal + rotationalVal);
		backRightMotor.set(xVal + yVal + rotationalVal);
	}
	
	public void moveDegrees(double degrees) {
		angleDrive = true;
			
		double conversionFactor = Math.PI/180;
		
		double xVal = -Math.cos(degrees * conversionFactor);
		double yVal = Math.sin(degrees * conversionFactor);
		
		System.out.println(xVal + " " + yVal + " " + degrees);
		
		frontLeftMotor.set(-xVal - yVal);
		frontRightMotor.set(-xVal + yVal);
		backLeftMotor.set(xVal - yVal);
		backRightMotor.set(xVal + yVal);
	}

	public void setDriveMode(DriveMode mode) {
		SmartDashboard.putString("DriveMode", mode.name());
		driveMode = mode;

		switch (mode) {
		case MECHANUM:
			engageWheels(Value.kForward);
			break;
		case TANK:
			engageWheels(Value.kReverse);
			break;
		}
	}

	public DriveMode getDriveMode() {
		return driveMode;
	}

	public void engageWheels(DoubleSolenoid.Value value) {
		frontPivots.set(value);
		backPivots.set(value);
		SmartDashboard.putString("Wheels", value.name());
	}
	
    public void resetEncoders(){
    	frontLeftMotor.setEncPosition(0);
    	backLeftMotor.setEncPosition(0);
    	frontRightMotor.setEncPosition(0);
    	backRightMotor.setEncPosition(0);
    }
    
    public double getEncDistance(){
    	return (frontRightMotor.getEncPosition()/1023.0) * (Math.PI * 2 * WHEEL_RADIUS);
    }

	@Override
	public void initDefaultCommand() {

	}
}
