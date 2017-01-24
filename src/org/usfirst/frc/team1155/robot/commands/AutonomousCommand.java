package org.usfirst.frc.team1155.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

	private boolean crossBorder = false;
	private boolean shootBalls = false;
	
	//possible order of auto commands
    public AutonomousCommand() {
        /*addSequential(new PositionDriveCommand());
    	addSequential(new DepositGearCommand());
    	if (crossBorder) {
    		addSequential(new DriveCommand());
    	}
    	if (shootBalls) {
    		addSequential(new DriveCommand());
    		addSeuqential(new ShootCommand());
    	}
    	addSequential(new PositionDriveCommand());*/
    }
}
