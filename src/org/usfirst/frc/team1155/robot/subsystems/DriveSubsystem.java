package org.usfirst.frc.team1155.robot.subsystems;
 
import org.usfirst.frc.team1155.robot.PortMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
 
public class DriveSubsystem extends Subsystem {
   
    public enum DriveMode {
        MECHANUM, TANK, TURN_FRONT, TURN_BACK;
    }
   
    public CANTalon frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    public static DoubleSolenoid[] frontPivots, backPivots;
    
   
    public static DriveMode driveMode;
   
    public DriveSubsystem() {
        frontLeftMotor = new CANTalon(PortMap.FRONT_LEFT_TALON);
        frontRightMotor = new CANTalon(PortMap.FRONT_RIGHT_TALON);
        backLeftMotor = new CANTalon(PortMap.BACK_LEFT_TALON);
        backRightMotor = new CANTalon(PortMap.BACK_RIGHT_TALON);
       
        frontPivots = new DoubleSolenoid[]{new DoubleSolenoid(PortMap.FRONT_PIVOT_SOLENOIDS[0][0], PortMap.FRONT_PIVOT_SOLENOIDS[0][1]), 
        								   new DoubleSolenoid(PortMap.FRONT_PIVOT_SOLENOIDS[1][0], PortMap.FRONT_PIVOT_SOLENOIDS[1][1])};
    
        backPivots = new DoubleSolenoid[]{new DoubleSolenoid(PortMap.BACK_PIVOT_SOLENOIDS[0][0], PortMap.BACK_PIVOT_SOLENOIDS[0][1]), 
        		                          new DoubleSolenoid(PortMap.BACK_PIVOT_SOLENOIDS[1][0], PortMap.BACK_PIVOT_SOLENOIDS[1][1])};
       
        driveMode = DriveMode.TANK;
    }
   
    public void setDriveMode(DriveMode mode) {
        driveMode = mode;
       
        switch(mode) {
        case MECHANUM:
            engageFrontWheels(Value.kForward);
            engageBackWheels(Value.kForward);
            break;
        case TANK:
            engageFrontWheels(Value.kReverse);
            engageBackWheels(Value.kReverse);
            break;
        case TURN_BACK:
            engageFrontWheels(Value.kReverse);
            engageBackWheels(Value.kForward);
            break;
        case TURN_FRONT:
            engageFrontWheels(Value.kForward);
            engageBackWheels(Value.kReverse);
            break;
        }  
    }
      
    public void setMechSpeed(double x, double y, double rotation) {
    	frontLeftMotor.set(x + y + rotation);
    	frontRightMotor.set(-x + y - rotation);
    	backLeftMotor.set(-x + y + rotation);
    	backRightMotor.set(x + y - rotation);
    }
   
    public DriveMode getDriveMode() {
        return driveMode;
    }
   
    public void setTankSpeed(double leftVal, double rightVal) {
            frontRightMotor.set(rightVal);
            frontLeftMotor.set(leftVal);
           
            backRightMotor.set(rightVal);
            backLeftMotor.set(leftVal);
    }
   
    public void engageFrontWheels(DoubleSolenoid.Value value) {
        for (DoubleSolenoid frontPivot: frontPivots) {
            frontPivot.set(value);
        }
    }
   
    public void engageBackWheels(DoubleSolenoid.Value value) {
        for (DoubleSolenoid backPivot: backPivots) {
            backPivot.set(value);
        }
    }
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}