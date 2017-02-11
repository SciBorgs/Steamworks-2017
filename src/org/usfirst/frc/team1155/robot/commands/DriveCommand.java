package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.RioDuinoSlaveController;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.DriveMode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {
	private DriveMode desiredMode;
	public String rioDuinoDrive;
	public Alliance allianceColor;
	RioDuinoSlaveController rioDuino;

	public DriveCommand(DriveMode mode) {
		requires(Robot.driveSubsystem);
		desiredMode = mode;
	}

	@Override
	protected void initialize() {
		Robot.driveSubsystem.setDriveMode(desiredMode);
		//IGNORE UGLINESS PLEASE FIX IF POSSIBLE - Karun
		allianceColor = DriverStation.getInstance().getAlliance();
		if (allianceColor == DriverStation.Alliance.Blue) {
			if (Robot.driveSubsystem.getDriveMode() == DriveMode.MECHANUM) {
				rioDuinoDrive = "mechBlue";
				rioDuino.SendString(rioDuinoDrive);
			} else if (Robot.driveSubsystem.getDriveMode() == DriveMode.TANK) {
				rioDuinoDrive = "tankBlue";
				rioDuino.SendString(rioDuinoDrive);
			} else if (Robot.driveSubsystem.getDriveMode() == DriveMode.TURN_FRONT) {
				rioDuinoDrive = "frontBlue";
				rioDuino.SendString(rioDuinoDrive);
			} else if (Robot.driveSubsystem.getDriveMode() == DriveMode.TURN_BACK) {
				rioDuinoDrive = "backBlue";
				rioDuino.SendString(rioDuinoDrive);
			} else {
				rioDuinoDrive = "mechBlue";
				rioDuino.SendString(rioDuinoDrive);
			}
		} else {
			if (Robot.driveSubsystem.getDriveMode() == DriveMode.MECHANUM) {
				rioDuinoDrive = "mechRed";
				rioDuino.SendString(rioDuinoDrive);
			} else if (Robot.driveSubsystem.getDriveMode() == DriveMode.TANK) {
				rioDuinoDrive = "tankRed";
				rioDuino.SendString(rioDuinoDrive);
			} else if (Robot.driveSubsystem.getDriveMode() == DriveMode.TURN_FRONT) {
				rioDuinoDrive = "frontRed";
				rioDuino.SendString(rioDuinoDrive);
			} else if (Robot.driveSubsystem.getDriveMode() == DriveMode.TURN_BACK) {
				rioDuinoDrive = "backRed";
				rioDuino.SendString(rioDuinoDrive);
			} else {
				rioDuinoDrive = "mechRed";
				rioDuino.SendString(rioDuinoDrive);
			}
		}
	}

	@Override
	protected void execute() {
		switch (Robot.driveSubsystem.getDriveMode()) {
		case MECHANUM:
			Robot.driveSubsystem.setMechSpeed(OI.gamepad.getX(GenericHID.Hand.kLeft),
					OI.gamepad.getY(GenericHID.Hand.kLeft), OI.gamepad.getX(GenericHID.Hand.kRight));
			break;
		case TANK:
			Robot.driveSubsystem.setTankSpeed(OI.gamepad.getY(GenericHID.Hand.kLeft),
					OI.gamepad.getY(GenericHID.Hand.kRight));
			break;
		case TURN_FRONT:
			Robot.driveSubsystem.setTankSpeed(OI.gamepad.getY(GenericHID.Hand.kLeft),
					OI.gamepad.getY(GenericHID.Hand.kRight));
			break;
		case TURN_BACK:
			Robot.driveSubsystem.setTankSpeed(OI.gamepad.getY(GenericHID.Hand.kLeft),
					OI.gamepad.getY(GenericHID.Hand.kRight));
			break;
		default:
			// Defaults to full MECH
			Robot.driveSubsystem.setMechSpeed(OI.gamepad.getX(GenericHID.Hand.kLeft),
					OI.gamepad.getY(GenericHID.Hand.kLeft), OI.gamepad.getX(GenericHID.Hand.kRight));
			break;
		}
	}

	@Override
	protected boolean isFinished() {
		/*
		 * command has to be stopped when changing modes because front/back
		 * wheels are engaged in each initialize
		 */
		return Robot.driveSubsystem.getDriveMode() != desiredMode;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

	}

}
