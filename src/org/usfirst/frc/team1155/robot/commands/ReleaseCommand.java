package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.PortMap;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReleaseCommand extends Command {
	
	private static ShooterSubsystem servo = new ShooterSubsystem();

    public ReleaseCommand() {
		
		requires(Robot.shootSubsystem);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    private void requires(ShooterSubsystem shootsubsystem) {
		// TODO Auto-generated method stub
		
	}

	// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	servo.toggleRelease();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
