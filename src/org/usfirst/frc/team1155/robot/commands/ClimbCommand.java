package org.usfirst.frc.team1155.robot.commands;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.ClimbSubsystem;

/**
 *
 */
public class ClimbCommand extends Command {

	public static JoystickButton cButton;

	private double circumferenceOfSpinnyThing = 6; // not actual circumference
													// fix later
	private double ropeLength = 58; // not actual rope distance fix later
	private int encoderFullRotations = 0;

	public ClimbCommand() {
		requires(Robot.climb);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.climb.halt();
		//climb.resetEncoder();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.climb.climbUp();
//		if (climb.getEncoder() == 360) {
//			encoderFullRotations++;
//		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		//return (circumferenceOfSpinnyThing * encoderFullRotations >= ropeLength);
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.climb.halt();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}