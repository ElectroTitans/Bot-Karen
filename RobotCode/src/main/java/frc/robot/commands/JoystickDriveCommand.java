/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.networking.NetworkedCommand;
import jaci.pathfinder.Trajectory;

import javax.sound.midi.Track;

import com.victoryforphil.victoryconnect.*;

/**
 * An example command.  You can replace me with your own command.
 */
public class JoystickDriveCommand extends NetworkedCommand
{
  
  public JoystickDriveCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double throttle = Robot.m_oi.getLeftJoyStick().getY();
    double turn     = Robot.m_oi.getRightJoystick().getX();

    Robot.m_drivetrain.arcadeDrive(throttle, turn);
  
    if(Robot.m_oi.getLeftJoyStick().getTrigger()){
      Robot.m_elevator.setRaw(0.5);
    }else if(Robot.m_oi.getRightJoystick().getTrigger()){
      Robot.m_elevator.setRaw(-0.1);
    }else{
      Robot.m_elevator.setRaw(0);
    }
  
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
