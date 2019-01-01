/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

import javax.sound.midi.Track;
import com.victoryforphil.victoryconnect.*;

public class RunPathCommand extends Command {
  private Trajectory trajectory = null;
  private TankModifier modifier = null;
  private EncoderFollower leftFollower = null;
  private EncoderFollower rightFollower = null;

  public RunPathCommand(Trajectory trajectory) {
    this.trajectory = trajectory;
    requires(Robot.m_drivetrain);
    requires(Robot.m_nav);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    modifier = new TankModifier(trajectory).modify(RobotMap.DriveProfile.wheelBaseWidth);
    leftFollower = new EncoderFollower(modifier.getLeftTrajectory());
    rightFollower = new EncoderFollower(modifier.getRightTrajectory());

    leftFollower.configureEncoder(Robot.m_drivetrain.getLeftEncoder(), (int) RobotMap.DriveProfile.gearBoxTicks,
        RobotMap.DriveProfile.wheelDiameter);
    rightFollower.configureEncoder(Robot.m_drivetrain.getRightEncoder(), (int) RobotMap.DriveProfile.gearBoxTicks,
        RobotMap.DriveProfile.wheelDiameter);

    leftFollower.configurePIDVA(
      RobotMap.DriveProfile.P, 
      RobotMap.DriveProfile.I, RobotMap.DriveProfile.D,
      RobotMap.DriveProfile.V, RobotMap.DriveProfile.A
    );

    rightFollower.configurePIDVA(
      RobotMap.DriveProfile.P, 
      RobotMap.DriveProfile.I, RobotMap.DriveProfile.D,
      RobotMap.DriveProfile.V, RobotMap.DriveProfile.A
    );

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftOut = leftFollower.calculate(Robot.m_drivetrain.getLeftEncoder());
    double rightOut = rightFollower.calculate(Robot.m_drivetrain.getRightEncoder());

    double heading = Robot.m_nav.getHeadingDegrees();
    double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());

    double angleDif = Pathfinder.boundHalfDegrees(desiredHeading - heading);
    double turn = 0.8 * (-1.0/80.0) *angleDif;

    Robot.m_drivetrain.setMotors(leftOut + turn , rightOut - turn);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return leftFollower.isFinished() && rightFollower.isFinished();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    trajectory = null;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.m_drivetrain.setMotors(0, 0);
    
  }
}
