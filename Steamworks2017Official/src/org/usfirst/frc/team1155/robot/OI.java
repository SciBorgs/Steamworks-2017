package org.usfirst.frc.team1155.robot;

import org.usfirst.frc.team1155.robot.commands.GearCommand;
import org.usfirst.frc.team1155.robot.commands.GyroTurnCommand;
import org.usfirst.frc.team1155.robot.commands.MechanumDriveCommand;
import org.usfirst.frc.team1155.robot.commands.ShootCommand;
import org.usfirst.frc.team1155.robot.commands.ShootCommand.ShooterSide;
import org.usfirst.frc.team1155.robot.commands.TankDriveCommand;
import org.usfirst.frc.team1155.robot.subsystems.GearSubsystem.GearPosition;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI  {

	public static Joystick leftJoystick, rightJoystick;

	public OI() {
		
		leftJoystick = new Joystick(PortMap.JOYSTICK_LEFT);
		rightJoystick = new Joystick(PortMap.JOYSTICK_RIGHT);
		
		new JoystickButton(OI.rightJoystick, 3).whenPressed(new TankDriveCommand());
		new JoystickButton(OI.rightJoystick, 4).whenPressed(new MechanumDriveCommand());
		
		new JoystickButton(OI.rightJoystick, 1).whenPressed(new GearCommand(GearPosition.OPEN));
		new JoystickButton(OI.leftJoystick, 1).whenPressed(new GearCommand(GearPosition.CLOSE));
		
		//new JoystickButton(OI.rightJoystick, 5).whenPressed(new GyroTurnCommand(180));
	}
}
