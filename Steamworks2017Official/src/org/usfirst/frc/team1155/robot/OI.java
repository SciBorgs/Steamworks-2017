package org.usfirst.frc.team1155.robot;

import org.usfirst.frc.team1155.robot.commands.MechanumDriveCommand;
import org.usfirst.frc.team1155.robot.commands.ShootCommand;
import org.usfirst.frc.team1155.robot.commands.TankDriveCommand;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.DriveMode;
import org.usfirst.frc.team1155.robot.subsystems.ShooterSubsystem.ServoPosition;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI  {

	public static Joystick leftJoystick =  new Joystick(PortMap.JOYSTICK_LEFT);
	public static Joystick rightJoystick = new Joystick(PortMap.JOYSTICK_RIGHT);

	public OI() {
		
		/* TEST COMMANDS
		* new JoystickButton(Robot.driveSubsystem.rightJoystick, 1).whenPressed(new SteadySpeedCommand());
		* new JoystickButton(Robot.driveSubsystem.rightJoystick, 3).whenPressed(new TankDriveCommand(DriveMode.TANK));
		* new JoystickButton(Robot.driveSubsystem.rightJoystick, 4).whenPressed(new MechanumDriveCommand());
		* new JoystickButton(Robot.driveSubsystem.rightJoystick, 2).whenPressed(new GyroTurnCommand(180));
		* new JoystickButton(OI.leftJoystick, 2).whenPressed(new GyroTurnCommand(-180));
		* new JoystickButton(rightJoystick, 3).whenPressed(new ShootCommand(ServoPosition.POSITION1));
		* new JoystickButton(rightJoystick, 4).whenPressed(new ShootCommand(ServoPosition.POSITION2));
		*/
		
		new JoystickButton(OI.rightJoystick, 3).whenPressed(new MechanumDriveCommand());
		new JoystickButton(OI.rightJoystick, 4).whenPressed(new TankDriveCommand(DriveMode.TANK));
		
		new JoystickButton(OI.rightJoystick, 1).whenPressed(new ShootCommand(ServoPosition.POSITION_DOWN));
		new JoystickButton(OI.leftJoystick, 1).whenPressed(new ShootCommand(ServoPosition.POSITION_UP));
		
		
	}
}
