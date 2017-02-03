package org.usfirst.frc.team1155.robot;

public class PortMap {
		
	//****************JOYSTICKS*******************//

	public static final int JOYSTICK_LEFT = 0;
	public static final int JOYSTICK_RIGHT = 1;
	
	//****************DRIVE*******************//
	
	public static final int DRIVE_FRONT_LEFT_TALON = 2;
	public static final int DRIVE_FRONT_RIGHT_TALON = 1;
	public static final int DRIVE_BACK_LEFT_TALON = 3;
	public static final int DRIVE_BACK_RIGHT_TALON = 4;
	
	public static final int[] DRIVE_FRONT_LEFT_PISTON = {2,3};
	public static final int[] DRIVE_FRONT_RIGHT_PISTON = {0,1};
	public static final int[] DRIVE_BACK_LEFT_PISTON = {5,4};
	public static final int[] DRIVE_BACK_RIGHT_PISTON = {6,7};
	
	//****************SHOOT*******************//

	public static final int RIGHT_SHOOT_TALON = 5;
	public static final int LEFT_SHOOT_TALON = 6;
	
	public static final int LEFT_SHOOT_SERVO = 0;
	public static final int RIGHT_SHOOT_SERVO = 1;
	
	//****************RESERVOIR*******************//

	public static final int LEFT_AGITATOR_SERVO = 2;
	public static final int RIGHT_AGITATOR_SERVO = 3;
	
}
