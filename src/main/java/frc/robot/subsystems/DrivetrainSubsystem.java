/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.beans.Encoder;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.*;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.JoystickDriveCommand;

public class DrivetrainSubsystem extends Subsystem {

    private CANSparkMax sparkLeft1;
    private CANSparkMax sparkLeft2;
    private CANSparkMax sparkRight1;
    private CANSparkMax sparkRight2;

    private SpeedControllerGroup leftGroup;
    private SpeedControllerGroup rightGroup;

    private edu.wpi.first.wpilibj.Encoder encoderLeft;
    private edu.wpi.first.wpilibj.Encoder encoderRight;

    private DifferentialDrive drive;

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new JoystickDriveCommand());
    
    }

    public DrivetrainSubsystem() {
        sparkLeft1 = new CANSparkMax(RobotMap.CAN.driveLeft1, MotorType.kBrushless);
        sparkLeft2 = new CANSparkMax(RobotMap.CAN.driveLeft2, MotorType.kBrushless);
        sparkRight1 = new CANSparkMax(RobotMap.CAN.driveRight1, MotorType.kBrushless);
        sparkRight2 = new CANSparkMax(RobotMap.CAN.driveRight2, MotorType.kBrushless);
        
        leftGroup = new SpeedControllerGroup(sparkLeft1, sparkLeft2);
        rightGroup = new SpeedControllerGroup(sparkRight1, sparkRight2);
        
        encoderLeft = new edu.wpi.first.wpilibj.Encoder(RobotMap.DIO.encoderLeft1, RobotMap.DIO.encoderLeft2);
        encoderRight = new edu.wpi.first.wpilibj.Encoder(RobotMap.DIO.encoderRight1, RobotMap.DIO.encoderRight2);

        drive = new DifferentialDrive(leftGroup, rightGroup);
    }
    
    public void setMotors(double left, double right){
        leftGroup.set(left);
        rightGroup.set(right);
    }

    public void arcadeDrive(double throttle, double turn){
       drive.arcadeDrive(throttle, turn);
    }

   

    public void resetEncoder(){
        encoderLeft.reset();
        encoderRight.reset();
    }

    public int getLeftEncoder(){
        return encoderRight.get();
    }

    public int getRightEncoder(){
        return encoderRight.get();
    }

    public int getAvgEncoder(){
        return (getLeftEncoder() + getRightEncoder()) / 2;
    }

    public double getLeftDistance(){
        return encoderLeft.get() * RobotMap.DriveProfile.ticksPerMeter ;
    }

    public double getRightDistance(){
        return encoderRight.get() * RobotMap.DriveProfile.ticksPerMeter ;
    }

    public double getAvgDistance(){
        return (getLeftDistance() + getRightDistance()) / 2;
    }

}
