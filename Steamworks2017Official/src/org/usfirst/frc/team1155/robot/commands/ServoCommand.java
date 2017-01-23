package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.ShooterSubsystem.ServoMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ServoCommand extends Command {

	private ServoMode currentMode;
	private boolean isFinished = false;
		
    public ServoCommand(ServoMode mode) {
    	//requires(Robot.shooterSubsystem);
        currentMode = mode;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println(Robot.shooterSubsystem.shootServo.getPosition());
    	double currentPosition = Robot.shooterSubsystem.shootServo.getPosition();
		if(currentMode == ServoMode.MODE1) {
			Robot.shooterSubsystem.setPosition(currentPosition + 0.1);
		}else {
			Robot.shooterSubsystem.setPosition(currentPosition - 0.1);	
		}
		isFinished = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
//		double servoCurrent = Robot.servoSubsystem.shootServo.getPosition();
//
//		if(Robot.driveSubsystem.rightJoystick.getRawButton(3)) {
//    		Robot.servoSubsystem.setPosition(servoCurrent + 0.05);
//    	}else if (Robot.driveSubsystem.rightJoystick.getRawButton(4)) {
//    		Robot.servoSubsystem.setPosition(servoCurrent - 0.05);
//    	}
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
