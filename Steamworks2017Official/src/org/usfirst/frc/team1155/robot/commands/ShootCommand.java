package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.ShooterSubsystem.ServoPosition;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCommand extends Command {
	
	public enum ShooterSide {
		LEFT, RIGHT;
	}
	
	private ShooterSide shooterSide;

    public ShootCommand(ShooterSide side) {    	
    	shooterSide = side;
    }

    protected void initialize() {
//        Robot.shooterSubsystem.setServoPosition(getCorrectServoPosition());        
//        
//        if(shooterSide == ShooterSide.LEFT) {
//        	Robot.shooterSubsystem.setLeftShooter(1,1);
//        }else if(shooterSide == ShooterSide.RIGHT) {
//        	Robot.shooterSubsystem.setRightShooter(1,1);
//        }
    }
    
//    private ServoPosition getCorrectServoPosition() {
//    	//logic goes here
//    	return ServoPosition.POSITION_1;
//    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (shooterSide == ShooterSide.LEFT) ? !OI.leftJoystick.getRawButton(1) : !OI.rightJoystick.getRawButton(1);
    }

    // Called once after isFinished returns true
    protected void end() {
//        if(shooterSide == ShooterSide.LEFT) {
//        	Robot.shooterSubsystem.setLeftShooter(0,0);
//        }else if(shooterSide == ShooterSide.RIGHT) {
//        	Robot.shooterSubsystem.setRightShooter(0,0);
//        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
