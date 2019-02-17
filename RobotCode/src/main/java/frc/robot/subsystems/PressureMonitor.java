/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.PublishPressureCommand;

/**
 * Add your docs here.
 */
public class PressureMonitor extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private AnalogInput pressureSensor;
  public PressureMonitor(){
    pressureSensor = new AnalogInput(RobotMap.Analog.pressureSensor);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new PublishPressureCommand());
    
  }

  public double getPressure(){
    return 250 * ( pressureSensor.getVoltage() / 5.0) - 25;
  }
}
