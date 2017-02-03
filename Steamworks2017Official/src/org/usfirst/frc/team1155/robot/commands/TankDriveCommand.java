package org.usfirst.frc.team1155.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.DriveMode;

/**
 *
 */
public class TankDriveCommand extends Command {
	
	private DriveMode mode;
	
	public TankDriveCommand() {
	}

	@Override
	protected void initialize() {
		System.out.println("Tank Drive COmmand");
		Robot.driveSubsystem.setDriveMode(DriveMode.TANK);
		Robot.driveSubsystem.setTankSpeed(0, 0);
	}

	@Override
	protected void execute() {
		Robot.driveSubsystem.setSpeed(OI.rightJoystick, OI.leftJoystick);
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
