package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.DriveMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

	// private boolean crossBorder = false;
	// private boolean shootBalls = false;
	private final double DISTANCE_TO_BASELINE = 93.25; // inches
	private final double DISTANCE_TO_PIVOT = 20.35; // inches

	public enum StartingPosition {
		POSITION_LEFT, // left driver station
		POSITION_MIDDLE, // middle driver station
		POSITION_RIGHT; // right driver station
	}

	// possible order of auto commands
	public AutonomousCommand(StartingPosition pos) {
		Robot.driveSubsystem.setDriveMode(DriveMode.MECHANUM);
		switch (pos) {
		case POSITION_LEFT:
			addSequential(new DistanceDriveCommand(57));//DISTANCE_TO_BASELINE + DISTANCE_TO_PIVOT));
			addSequential(new DepositGearCommand());
			addSequential(new DistanceDriveCommand(-57));
			//addSequential(new GyroTurnCommand(60));
//			addSequential(new VisionAlignCommand());
//			addSequential(new DepositGearCommand());
			break;
		case POSITION_RIGHT:
			addSequential(new DistanceDriveCommand(DISTANCE_TO_BASELINE + DISTANCE_TO_PIVOT));
			addSequential(new GyroTurnCommand(-60));
			addSequential(new VisionAlignCommand());
			addSequential(new DepositGearCommand());
			break;
		case POSITION_MIDDLE:
			//57
			addSequential(new DistanceDriveCommand(DISTANCE_TO_BASELINE));
			addSequential(new VisionAlignCommand());
			addSequential(new DepositGearCommand());
			break;
		default:
			break;
		}

		/*
		 * addSequential(new DistanceDriveCommand()); addSequential(new
		 * DepositGearCommand()); if (crossBorder) { addSequential(new
		 * DriveCommand()); } if (shootBalls) { addSequential(new
		 * DriveCommand()); addSeuqential(new ShootCommand()); }
		 * addSequential(new PositionDriveCommand());
		 */
	}
}
