package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DistanceDriveCommand extends Command {
	
	private double distanceDriven;
	public double distanceToDrive;
	
	public DistanceDriveCommand(double distance) {
        requires(Robot.driveSubsystem);
        distanceDriven = 0;
        //TODO set proper speed for going straight
        distanceToDrive = distance;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.resetEncoders();
    	Robot.driveSubsystem.setMechSpeed(1, 1, 90);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	distanceDriven = Robot.driveSubsystem.getEncDistance();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return distanceDriven >= distanceToDrive;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.setMechSpeed(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
