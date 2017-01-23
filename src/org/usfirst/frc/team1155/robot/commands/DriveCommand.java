package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.DriveMode;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command{

	
	private DriveMode desiredMode;
	
    public DriveCommand(DriveMode mode) {
        requires(Robot.driveSubsystem);
        desiredMode = mode;
    }
	
	@Override
	protected void initialize() {
		Robot.driveSubsystem.setDriveMode(desiredMode);
	}

	@Override
	protected void execute() {
		switch(Robot.driveSubsystem.getDriveMode()){
			case MECHANUM:
				Robot.driveSubsystem.setMechSpeed(OI.gamepad.getX(GenericHID.Hand.kLeft),OI.gamepad.getY(GenericHID.Hand.kLeft),OI.gamepad.getX(GenericHID.Hand.kRight));
				break;
			case TANK:
				Robot.driveSubsystem.setTankSpeed(OI.gamepad.getY(GenericHID.Hand.kLeft), OI.gamepad.getY(GenericHID.Hand.kRight));
				break;
			case TURN_FRONT:
				Robot.driveSubsystem.setTankSpeed(OI.gamepad.getY(GenericHID.Hand.kLeft), OI.gamepad.getY(GenericHID.Hand.kRight));
				break;
			case TURN_BACK:
				Robot.driveSubsystem.setTankSpeed(OI.gamepad.getY(GenericHID.Hand.kLeft), OI.gamepad.getY(GenericHID.Hand.kRight));
				break;
			default:
				//Defaults to full MECH
				Robot.driveSubsystem.setMechSpeed(OI.gamepad.getX(GenericHID.Hand.kLeft),OI.gamepad.getY(GenericHID.Hand.kLeft),OI.gamepad.getX(GenericHID.Hand.kRight));
				break;
		}
	}

	@Override
	protected boolean isFinished() {
		/*command has to be stopped when changing modes because 
		  front/back wheels are engaged in each initialize*/
		return Robot.driveSubsystem.getDriveMode() != desiredMode;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}

}
