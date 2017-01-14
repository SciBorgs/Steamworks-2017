package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;


/**
 *
 */
public class DepositGearCommand extends Command {

    	boolean ifClosed = true;
    	
	public DepositGearCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearSubsystem);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gearSubsystem.close();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(ifClosed){
    		Robot.gearSubsystem.open();
    		ifClosed = false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(!ifClosed && !Robot.gearSubsystem.hasGear()){
        	Robot.gearSubsystem.close();
        	return true;
        }
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
