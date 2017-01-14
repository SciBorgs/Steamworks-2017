package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.DriveMode;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

public class TankDriveCommand extends Command{

	
	   
    public TankDriveCommand() {
        requires(Robot.driveSubsystem);
    }
	
	@Override
	protected void initialize() {
		Robot.driveSubsystem.setDriveMode(DriveMode.TANK);
	}

	@Override
	protected void execute() {
		Robot.driveSubsystem.setTankSpeed(OI.gamepad.getY(GenericHID.Hand.kLeft), OI.gamepad.getY(GenericHID.Hand.kRight));
	}

	@Override
	protected boolean isFinished() {
		return Robot.driveSubsystem.getDriveMode() != DriveMode.TANK;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}

}
