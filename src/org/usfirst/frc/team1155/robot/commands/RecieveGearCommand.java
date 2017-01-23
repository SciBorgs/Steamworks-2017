package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.GearSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RecieveGearCommand extends Command {
	// short for gear opportunity and is true when there is a situation where the gear can get into the robot
	boolean gearOpp = false;
	private GearSubsystem gearSubsystem = Robot.gearSubsystem;
	
    public RecieveGearCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(gearSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gearSubsystem.close();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!gearOpp && gearSubsystem.validDistFromWall()){
    		gearSubsystem.open();
    		gearOpp = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(gearOpp && gearSubsystem.hasGear()){
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
