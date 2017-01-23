package org.usfirst.frc.team1155.robot;

import org.usfirst.frc.team1155.robot.commands.GyroDriveStraightCommand;
import org.usfirst.frc.team1155.robot.commands.GyroTurnCommand;
import org.usfirst.frc.team1155.robot.commands.MechanumDriveCommand;
import org.usfirst.frc.team1155.robot.commands.ServoCommand;
import org.usfirst.frc.team1155.robot.commands.SteadySpeedCommand;
import org.usfirst.frc.team1155.robot.commands.TankDriveCommand;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.DriveMode;
import org.usfirst.frc.team1155.robot.subsystems.ShooterSubsystem.ServoPosition;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI  {

	public static Joystick leftJoystick =  new Joystick(PortMap.JOYSTICK_LEFT);
	public static Joystick rightJoystick = new Joystick(PortMap.JOYSTICK_RIGHT);

	public OI() {
//		new JoystickButton(Robot.driveSubsystem.rightJoystick, 1).whenPressed(new SteadySpeedCommand());
//		new JoystickButton(Robot.driveSubsystem.rightJoystick, 3).whenPressed(new TankDriveCommand(DriveMode.TANK));
//		new JoystickButton(Robot.driveSubsystem.rightJoystick, 4).whenPressed(new MechanumDriveCommand());
//		new JoystickButton(Robot.driveSubsystem.rightJoystick, 2).whenPressed(new GyroTurnCommand(180));
//		new JoystickButton(OI.leftJoystick, 2).whenPressed(new GyroTurnCommand(-180));
		new JoystickButton(rightJoystick, 3).whenPressed(new ServoCommand(ServoPosition.POSITION1));
		new JoystickButton(rightJoystick, 4).whenPressed(new ServoCommand(ServoPosition.POSITION2));
	}		
}
