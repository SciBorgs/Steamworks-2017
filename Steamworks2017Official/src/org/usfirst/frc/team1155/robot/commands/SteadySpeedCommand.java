package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SteadySpeedCommand extends Command {

	private double currentSpeed;
	
    public SteadySpeedCommand() {
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.driveSubsystem.setTankSpeed(Robot.driveSubsystem.leftJoystick.getY(), Robot.driveSubsystem.leftJoystick.getY());
    	if(!Robot.driveSubsystem.rightJoystick.getRawButton(1)) {
        	Robot.driveSubsystem.setTankSpeed(Robot.driveSubsystem.rightJoystick.getY(), Robot.driveSubsystem.rightJoystick.getY());
    	}
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