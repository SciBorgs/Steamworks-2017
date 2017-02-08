package org.usfirst.frc.team1155.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	// public static DoubleSolenoid leftDoubleSolenoid, rightDoubleSolenoid;
	// public static Ultrasonic ultrasonic;

	// Min and max distances for the wall and the gear when it is in the robot
	public static final double FARTHEST_FROM_WALL = 0.0;
	public static final double CLOSEST_TO_WALL = 0.0;
	public static final double GEAR_CLOSEST = 0.0;
	public static final double GEAR_FARTHEST = 0.0;

	public GearSubsystem() {
		// leftDoubleSolenoid = new
		// DoubleSolenoid(PortMap.LEFT_GEAR_SOLENOID_FWD,
		// PortMap.LEFT_GEAR_SOLENOID_REV);
		// rightDoubleSolenoid = new
		// DoubleSolenoid(PortMap.RIGHT_GEAR_SOLENOID_FWD,
		// PortMap.RIGHT_GEAR_SOLENOID_REV);
		// ultrasonic = new Ultrasonic(PortMap.ULTRASONIC_PING,
		// PortMap.ULTRASONIC_ECHO);//, Ultrasonic.Unit.kInches);
	}

	public void enableUltrasonic() {
		// ultrasonic.setEnabled(true);
	}

	// Checks whether the robot is in a suitable distance from the wall
	public boolean validDistFromWall() {
		// return (ultrasonic.getRangeInches() <= FARTHEST_FROM_WALL &&
		// ultrasonic.getRangeInches() >= CLOSEST_TO_WALL);
		return false;
	}

	// Uses ultrasonic to detect wether the gear is in the robot
	public boolean hasGear() {
		return false;
		// return (ultrasonic.getRangeInches() <= GEAR_FARTHEST &&
		// ultrasonic.getRangeInches() >= GEAR_CLOSEST);
	}

	// Opens solenoid
	public void open() {
		// leftDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
		// rightDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	// Closes solenoid
	public void close() {
		// leftDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
		// rightDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
