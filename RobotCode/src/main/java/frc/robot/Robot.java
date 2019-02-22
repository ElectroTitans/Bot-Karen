/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.victoryforphil.victoryconnect.listeners.ClientListener;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.networking.NetworkSpark;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.NavigationSubsystem;
import frc.robot.subsystems.PressureMonitor;
import frc.robot.subsystems.DrivetrainSubsystem.EncoderMode;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.ElevatorPosition;
import frc.robot.commands.ElevatorTest;
import frc.robot.commands.JoystickDriveCommand;
import frc.robot.commands.PublishDriveTrainCommand;
import frc.robot.commands.PublishPressureCommand;
import frc.robot.commands.TestCommand;
import frc.robot.commands.TipPrevention;
import frc.robot.networking.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static OI m_oi;
  public static DrivetrainSubsystem m_drivetrain;
  public static ElevatorSubsystem m_elevator;
  public static NavigationSubsystem m_nav;
  public static PressureMonitor m_pressure;
  public static Intake m_intake;
  /*
   * private NetworkSpark sparkLeft1 = new NetworkSpark("bot/drive/spark/left/1",
   * RobotMap.PWM.driveLeft1); private NetworkSpark sparkLeft2 = new
   * NetworkSpark("bot/drive/spark/left/2", RobotMap.PWM.driveLeft2); private
   * NetworkSpark sparkRight1 = new NetworkSpark("bot/drive/spark/right/1",
   * RobotMap.PWM.driveRight1); private NetworkSpark sparkRight2 = new
   * NetworkSpark("bot/drive/spark/right/1", RobotMap.PWM.driveRight2);
   */

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {

    m_oi = new OI();
    m_drivetrain = new DrivetrainSubsystem(EncoderMode.NEO);
    m_elevator = new ElevatorSubsystem();
    m_nav = new NavigationSubsystem();
    m_pressure = new PressureMonitor();
    m_intake =new Intake();

    System.out.println("Robot Code Init! - VFP");

    //CameraServer.getInstance().startAutomaticCapture();

    if (RobotMap.NetworkingSettings.useVictoryConnect) {

      Networking.startVictoryConnect();
      Networking.vcClient.setListener(new ClientListener() {

        @Override
        public void ready() {

          RobotMap.syncWithNetwork();
          Networking.vcClient.newTopic("Robot Status", "bot/status", "TCP");
          Networking.vcClient.setTopic("bot/status", "Init'd");
          System.out.println("Network Code Init! - VFP");

          m_nav.networkReady();

          new PublishPressureCommand().start();
          new PublishDriveTrainCommand().start();
        }
      });
      
    }else{
      new PublishPressureCommand().start();
      //new PublishDriveTrainCommand().start();
    }

  }

  @Override
  public void robotPeriodic() {

  }

  @Override
  public void disabledInit() {
    if(RobotMap.NetworkingSettings.useVictoryConnect){
      Networking.vcClient.setTopic("bot/status", "Disabled");
    }

  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    if(RobotMap.NetworkingSettings.useVictoryConnect){
      Networking.vcClient.setTopic("bot/status", "Auto");
    }
   // new DriveDistance(1.0, 0.5).start();
    new ElevatorTest().start();
    
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  
  }

  @Override
  public void teleopInit() {
    if(RobotMap.NetworkingSettings.useVictoryConnect){
      Networking.vcClient.setTopic("bot/status", "Tele");
    }
    new JoystickDriveCommand().start();
    new TipPrevention().start();
  
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}

/*
 * _ __ _____ __ _ _ ___ __ _ _ __ | '_ \ / _ \ \ /\ / / | | | |/ _ \/ _` | '__|
 * | | | | __/\ V V / | |_| | __/ (_| | | |_| |_|\___| \_/\_/ \__,
 * |\___|\__,_|_| |___/ happy new year - new year's day
 * 
 * .;;, .;;, ` ;; ;; ' ;; ;; , .;;;. .;;,;;;, .;;,;;;, .;;. .;;. ,;;;;;;;;;' `
 * ;; ` ;; ;; ` ;; ;; ` ;; ;; ' ` ;; ;; .;;.;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ;; ,
 * ;; ;; ;; ;; ;; ;; . ;; ';;' `;;;';;' ;;';;' ;;';;' `;;'; ';;' ;; ;; ;; . ;; .
 * ;; . ;; ';;' ';;' ';;'
 * 
 * .;;, ,;;;, ` ;; ;; ;; ;; ,;;, .;;. .;;, ;; ;; ;; ;; ` ;; ;; ' ;; ;; ;;;;;' ;;
 * ;; ;; ;; ;; ;; . ;; ;; ;; . ;; ';;' `;;;' `;;'`;;' ';;'
 * 
 * .;;. .;;. ` ;; ;; ' ;; ;; .;;, .;;;. .;;.;;;, ;; ;; ;; ;; ' ;; ` ;; ' ;; ;;
 * ;;;;;' .;;,;; ;; `;;;'; ;; . ;; ;; , ;; ;; `;;;' `;;';;' ;' ;; .' ;; ';;;'
 * ,;. ..,,,...;;. ,;;;;;;;;;;;;;;;. .;;;;;;;;;;;;;;;;;; /~~~\
 * ;;;;;;;;;;;;;;;;;;' .;;;; \ .,. ;; ;;;;;;;;;;;;;;;;;;, .;;'\ | ;;;;;;;,
 * `;;;;;;;;;;;;;;;;; .;;;;' \__ / .:::. `;;;;;;;;, `;;;;;;;;;;;;;' .;;;;;' \
 * ::::: `;;;;;, `;;;;;;' .,;;;;;;' \::: `;;;;;,.,,.;;;;;;.;;;;;;;''
 * ,a@@@@@@@@@a, `;;;;;;;;;;;;;;;;;;;' .a@@@@@@@ XII @@@@@@@a, `;;;;;;;;;;;;;;;;
 * .a@@.@@@@aaaa/|\aaaa@@@@.@@a, `;;;;;;;;;;;;;;;
 * .@@@@@@@aaa@@@@@|@@@@@aaa@@@@@@@, ;;;;;;;;;;;;;;;.
 * .@@@@@@@aa@@@@@@@/|\@@@@@@@aa@@@@@@@. ;;;;;;;;;;;;;;;;;
 * .@@@.@@@aa@@@@@@@@@|@@@@@@@@@aa@@@.@@@.
 * .################### @@@@@@@aa@@@@@@@@@@|@@@@@@@@@@aa@@@@@@@
 * ###################; @@@@@@@aa@@@@@@@@@@|@@@@@@@@@@aa@@@@@@@
 * #;;;;;;;#######;;;;; @ IX @@aa@@@@@@@@@@|@@@@@@@@@@aa@ III @
 * ;;;;;;;;;###;;;;;;;; @aaaa@@aa@@@@@@@@@@@@@@@@@@@@@aa@aaaaa@ ;;;;;;;;'
 * `;;;;;;;' @@@@@@@aa@@@@@@@@@@@@@@@@@@@@@aa@@@@@@@ ,;;;;;;' ;;;;;;'
 * `@@@.@@@aa@@@@@@@@@@@@@@@@@@@aa@@@.@@@' ,;;;;;' ;;;;;'
 * `@@@@@@@aa@@@@@@@@@@@@@@@@aa@@@@@@@@' ,;;;;' ,;;;;'
 * `@@@@.@@@aaa@@@@@@@@@@aaa@@.@@@@@@' ;;;;' .;;;;'
 * `@@@@@@@@aaaaaaaaaa@@@@@@@@@' .;;;;; ;;;;;;;, `@@@@@@@@@ VI @@@@@@@@' `;;;;;'
 * ''''''' `@@@@aaaa@@@'
 */
// Made by Alex 2018/2019 New Years as he fights that he isnt a work-aholic