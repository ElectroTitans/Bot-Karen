/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class ElevatorPosition extends Command {
  private boolean inc;
  public ElevatorPosition(boolean inc) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_elevator);
    this.inc = inc;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(inc){
      Robot.m_elevator.incrementPos();
    }else{
      Robot.m_elevator.decrementPos();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("Elevator PID Loop:");
    System.out.println("\t - Current Position: " + Robot.m_elevator.getEncoder().getPosition());
    
    SmartDashboard.putNumber("elevator/pos",  Robot.m_elevator.getEncoder().getPosition());
   // SmartDashboard.putNumber("elevator/target", pos);
    //SmartDashboard.putNumber("elevator/error",  Math.abs(Robot.m_elevator.getEncoder().getPosition() - pos));
 // System.out.println("\t - Error: " + Robot.m)
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_elevator.isDone();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_elevator.idle();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
