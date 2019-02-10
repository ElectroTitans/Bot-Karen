/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.victoryforphil.victoryconnect.listeners.ClientListener;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.networking.NetworkSpark;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.NavigationSubsystem;
import frc.robot.subsystems.PressureMonitor;
import frc.robot.subsystems.DrivetrainSubsystem.EncoderMode;
import frc.robot.commands.TestCommand;
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
  public static ElevatorSubsystem   m_elevator;
  public static NavigationSubsystem m_nav;
  public static PressureMonitor     m_pressure;
  /*
   private NetworkSpark sparkLeft1 = new NetworkSpark("bot/drive/spark/left/1", RobotMap.PWM.driveLeft1);
  private NetworkSpark sparkLeft2 = new NetworkSpark("bot/drive/spark/left/2", RobotMap.PWM.driveLeft2);
  private NetworkSpark sparkRight1 = new NetworkSpark("bot/drive/spark/right/1", RobotMap.PWM.driveRight1);
  private NetworkSpark sparkRight2 = new NetworkSpark("bot/drive/spark/right/1", RobotMap.PWM.driveRight2);
  */

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    Networking.startVictoryConnect();
    m_oi          = new OI();
    m_drivetrain  = new DrivetrainSubsystem(EncoderMode.NEO);
    m_elevator    = new ElevatorSubsystem();
    m_nav         = new NavigationSubsystem();
    m_pressure    = new PressureMonitor();

    System.out.println("Robot Code Init! - VFP");

    
    
    Networking.vcClient.setListener(new ClientListener(){
    
      @Override
      public void ready() {
        Networking.vcClient.newTopic("Robot Status", "bot/status", "TCP");
        Networking.vcClient.setTopic("bot/status", "Init'd");
        
        m_nav.networkReady();

        System.out.println("Network Code Init! - VFP");


      }
    });
    
    new TestCommand().start();

  }
  
  @Override
  public void robotPeriodic() {
    
  }

  @Override
  public void disabledInit() {
    Networking.vcClient.setTopic("bot/status", "Disabled");
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() { 
    Networking.vcClient.setTopic("bot/status", "Auto");
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    m_drivetrain.setMotors(1.0, 1.0);
  }

  @Override
  public void teleopInit() {
    Networking.vcClient.setTopic("bot/status", "Tele");
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
  _ __   _____      __  _   _  ___  __ _ _ __ 
 | '_ \ / _ \ \ /\ / / | | | |/ _ \/ _` | '__|
 | | | |  __/\ V  V /  | |_| |  __/ (_| | |   
 |_| |_|\___| \_/\_/    \__, |\___|\__,_|_|   
                        |___/                 
happy new year - new year's day

.;;,     .;;, 
`  ;;   ;;  ' 
   ;;   ;; ,  .;;;.   .;;,;;;,  .;;,;;;,  .;;.  .;;. 
 ,;;;;;;;;;'  `   ;;  ` ;;   ;; ` ;;   ;; ` ;;  ;; ' 
 ` ;;   ;;    .;;.;;    ;;   ;;   ;;   ;;   ;;  ;; 
   ;;   ;;    ;;  ;; ,  ;;   ;;   ;;   ;;   ;;  ;; 
.  ;;    ';;' `;;;';;'  ;;';;'    ;;';;'     `;;'; 
';;'                    ;;        ;;            ;; 
                     .  ;;     .  ;;         .  ;; 
                     ';;'      ';;'          ';;' 

               .;;, ,;;;, 
               `  ;;    ;; 
                  ;;    ;;     ,;;,  .;;.      .;;, 
                  ;;    ;;    ;;  ;; ` ;;      ;; ' 
                  ;;    ;;    ;;;;;'   ;;  ;;  ;; 
                  ;;    ;;    ;;   .   ;;  ;;  ;; 
               .  ;;     ';;'  `;;;'    `;;'`;;' 
               ';;' 

                              .;;.     .;;. 
                              `  ;;   ;;  ' 
                                 ;;   ;;   .;;,  .;;;.   .;;.;;;, 
                                 ;;   ;;  ;;  ;; '   ;;  ` ;;   ' 
                                 ;;   ;;  ;;;;;' .;;,;;    ;; 
                                  `;;;';  ;;   . ;;  ;; ,  ;; 
                                      ;;   `;;;'  `;;';;'  ;' 
                                      ;; 
                                  .'  ;; 
                                  ';;;' 
                         ,;. 
                  ..,,,...;;. 
              ,;;;;;;;;;;;;;;;. 
            .;;;;;;;;;;;;;;;;;;            /~~~\ 
            ;;;;;;;;;;;;;;;;;;'         .;;;;    \ 
   .,. ;;   ;;;;;;;;;;;;;;;;;;,        .;;'\      | 
 ;;;;;;;,    `;;;;;;;;;;;;;;;;;     .;;;;'   \__ /  .:::. 
 `;;;;;;;;,    `;;;;;;;;;;;;;'   .;;;;;'          \ ::::: 
       `;;;;;,     `;;;;;;'  .,;;;;;;'              \::: 
         `;;;;;,.,,.;;;;;;.;;;;;;;''            ,a@@@@@@@@@a, 
           `;;;;;;;;;;;;;;;;;;;'           .a@@@@@@@ XII @@@@@@@a, 
              `;;;;;;;;;;;;;;;;         .a@@.@@@@aaaa/|\aaaa@@@@.@@a, 
                `;;;;;;;;;;;;;;;      .@@@@@@@aaa@@@@@|@@@@@aaa@@@@@@@, 
                 ;;;;;;;;;;;;;;;.   .@@@@@@@aa@@@@@@@/|\@@@@@@@aa@@@@@@@. 
                ;;;;;;;;;;;;;;;;;  .@@@.@@@aa@@@@@@@@@|@@@@@@@@@aa@@@.@@@. 
              .################### @@@@@@@aa@@@@@@@@@@|@@@@@@@@@@aa@@@@@@@ 
              ###################; @@@@@@@aa@@@@@@@@@@|@@@@@@@@@@aa@@@@@@@ 
              #;;;;;;;#######;;;;; @ IX @@aa@@@@@@@@@@|@@@@@@@@@@aa@ III @ 
              ;;;;;;;;;###;;;;;;;; @aaaa@@aa@@@@@@@@@@@@@@@@@@@@@aa@aaaaa@ 
              ;;;;;;;;'  `;;;;;;;' @@@@@@@aa@@@@@@@@@@@@@@@@@@@@@aa@@@@@@@ 
             ,;;;;;;'     ;;;;;;'  `@@@.@@@aa@@@@@@@@@@@@@@@@@@@aa@@@.@@@' 
            ,;;;;;'       ;;;;;'    `@@@@@@@aa@@@@@@@@@@@@@@@@aa@@@@@@@@' 
           ,;;;;'        ,;;;;'      `@@@@.@@@aaa@@@@@@@@@@aaa@@.@@@@@@' 
          ;;;;'         .;;;;'          `@@@@@@@@aaaaaaaaaa@@@@@@@@@' 
        .;;;;;          ;;;;;;;,           `@@@@@@@@@ VI @@@@@@@@' 
        `;;;;;'          '''''''                `@@@@aaaa@@@'
*/
// Made by Alex 2018/2019 New Years as he fights that he isnt a work-aholic