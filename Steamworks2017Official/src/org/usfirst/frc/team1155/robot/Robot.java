
package org.usfirst.frc.team1155.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1155.robot.commands.AutonomousCommand;
import org.usfirst.frc.team1155.robot.commands.TankDriveCommand;
import org.usfirst.frc.team1155.robot.commands.AutonomousCommand.StartingPosition;
import org.usfirst.frc.team1155.robot.commands.DistanceDriveCommand;
import org.usfirst.frc.team1155.robot.commands.GyroTurnCommand;
import org.usfirst.frc.team1155.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1155.robot.subsystems.GearSubsystem;
import org.usfirst.frc.team1155.robot.subsystems.ShooterSubsystem;

public class Robot extends IterativeRobot {

	public static DriveSubsystem driveSubsystem; 
	public static ShooterSubsystem shooterSubsystem;
	public static GearSubsystem gearSubsystem;
	public static ClimberSubsystem climberSubsystem;
	
	public static OI oi;
	
	public static ADXRS450_Gyro gyro;
	
	public static RioDuinoController rioDuino;
	public static DriverStation.Alliance allianceColor;
	String rioDuinoLEDMode;
	

	@Override
	public void robotInit() {
    	gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

		driveSubsystem = new DriveSubsystem();
		gearSubsystem = new GearSubsystem();
		shooterSubsystem = new ShooterSubsystem();
		climberSubsystem = new ClimberSubsystem();

		oi = new OI();
		
		rioDuino = new RioDuinoController();
	}
	
	@Override
	public void teleopInit() {
		gyro.reset();
		driveSubsystem.resetEncoders();
		driveSubsystem.endAdjustment();
		
		rioDuino.SendString("green");

		new TankDriveCommand().start(); 
	//	new Compressor(0).start();
	}

	@Override
	public void teleopPeriodic() {
    	SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());

    	//testing servos
    	Robot.shooterSubsystem.leftShootServo.set(-OI.leftJoystick.getThrottle());
    	Robot.shooterSubsystem.rightShootServo.set(1+OI.leftJoystick.getThrottle());
    	
		Scheduler.getInstance().run();
	}
	
	@Override
	public void autonomousInit() {
		new AutonomousCommand(StartingPosition.POSITION_LEFT).start();
		
		allianceColor = DriverStation.getInstance().getAlliance();
		if (allianceColor == DriverStation.Alliance.Blue) {
			rioDuinoLEDMode = "autoBlue";
			rioDuino.SendString(rioDuinoLEDMode);
		} else {
			rioDuinoLEDMode = "autoRed";
			rioDuino.SendString(rioDuinoLEDMode);
		}	
		
	}

	@Override
	public void autonomousPeriodic() {
    	SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());

		Scheduler.getInstance().run();
	}

	@Override
	public void disabledInit() {	
		Robot.driveSubsystem.endAdjustment();
		
		if(shooterSubsystem.leftAgitatorServo != null && shooterSubsystem.rightAgitatorServo != null) {
			shooterSubsystem.stopAgitators();
		}
		
		if(rioDuino != null)
			rioDuino.SendString("disableInit");
		
		if(gyro != null) 
			gyro.reset();
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
