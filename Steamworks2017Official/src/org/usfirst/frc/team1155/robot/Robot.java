package org.usfirst.frc.team1155.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1155.robot.commands.AutonomousCommand;
import org.usfirst.frc.team1155.robot.commands.AutonomousCommand.AutoRoutine;
import org.usfirst.frc.team1155.robot.commands.TankDriveCommand;
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

    public static AutoRoutine autoRoutine;

    public static RioDuinoController rioDuino;
    public static DriverStation.Alliance allianceColor;
    public static Compressor compressor;
    public static boolean isInTeleop;
    String rioDuinoLEDMode;

    @Override
    public void robotInit() {
        gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

        driveSubsystem = new DriveSubsystem();
        gearSubsystem = new GearSubsystem();
        shooterSubsystem = new ShooterSubsystem();
        climberSubsystem = new ClimberSubsystem();

        compressor = new Compressor(0);

        oi = new OI();

        rioDuino = new RioDuinoController();

        isInTeleop = false;
    }

    @Override
    public void disabledInit() {
        Robot.driveSubsystem.endAdjustment();

        if (shooterSubsystem.leftAgitatorServo != null && shooterSubsystem.rightAgitatorServo != null) {
            shooterSubsystem.stopAgitators();
        }
    }

    @Override
    public void autonomousInit() {
        isInTeleop = false;

        switch (SmartDashboard.getString("Auto Routine: ").toLowerCase()) {
            case "gear left":
                autoRoutine = AutoRoutine.GEAR_LEFT;
                break;
            case "gear right":
                autoRoutine = AutoRoutine.GEAR_RIGHT;
                break;
            case "gear middle":
                autoRoutine = AutoRoutine.GEAR_MIDDLE;
                break;
            case "shoot red":
                autoRoutine = AutoRoutine.SHOOT_RED;
                break;
            case "shoot blue":
                autoRoutine = AutoRoutine.SHOOT_BLUE;
                break;
            default:
                autoRoutine = AutoRoutine.NOTHING;
                break;
        }

        autoRoutine = AutoRoutine.GEAR_MIDDLE;
        new AutonomousCommand(autoRoutine).start();
    }

    @Override
    public void teleopInit() {
        driveSubsystem.resetEncoders();
        driveSubsystem.endAdjustment();

        isInTeleop = true;

        new TankDriveCommand().start();
    }

    @Override
    public void testInit() {
        compressor = new Compressor(0);
        compressor.start();
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousPeriodic() {
        //SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());

        Scheduler.getInstance().run();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();

        //testing servos
        Robot.shooterSubsystem.leftShootServo.set(-OI.leftJoystick.getThrottle());
        Robot.shooterSubsystem.rightShootServo.set(1 + OI.leftJoystick.getThrottle());

        if (OI.leftJoystick.getRawButton(1) || OI.rightJoystick.getRawButton(1)) {
            compressor.stop();
        } else if (!compressor.enabled()) {
            compressor.start();
        }

        SmartDashboard.putBoolean("Compressor on: ", compressor.enabled());
    }

    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }
}
