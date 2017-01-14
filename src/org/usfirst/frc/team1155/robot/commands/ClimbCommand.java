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

	private ClimbSubsystem climb = Robot.climb;

	public ClimbCommand() {
		requires(Robot.climb);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		climb.halt();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (cButton.get()) {
			climb.climbUp();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
