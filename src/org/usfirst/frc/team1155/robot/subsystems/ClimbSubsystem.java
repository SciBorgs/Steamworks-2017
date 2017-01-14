package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private CANTalon spinningTubeTalon; 
	
	public ClimbSubsytem() {
		spinningTubeTalon = new CANTalon(PortMap.SPIN_TUBE_TALON);
	}
	
	public void fSpin(double speed){
		spinningTubeTalon.set(speed);
	}
	
	public void halt(){
		spinningTubeTalon.set(0);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
