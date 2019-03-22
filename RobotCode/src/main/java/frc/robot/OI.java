/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.DeployIntake;
import frc.robot.commands.ElevatorPosition;
import frc.robot.commands.PanelIntake;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private Joystick joyboy1;
  private Joystick joyboy2;

  private JoystickButton liftUpButton, liftDownButton;
  private JoystickButton deployButton, foldButton;
  private JoystickButton panelCloseButton, panelOpenButton;

  public OI(){
    joyboy1 = new Joystick(RobotMap.HID.joyboy1);
    joyboy2 = new Joystick(RobotMap.HID.joyboy2);

    liftUpButton     = new JoystickButton(getRightJoystick(), 4);
    liftDownButton   = new JoystickButton(getRightJoystick(), 1);

    deployButton     = new JoystickButton(getRightJoystick(), 5);
    foldButton       = new JoystickButton(getRightJoystick(), 6);

    panelCloseButton = new JoystickButton(getRightJoystick(), 2);
    panelOpenButton  = new JoystickButton(getRightJoystick(), 3);
    

    liftUpButton.whenPressed(new ElevatorPosition(true));
    liftDownButton.whenPressed(new ElevatorPosition(false));

    foldButton.whenPressed(new DeployIntake(false));
    deployButton.whenPressed(new DeployIntake(true));

    panelOpenButton.whenPressed(new PanelIntake(true));
    panelCloseButton.whenPressed(new PanelIntake(false));
  }

  public void bindButton(){

  }

  public Joystick getLeftJoyStick(){
    return joyboy1;
  }

  public Joystick getRightJoystick(){
    return joyboy2;
  }
}
