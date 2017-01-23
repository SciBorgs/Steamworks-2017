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
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public static XboxController gamepad = new XboxController(PortMap.XBOX_CONTROLLER);
	
	private JoystickButton tank,mech,frontMech,backMech, climb, shoot, release;
	
	
	public OI() { //change these buttons later
		tank = new JoystickButton(gamepad, PortMap.XBOX_CONTROLLER_A);
		mech = new JoystickButton(gamepad, PortMap.XBOX_CONTROLLER_B);
		frontMech = new JoystickButton(gamepad, PortMap.XBOX_CONTROLLER_X);
		backMech = new JoystickButton(gamepad, PortMap.XBOX_CONTROLLER_Y);
		climb = new JoystickButton(gamepad, PortMap.XBOX_CONTROLLER_LB);
		shoot = new JoystickButton(gamepad, PortMap.XBOX_CONTROLLER_RT);
		release = new JoystickButton(gamepad, PortMap.XBOX_CONTROLLER_RB);
	}
	
	protected void initialize() {
	}
	
	protected void execute(){
		tank.whenPressed(new DriveCommand(DriveMode.TANK));
		mech.whenPressed(new DriveCommand(DriveMode.MECHANUM));
		frontMech.whenPressed(new DriveCommand(DriveMode.TURN_FRONT));
		backMech.whenPressed(new DriveCommand(DriveMode.TURN_BACK));
		climb.whenPressed(new ClimbCommand());
		shoot.whenPressed(new ShootCommand());
		release.whenPressed(new ReleaseCommand());
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

