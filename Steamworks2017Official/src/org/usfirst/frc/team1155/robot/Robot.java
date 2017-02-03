
package org.usfirst.frc.team1155.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1155.robot.commands.MechanumDriveCommand;
import org.usfirst.frc.team1155.robot.commands.ShootCommand;
import org.usfirst.frc.team1155.robot.commands.SteadySpeedCommand;
import org.usfirst.frc.team1155.robot.commands.TankDriveCommand;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1155.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team1155.robot.subsystems.ShooterSubsystem.ServoPosition;

public class Robot extends IterativeRobot {

	public static DriveSubsystem driveSubsystem; 
	public static ShooterSubsystem shooterSubsystem;
	
	public static OI oi = new OI();
//	public static ADXRS450_Gyro gyro;

	@Override
	public void robotInit() {
    	//gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

		driveSubsystem = new DriveSubsystem();
		shooterSubsystem = new ShooterSubsystem();
	}
	
	@Override
	public void teleopInit() {
		//gyro.reset();
		
		new TankDriveCommand().start(); 
		new Compressor(0).start();
	}

	@Override
	public void teleopPeriodic() {
//    	SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());
		Scheduler.getInstance().run();
	}
	
	@Override
	public void autonomousInit() {
		
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}


	@Override
	public void disabledInit() {
		//gyro.reset();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
