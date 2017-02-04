package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionAlignCommand extends Command {
	
	private double distToTape;
	private double anglePOV;
	private final double MIN_PEG_DISTANCE = 1; //inches
	private final double INIT_ANGLE = 60; //degrees; initial angle of the gyro
	private final double INIT_DISTANCE = 12; //inches; initial distance from the peg
	private final double ANGLE_BUFFER = 5; //degrees
	private boolean alignMode;	//rotate >> true; drive >> false
	Command gyroTurnCommand;
	Command mechanumDriveCommand;
	
    public VisionAlignCommand() {
        requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// UNNEEDED alignMode = true;
    	Robot.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	anglePOV = SmartDashboard.getNumber("Theta: ", INIT_ANGLE);
    	distToTape = SmartDashboard.getNumber("Distance: ", INIT_DISTANCE);
    
		if(Math.abs(anglePOV) > ANGLE_BUFFER){
			//rotate first
			gyroTurnCommand = new GyroTurnCommand((Math.abs(anglePOV) - ANGLE_BUFFER) * (anglePOV/-Math.abs(anglePOV)));
			gyroTurnCommand.start();
			//then go the distance necessary from the initial value 
			Robot.driveSubsystem.strafeDiagonal(90-anglePOV);
		}
    			
    }

    // Make this return true when this Command no longer needs to run execute()
    //Check to see whether it is both within angle buffer from tape and
    //within the distance
    protected boolean isFinished() {
        return distToTape <= MIN_PEG_DISTANCE && (anglePOV > -ANGLE_BUFFER && anglePOV < ANGLE_BUFFER);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
