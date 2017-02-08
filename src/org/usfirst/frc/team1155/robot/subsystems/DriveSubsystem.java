package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

	public enum DriveMode {
		MECHANUM, TANK, TURN_FRONT, TURN_BACK;
	}

	int WHEEL_RADIUS = 1;

	public CANTalon frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
	// public static DoubleSolenoid[] frontPivots, backPivots;

	public static DriveMode driveMode;

	public DriveSubsystem() {
		frontLeftMotor = new CANTalon(PortMap.FRONT_LEFT_TALON);
		frontRightMotor = new CANTalon(PortMap.FRONT_RIGHT_TALON);
		backLeftMotor = new CANTalon(PortMap.BACK_LEFT_TALON);
		backRightMotor = new CANTalon(PortMap.BACK_RIGHT_TALON);

		// frontPivots = new DoubleSolenoid[]{new
		// DoubleSolenoid(PortMap.FRONT_PIVOT_SOLENOIDS[0][0],
		// PortMap.FRONT_PIVOT_SOLENOIDS[0][1]),
		// new DoubleSolenoid(PortMap.FRONT_PIVOT_SOLENOIDS[1][0],
		// PortMap.FRONT_PIVOT_SOLENOIDS[1][1])};
		//
		// backPivots = new DoubleSolenoid[]{new
		// DoubleSolenoid(PortMap.BACK_PIVOT_SOLENOIDS[0][0],
		// PortMap.BACK_PIVOT_SOLENOIDS[0][1]),
		// new DoubleSolenoid(PortMap.BACK_PIVOT_SOLENOIDS[1][0],
		// PortMap.BACK_PIVOT_SOLENOIDS[1][1])};

		driveMode = DriveMode.TANK;
	}

	public void setDriveMode(DriveMode mode) {
		driveMode = mode;

		switch (mode) {
		case MECHANUM:
			engageFrontWheels(Value.kForward);
			engageBackWheels(Value.kForward);
			break;
		case TANK:
			engageFrontWheels(Value.kReverse);
			engageBackWheels(Value.kReverse);
			break;
		case TURN_BACK:
			engageFrontWheels(Value.kReverse);
			engageBackWheels(Value.kForward);
			break;
		case TURN_FRONT:
			engageFrontWheels(Value.kForward);
			engageBackWheels(Value.kReverse);
			break;
		}
	}

	public void setMechSpeed(double x, double y, double rotation) {
		frontLeftMotor.set(x + y + rotation);
		frontRightMotor.set(-x + y - rotation);
		backLeftMotor.set(-x + y + rotation);
		backRightMotor.set(x + y - rotation);
	}

	public void strafeDiagonal(double angle) {
		double thetaRad = (Math.PI / 180) * angle;
		// switch(angle){
		if (angle > 0 && angle <= 45) {
			frontLeftMotor.set(1);
			frontRightMotor.set(Math.cos(2 * thetaRad));
			backLeftMotor.set(Math.cos(2 * thetaRad));
			backRightMotor.set(1);
		} else if (angle > 45 && angle <= 90) {
			frontLeftMotor.set(1);
			frontRightMotor.set(-Math.sin(thetaRad));
			backLeftMotor.set(-Math.sin(thetaRad));
			backRightMotor.set(1);
		} else if (angle > 90 && angle <= 135) {
			frontLeftMotor.set(Math.sin(2 * (thetaRad % (Math.PI / 2))));
			frontRightMotor.set(-1);
			backLeftMotor.set(-1);
			backRightMotor.set(Math.sin(2 * (thetaRad % (Math.PI / 2))));
		} else if (angle > 135 && angle <= 180) {
			frontLeftMotor.set(-Math.cos((thetaRad % (Math.PI / 2))));
			frontRightMotor.set(-1);
			backLeftMotor.set(-1);
			backRightMotor.set(-Math.cos(thetaRad));
		} else if (angle >= -45 && angle < 0) {
			frontLeftMotor.set(Math.cos(thetaRad));
			frontRightMotor.set(1);
			backLeftMotor.set(1);
			backRightMotor.set(Math.cos(thetaRad));
		} else if (angle >= -90 && angle < -45) {
			frontLeftMotor.set(-Math.sin(thetaRad));
			frontRightMotor.set(1);
			backLeftMotor.set(1);
			backRightMotor.set(-Math.sin(thetaRad));
		} else if (angle >= -135 && angle < -90) {
			frontLeftMotor.set(-1);
			frontRightMotor.set(Math.sin(thetaRad));
			backLeftMotor.set(Math.sin(thetaRad));
			backRightMotor.set(-1);
		} else if (angle >= -180 && angle < -135) {
			frontLeftMotor.set(-1);
			frontRightMotor.set(-Math.cos(thetaRad));
			backLeftMotor.set(-Math.cos(thetaRad));
			backRightMotor.set(-1);
		}
	}

	public DriveMode getDriveMode() {
		return driveMode;
	}

	public void setTankSpeed(double leftVal, double rightVal) {
		frontRightMotor.set(rightVal);
		frontLeftMotor.set(leftVal);

		backRightMotor.set(rightVal);
		backLeftMotor.set(leftVal);
	}

	public void engageFrontWheels(DoubleSolenoid.Value value) {
		// for (DoubleSolenoid frontPivot: frontPivots) {
		// frontPivot.set(value);
		// }
	}

	public void engageBackWheels(DoubleSolenoid.Value value) {
		// for (DoubleSolenoid backPivot: backPivots) {
		// backPivot.set(value);
		// }
	}

	public void resetEncoders() {
		frontLeftMotor.setEncPosition(0);
		backLeftMotor.setEncPosition(0);
		frontRightMotor.setEncPosition(0);
		backRightMotor.setEncPosition(0);
	}

	public double getEncDistance() {
		return (frontRightMotor.getEncPosition() / 1023.0) * (Math.PI * 2 * WHEEL_RADIUS);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}