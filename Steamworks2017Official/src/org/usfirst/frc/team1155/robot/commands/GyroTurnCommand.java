package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GyroTurnCommand extends Command {

	private double initialAngle, degreesToTurn;
	
	private final double TURN_SPEED = 0.25;
	
    public GyroTurnCommand(double degrees) {
    	degreesToTurn = degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    //    initialAngle = Robot.gyro.getAngle();
		SmartDashboard.putNumber("Initial Angle",  initialAngle);   
		SmartDashboard.putNumber("Degrees To Turn",  degreesToTurn);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		SmartDashboard.putNumber("turn to ",  Math.abs(degreesToTurn + initialAngle));
    	Robot.driveSubsystem.setMechSpeed(0, 0, TURN_SPEED * (degreesToTurn/Math.abs(degreesToTurn)));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(degreesToTurn > 0) {
 //           return !(Robot.gyro.getAngle() < (degreesToTurn + initialAngle));		
    	}else {
 //   		return !(Robot.gyro.getAngle() > (degreesToTurn + initialAngle)); 
    	}
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("FINISHED");
    	Robot.driveSubsystem.setMechSpeed(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
