package org.usfirst.frc.team1155.robot;

import org.usfirst.frc.team1155.robot.subsystems.ClimbSubsystem;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1155.robot.subsystems.GearSubsystem;
import org.usfirst.frc.team1155.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static DriveSubsystem driveSubsystem;
	public static ClimbSubsystem climb;
	public static GearSubsystem gearSubsystem;
	public static ShooterSubsystem shootSubsystem;
	public static OI oi;
	public static ADXRS450_Gyro gyro;

	Command autonomousCommand;
	NetworkTable table;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		driveSubsystem = new DriveSubsystem();
		climb = new ClimbSubsystem();
		gearSubsystem = new GearSubsystem();
		shootSubsystem = new ShooterSubsystem();
		gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
		oi = new OI();
		// instantiate the command used for the autonomous period
		// autonomousCommand = new ExampleCommand();
		table = NetworkTable.getTable("datatable");

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		oi = new OI();
		oi.start();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	@Override
	public void disabledInit() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
