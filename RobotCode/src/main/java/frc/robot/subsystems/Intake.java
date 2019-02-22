/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */

public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DoubleSolenoid deploySol, panelSol;

  public Intake(){
    deploySol = new DoubleSolenoid(0, 1);
    panelSol = new DoubleSolenoid(2, 3);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
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
