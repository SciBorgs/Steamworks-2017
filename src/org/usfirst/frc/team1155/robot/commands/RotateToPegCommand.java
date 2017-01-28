package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToPegCommand extends Command {

	private String directionToRotate;
	private double initialAngle;
	private final double TURN_SPEED = 0.25;
	
    public RotateToPegCommand(String dir) {
       directionToRotate = dir;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//TODO change values to turn robot 
    	initialAngle = Robot.gyro.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.setMechSpeed(0, 0, TURN_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (directionToRotate.equals("left") && Robot.gyro.getAngle() <= -60) ||
    			(directionToRotate.equals("right") && Robot.gyro.getAngle() >= 60);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.setMechSpeed(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
