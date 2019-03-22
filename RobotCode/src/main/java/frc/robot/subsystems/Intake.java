/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.IntakeWheels;

/**
 * Add your docs here.
 */

public class Intake extends Subsystem {
  // Put methods for controlling this subsystem`
  // here. Call these from Commands.
  private DoubleSolenoid deploySol, panelSol;
  private CANSparkMax wheel1, wheel2;

  public Intake(){
    deploySol = new DoubleSolenoid(0, 1);
    panelSol = new DoubleSolenoid(2, 3);

    wheel1 = new CANSparkMax(RobotMap.CAN.intakeWheel1, MotorType.kBrushed);
    wheel1.setInverted(true);
    wheel2= new CANSparkMax(RobotMap.CAN.intakeWheel2, MotorType.kBrushed);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new IntakeWheels());
  }
  public void setWheelsRaw(double val){
    wheel1.set(val *0.6);
    wheel2.set(val );
    
  }
  public void  setWheels(boolean val){
    if(val){
      wheel1.set(0.5);
      wheel2.set(0.5);
    }else{
      wheel1.set(-0.5);
      wheel2.set(-0.5);
    }
  }

  public void setPanel(boolean val){
    if(val){
      panelSol.set(DoubleSolenoid.Value.kForward);
    }else{
      panelSol.set(DoubleSolenoid.Value.kReverse);
    }
  }
  public void setDeploy(boolean val){
    if(val){
      deploySol.set(DoubleSolenoid.Value.kForward);
    }else{
      deploySol.set(DoubleSolenoid.Value.kReverse);
    }
  }
}
