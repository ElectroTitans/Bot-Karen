/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveDistancePID extends Command {
  private PIDController distanceController;
  public DriveDistancePID() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    distanceController = new PIDController(0.5, 0, 0, Robot.m_drivetrain, Robot.m_drivetrain);
    distanceController.setSetpoint(1.0); //1m
    distanceController.setPercentTolerance(0.1);
    distanceController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !distanceController.isEnabled() || distanceController.onTarget() ;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    distanceController.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    distanceController.disable();
    Robot.m_drivetrain.setMotors(0, 0); // Stop the bot!
  }
}
