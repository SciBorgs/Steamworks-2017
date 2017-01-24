package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.ShooterSubsystem.ServoPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ServoCommand extends Command {
	
	// dont judge me ok we had no time
	private boolean isFinished = false;
		
	private ServoPosition servoPos;
	
    public ServoCommand(ServoPosition servoPosition) {
    	requires(Robot.shooterSubsystem);
    	
    	servoPos = servoPosition;
    }

    protected void initialize() {
        Robot.shooterSubsystem.setServoPosition(servoPos);
        isFinished = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
