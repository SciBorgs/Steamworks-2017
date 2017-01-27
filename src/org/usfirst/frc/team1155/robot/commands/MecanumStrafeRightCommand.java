package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MecanumStrafeRightCommand extends Command {

	public MecanumStrafeRightCommand() {
        requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.driveSubsystem.getDriveMode() != Robot.driveSubsystem.driveMode.MECHANUM)
    		Robot.driveSubsystem.setDriveMode(Robot.driveSubsystem.driveMode.MECHANUM);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO set proper values to strafe right
    	Robot.driveSubsystem.setMechSpeed(90, 90, 90);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
