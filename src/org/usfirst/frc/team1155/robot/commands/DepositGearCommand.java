package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.GearSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DepositGearCommand extends Command {
		// Shows if the arms on the shooting mechanism are open or closed
    	boolean ifClosed = true; 
    	private GearSubsystem gearSubsystem = Robot.gearSubsystem;  
	
    public DepositGearCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(gearSubsystem);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gearSubsystem.enableUltrasonic();
    	gearSubsystem.close();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// If the arms on the gear mechanism are closed they will open
    	if(ifClosed){
    		gearSubsystem.open();
    		ifClosed = false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // If the arms on the gear mechanism are open and the gear is not in it(meaning that it is on the airship peg) the arms will close and the command will end 
    	if(!ifClosed && !gearSubsystem.hasGear()){
    		gearSubsystem.close();
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
