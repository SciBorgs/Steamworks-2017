package org.usfirst.frc.team1155.robot.subsystems;

import com.ctre.CANTalon;
import org.usfirst.frc.team1155.robot.PortMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private CANTalon climbTalon;
	private Encoder encoderForClimbTalon;

	public ClimbSubsystem() {
		climbTalon = new CANTalon(PortMap.CLIMB_TALON);
		encoderForClimbTalon = new Encoder(PortMap.CLIMB_SOURCE1, PortMap.CLIMB_SOURCE2);
	}

	public void climbUp() {
		encoderForClimbTalon.set(1);
	}

	public void halt() {
		climbTalon.set(0);
	}

	public void resetEncoder(){
		encoderForClimbTalon.reset();
	}

	public int getEncoder(){
	return encoderForClimbTalon.get();
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}