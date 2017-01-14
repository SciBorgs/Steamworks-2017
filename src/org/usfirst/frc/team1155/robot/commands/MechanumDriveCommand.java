package org.usfirst.frc.team1155.robot.commands;
 
import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.DriveMode;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
 
/**
 *
 */
public class MechanumDriveCommand extends Command {
 
   
    public MechanumDriveCommand() {
        requires(Robot.driveSubsystem);
       
    }
 
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.driveSubsystem.setDriveMode(DriveMode.MECHANUM);
    }
 
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.setMechSpeed(OI.gamepad.getX(GenericHID.Hand.kLeft),OI.gamepad.getY(GenericHID.Hand.kLeft),OI.gamepad.getX(GenericHID.Hand.kRight));
    }
 
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSubsystem.getDriveMode() != DriveMode.MECHANUM;
    }
 
    // Called once after isFinished returns true
    protected void end() {
    }
 
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}