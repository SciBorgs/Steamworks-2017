package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.GearSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DepositGearCommand extends Command {
		// Shows if the arms on the shooting mechanism are open or closed
    	boolean isClosed = true;  
	
    public DepositGearCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearSubsystem);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gearSubsystem.enableUltrasonic();
    	Robot.gearSubsystem.close();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// If the arms on the gear mechanism are closed they will open
    	if(isClosed){
    		Robot.gearSubsystem.open();
    		isClosed = false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // If the arms on the gear mechanism are open and the gear is not in it(meaning that it is on the airship peg) the arms will close and the command will end 
    	if(!isClosed && !Robot.gearSubsystem.hasGear()){
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
