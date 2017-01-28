package org.usfirst.frc.team1155.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

	//private boolean crossBorder = false;
	private boolean shootBalls = false;
	private final double DISTANCE_TO_BASELINE = 93.25; //inches
	private final double DISTANCE_TO_PIVOT = 20.35; //inches
	
	public enum StartingPosition{
		POSITION_1, //left driver station
		POSITION_2, //middle driver station
		POSITION_3; //right driver station
	}
	
	
	//possible order of auto commands
    public AutonomousCommand(StartingPosition pos) {
    	 switch(pos){
         case POSITION_1: 
        	 addSequential(new DistanceDriveCommand(
          			DISTANCE_TO_BASELINE + DISTANCE_TO_PIVOT));
          	addSequential(new RotateToPegCommand("right"));
          	addSequential(new DepositGearCommand());
          	break;
         case POSITION_3:
         	addSequential(new DistanceDriveCommand(
         			DISTANCE_TO_BASELINE + DISTANCE_TO_PIVOT));
         	addSequential(new RotateToPegCommand("left"));
         	addSequential(new DepositGearCommand());
         	break;
         case POSITION_2:
        	addSequential(new DistanceDriveCommand(DISTANCE_TO_BASELINE));
        	addSequential(new VisionCommand());
         	break;
         default:
         	break;
         }
    	 
    	 
    	 
        /*addSequential(new DistanceDriveCommand());
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
