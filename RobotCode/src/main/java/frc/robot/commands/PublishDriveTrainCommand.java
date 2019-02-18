/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.victoryforphil.logger.VictoryLogger;
import com.victoryforphil.victoryconnect.listeners.TopicSource;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.networking.Networking;

public class PublishDriveTrainCommand extends Command {
  public PublishDriveTrainCommand() {
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
   
    
    if(RobotMap.NetworkingSettings.useVictoryConnect){
      
      Networking.vcClient.addSource(new TopicSource(){  
        @Override
        public String getPath() {
          return "bot/drivetrain/encoder/avg_distance";
        }
      
        @Override
        public Object getData() {
          return  Robot.m_drivetrain.getAvgDistance();
        }
      
        @Override
        public String getConnection() {
          return "TCP";
        }
      });

     

    }else{
      SmartDashboard.putNumber("bot/drivetrain/encoder/avg_distance", Robot.m_drivetrain.getAvgDistance());
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
