package org.usfirst.frc.team1155.robot;

import org.usfirst.frc.team1155.robot.commands.ClimbCommand;
import org.usfirst.frc.team1155.robot.commands.DriveCommand;
import org.usfirst.frc.team1155.robot.commands.ReleaseCommand;
import org.usfirst.frc.team1155.robot.commands.ShootCommand;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.DriveMode;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI extends Command{
    
	public static XboxController gamepad = new XboxController(PortMap.XBOX_CONTROLLER);
	public Command drive;
	
	//private JoystickButton tank,mech,frontMech,backMech, climb, shoot, release;
	
	
	public OI() { //change these buttons later
		tank = new JoystickButton(gamepad, PortMap.XBOX_CONTROLLER_A);
		mech = new JoystickButton(gamepad, PortMap.XBOX_CONTROLLER_B);
		frontMech = new JoystickButton(gamepad, PortMap.XBOX_CONTROLLER_X);
		backMech = new JoystickButton(gamepad, PortMap.XBOX_CONTROLLER_Y);
		climb = new JoystickButton(gamepad, PortMap.XBOX_CONTROLLER_LB);
		shoot = new JoystickButton(gamepad, PortMap.XBOX_CONTROLLER_RT);
		release = new JoystickButton(gamepad, PortMap.XBOX_CONTROLLER_RB);
		
		drive = new DriveCommand(DriveMode.MECHANUM);
	}
	
	protected void initialize() {
		drive.start();
	}
	
	protected void execute(){
//		tank.whenPressed(new DriveCommand(DriveMode.TANK));
//		mech.whenPressed(new DriveCommand(DriveMode.MECHANUM));
//		frontMech.whenPressed(new DriveCommand(DriveMode.TURN_FRONT));
//		backMech.whenPressed(new DriveCommand(DriveMode.TURN_BACK));
//		climb.whenPressed(new ClimbCommand());
//		shoot.whenPressed(new ShootCommand());
//		release.whenPressed(new ReleaseCommand());
	}

	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
}

