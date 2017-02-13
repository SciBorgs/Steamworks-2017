package org.usfirst.frc.team1155.robot;

import edu.wpi.first.wpilibj.I2C;

public class RioDuinoSlaveController {

	private I2C i2cBus;
	
	public RioDuinoSlaveController()
	{
		i2cBus = new I2C(I2C.Port.kMXP, 4);
	}
	
	public void SendStateChange(char state)
	{
		i2cBus.write(0x02, state);
		//causes color to update
	}
	
	public void SendString(String writeStr)
	{
		char[] CharArray = writeStr.toCharArray(); 
		//you can't use strings as they are too big too pass through, so convert into chars
		byte[] WriteData = new byte[CharArray.length]; 
		//you can't use chars as they are too big too pass through, so convert into bytes
		for (int i = 0; i < CharArray.length; i++) {
			WriteData[i] = (byte) CharArray[i];
		} //convert chars into bytes
		i2cBus.transaction(WriteData, WriteData.length, null, 0);
		//sends it to RioDuino
	}
}