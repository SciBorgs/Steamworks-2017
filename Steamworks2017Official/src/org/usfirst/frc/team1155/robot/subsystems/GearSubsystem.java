package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearSubsystem extends Subsystem {
	
	public enum GearPosition {
		OPEN, CLOSE;
	}
	
	public DoubleSolenoid gearPistons;
	
	public GearSubsystem() {
		gearPistons = new DoubleSolenoid(0, PortMap.GEAR_PISTONS[0], PortMap.GEAR_PISTONS[1]);
		
		gearPistons.set(DoubleSolenoid.Value.kForward);
	}
	
	public void setGearPosition(GearPosition position) {
		if(position == GearPosition.OPEN) {
			gearPistons.set(DoubleSolenoid.Value.kReverse);
		}else {
			gearPistons.set(DoubleSolenoid.Value.kForward);
		}
		SmartDashboard.putString("Gear Position", gearPistons.get().name());
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

