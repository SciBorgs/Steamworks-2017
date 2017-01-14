package org.usfirst.frc.team1155.robot.commands;
import org.usfirst.frc.team1155.robot.PortMap;
import org.usfirst.frc.team1155.robot.subsystems.ShooterSubsystem;

/**
 *
 */
public class ShootCommand extends Command {
	
	private static Joystick leftJoy;
	private static JoystickButton button;
	private ShooterSubsystem fire = new ShooterSubsystem();

    public ShootCommand() {
    	requires(fire);
    	leftJoy = new Joystick(PortMap.LEFT_JOYSTICK);
    	button = new JoystickButton(leftJoy , PortMap.LEFT_JOYSTICK_SHOOT_BUTTON);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	fire.rev(button.get());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	fire.stopWheel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}