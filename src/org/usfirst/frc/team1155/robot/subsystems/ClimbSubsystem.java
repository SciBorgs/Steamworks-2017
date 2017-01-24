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

	private CANTalon spinningTubeTalon;
	private Encoder encoderForSpinningTubeTalon;

	public ClimbSubsystem() {
		spinningTubeTalon = new CANTalon(PortMap.SPIN_TUBE_TALON);
		encoderForSpinningTubeTalon = new Encoder(PortMap.SPIN_TUBE_SOURCE2, PortMap.SPIN_TUBE_SOURCE2);
	}

	public void climbUp() {
		spinningTubeTalon.set(1);
	}

	public void halt() {
		spinningTubeTalon.set(0);
	}

	public void resetEncoder(){
		encoderForSpinningTubeTalon.reset();
	}

	public int getEncoder(){
	return encoderForSpinningTubeTalon.get();
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}