package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DistanceDriveCommand extends Command {
	
	public double distanceToDrive;
	
	//distance in inches
	public DistanceDriveCommand(double distance) {
        requires(Robot.driveSubsystem);

        distanceToDrive = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.resetEncoders();
    	Robot.driveSubsystem.setTankSpeed(0.75, 0.75);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	  
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSubsystem.getEncDistance() >= distanceToDrive;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.setTankSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
