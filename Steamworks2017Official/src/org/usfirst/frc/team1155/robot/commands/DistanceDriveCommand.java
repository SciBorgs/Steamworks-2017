package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.SensorMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DistanceDriveCommand extends Command {
	
	public double distanceToDrive;
	
	//distance in inches
	public DistanceDriveCommand(double distance) {
        requires(Robot.driveSubsystem);

        Robot.driveSubsystem.getPIDController().setPercentTolerance(5);
        distanceToDrive = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.resetEncoders();
    	
    	Robot.driveSubsystem.sensorMode = SensorMode.ENCODER;
    	
		Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.getEncDistance(), distanceToDrive);
		Robot.driveSubsystem.getPIDController().setPID(SmartDashboard.getNumber("P", 0.1), SmartDashboard.getNumber("I", 0), SmartDashboard.getNumber("D", 0.1));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		SmartDashboard.putNumber("EncoderValue", Robot.driveSubsystem.getEncDistance());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return (Robot.driveSubsystem.getPIDController().getAvgError() < 0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Finished");
    	Robot.driveSubsystem.endAdjustment();
    	Robot.driveSubsystem.setTankSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}