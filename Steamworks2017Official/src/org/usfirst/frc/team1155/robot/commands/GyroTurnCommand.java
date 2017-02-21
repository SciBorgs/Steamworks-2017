package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.SensorMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GyroTurnCommand extends Command {

	private double initialAngle, degreesToTurn;
	
	
    public GyroTurnCommand(double degrees) {
    	degreesToTurn = degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialAngle = Robot.gyro.getAngle();

    	Robot.driveSubsystem.sensorMode = SensorMode.GYRO;
    	
    	Robot.driveSubsystem.startAdjustment(initialAngle, initialAngle + degreesToTurn);
		// Sets PID of the PID controller to the values given on the SmartDashboard
		Robot.driveSubsystem.getPIDController().setPID(SmartDashboard.getNumber("P"), SmartDashboard.getNumber("I"), SmartDashboard.getNumber("D"));
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return (Robot.driveSubsystem.getPIDController().getAvgError() < 0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("FInished gyro");
    	Robot.driveSubsystem.setTankSpeed(0, 0);
    	Robot.driveSubsystem.endAdjustment();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
