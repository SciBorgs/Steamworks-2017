package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToPegCommand extends Command {

	private String rotate;
	
    public RotateToPegCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//TODO change values to turn robot 
    	if (rotate == "left") 
    		Robot.driveSubsystem.setMechSpeed(-1, 1, 0); 
    	else
    		Robot.driveSubsystem.setMechSpeed(1, -1, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
        //return Robot.visionSubsystem.isPegTape();
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
