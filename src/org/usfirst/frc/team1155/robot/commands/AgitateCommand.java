package org.usfirst.frc.team1155.robot.commands;
import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.PortMap;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class AgitateCommand extends Command{
	
	
	public AgitateCommand(){
		requires(Robot.shootSubsystem);
    }
  
	protected void initialize(){
		Robot.shootSubsystem.setAgitatorServo(0);
	}
  
	protected void execute(){
		if(Robot.shootSubsystem.getAgitatorServo() != 0){
			Robot.shootSubsystem.setAgitatorServo(0);
		}else if(Robot.shootSubsystem.getAgitatorServo() != 1){
			Robot.shootSubsystem.setAgitatorServo(1);
		}else{
			Robot.shootSubsystem.setAgitatorServo(0);
		}
    
	}
  
	protected boolean isFinished(){
		return false;
	}
  
	protected void end(){
	  Robot.shootSubsystem.setAgitatorServo(0);
  
	}
  
	protected void interrupted(){
		end();
	}
}