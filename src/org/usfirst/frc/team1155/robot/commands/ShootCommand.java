package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCommand extends Command {
	public ShootCommand() {
		requires(Robot.shootSubsystem);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	private void requires(ShooterSubsystem shoot) {
		// TODO Auto-generated method stub

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.shootSubsystem.setWheelSpeed(0);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.shootSubsystem.setWheelSpeed(OI.gamepad.getTriggerAxis(GenericHID.Hand.kRight));
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.shootSubsystem.setWheelSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
