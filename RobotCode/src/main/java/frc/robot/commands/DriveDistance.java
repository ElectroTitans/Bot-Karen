/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveDistance extends Command {
  private double distance, speed;
  public DriveDistance(double distance, double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivetrain);
    this.distance = distance;
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_drivetrain.resetEncoder();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("Avg Distance: " + Robot.m_drivetrain.getAvgDistance());
    double p = 0.5;
    double error = distance - Robot.m_drivetrain.getAvgDistance();
    double export = p * error;
    if(export > 1.0){
      export = 1.0;
    }
    if (export < -1.0){
      export = -1.0;
    }
   
    Robot.m_drivetrain.setMotors(speed * export, speed  * export  * -1);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double error = distance - Robot.m_drivetrain.getAvgDistance();
    System.out.println(error);
    return Math.abs(error) <= 0.25; 
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
