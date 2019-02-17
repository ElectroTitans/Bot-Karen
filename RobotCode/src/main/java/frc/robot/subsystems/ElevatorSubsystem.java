/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.networking.NetworkedSubsystem;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class ElevatorSubsystem extends NetworkedSubsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private CANSparkMax liftSpark1;
    private DigitalInput hallEffectTop;
    private DigitalInput hallEffectBottom;
    private CANEncoder liftEncoder;
    private CANPIDController pidController;

    private double tickOffset = 0;

    public ElevatorSubsystem(){
        liftSpark1 = new CANSparkMax(RobotMap.CAN.elevator1, MotorType.kBrushless);
        liftSpark1.setIdleMode(IdleMode.kBrake);
        hallEffectBottom = new DigitalInput(RobotMap.DIO.hallEffectBottom);


    }
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void setRaw(double value){
        liftSpark1.set(value);
        
    }

    public void setPosition(double meters){
        pidController.setReference(meters,ControlType.kPosition);
    }


    @Override
    public void networkInit() {

    }

    public CANSparkMax getMasterSpark(){
        return liftSpark1;
    }
}
