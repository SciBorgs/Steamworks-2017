package org.usfirst.frc.team1155.robot;

public class PortMap {
		
	//****************JOYSTICKS*******************//

	public static final int JOYSTICK_LEFT = 0;
	public static final int JOYSTICK_RIGHT = 1;
	
	//****************DRIVE*******************//
	
	public static final int DRIVE_FRONT_LEFT_TALON = 0;
	public static final int DRIVE_FRONT_RIGHT_TALON = 1;
	public static final int DRIVE_BACK_RIGHT_TALON = 2;
	public static final int DRIVE_BACK_LEFT_TALON = 3;
	
	public static final int[] DRIVE_FRONT_PISTONS = {5,4};
	public static final int[] DRIVE_BACK_PISTONS = {3,2};
		
	//****************GEAR*******************//
	
	public static final int[] GEAR_PISTONS = {0,1};

	//****************SHOOT*******************//

	public static final int RIGHT_SHOOT_TALON = 5;
	public static final int LEFT_SHOOT_TALON = 6;
	
	public static final int LEFT_SHOOT_SERVO = 0;
	public static final int RIGHT_SHOOT_SERVO = 1;
	
	//****************RESERVOIR*******************//

	public static final int LEFT_AGITATOR_SERVO = 2;
	public static final int RIGHT_AGITATOR_SERVO = 3;
	
}
