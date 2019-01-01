/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public class HID{
    public static final int joyboy1 = 0;
    public static final int joyboy2 = 1;
  }

  public class PWM{
    public static final int driveLeft1 = 0;
    public static final int driveLeft2 = 1;
    public static final int driveRight1 = 2;
    public static final int driveRight2 = 3;
  }


  public class DIO{
    public static final int encoderLeft1 = 0;
    public static final int encoderLeft2 = 1;
    public static final int encoderRight1 = 2;
    public static final int encoderRight2 = 3;
  }

  public class DriveProfile{
    public static final double maxAccel = 2.0;
    public static final double maxVelocity = 1.7;
    public static final double maxJerk = 60.0;
    public static final double wheelBaseWidth = 0.5;
    public static final double gearRatio = 10.71;
    public static final double encoderTicks = 40;
    public static final double gearBoxTicks = encoderTicks / gearRatio; 
    public static final double wheelDiameter = 0.1524;
    public static final double P = 1.0;
    public static final double I = 0;
    public static final double D = 0;
    public static final double V = 1 / maxVelocity;
    public static final double A = 0;
    
  }
}
