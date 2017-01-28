package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.DriveMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MecanumStrafeLeftCommand extends Command {

	public MecanumStrafeLeftCommand() {
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.driveSubsystem.getDriveMode() != DriveMode.MECHANUM)
    		Robot.driveSubsystem.setDriveMode(DriveMode.MECHANUM);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO set vals to strafe left
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