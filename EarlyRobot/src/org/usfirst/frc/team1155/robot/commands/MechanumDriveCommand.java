package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.DriveMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MechanumDriveCommand extends Command {

	private Joystick lateralMovement, rotationalMovement;
	
    public MechanumDriveCommand() {
        requires(Robot.driveSubsystem);
        
        lateralMovement = Robot.driveSubsystem.rightJoystick;
        rotationalMovement = Robot.driveSubsystem.leftJoystick;
    }

    @Override
	protected void initialize() {
    	Robot.driveSubsystem.setMechSpeed(0, 0, 0);
    	Robot.driveSubsystem.setDriveMode(DriveMode.MECHANUM);
    }

    @Override
	protected void execute() {    	
    	Robot.driveSubsystem.setSpeed(lateralMovement, rotationalMovement);
    }

    @Override
	protected boolean isFinished() {
        return false;
    }

    @Override
	protected void end() {
    	
    }

    @Override
	protected void interrupted() {
    	
    }
}
