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

import edu.wpi.first.networktables.NetworkTableInstance;
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
    private double  idleVal= 0.05;
    private double[] positions = {
        0,0.25,0.5,0.75,0.95
    };
    private double currentTarget = 0;
    private int curIndex = 0;

    public ElevatorSubsystem(){
        liftSpark1 = new CANSparkMax(RobotMap.CAN.elevator1, MotorType.kBrushless);
        liftSpark1.setIdleMode(IdleMode.kBrake);
        hallEffectBottom = new DigitalInput(RobotMap.DIO.hallEffectBottom);
        
        liftEncoder = liftSpark1.getEncoder();
        liftEncoder.setPositionConversionFactor(0.02);

        

        pidController = liftSpark1.getPIDController();
        pidController.setP(5.5);
        pidController.setI(0.005);
        pidController.setD(0.055);
        pidController.setOutputRange(-0.25, 1.0);

        


        
    }
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());

    }

    public void setRaw(double value){
        liftSpark1.set(value);

        System.out.println("Max fault:" +  liftSpark1.getFaults());
        
    }  
    public void incrementPos(){
        if(curIndex < positions.length - 1){
            curIndex++;
            setPos(curIndex);
        }
    }

    public void decrementPos(){
        if(curIndex > 0){
            curIndex--;
        setPos(curIndex);
        }
    }

    public void setPos(int index){
        setDistance(positions[index]);
    }
    public void setDistance(double meters){
        currentTarget = meters;
       runPID();
        
        
    }
    private  void runPID(){
        pidController.setReference(currentTarget,ControlType.kPosition);
    }

    public boolean isDone(){
         return Math.abs(getEncoder().getPosition() - currentTarget) <= 0.01;
    }

    public void idle(){
        
        liftSpark1.set(idleVal);
    }

    public CANEncoder getEncoder(){
        return liftEncoder;
    }

    public CANPIDController getPidController(){
        return pidController;
    }

    @Override
    public void networkInit() {

    }

    public CANSparkMax getMasterSpark(){
        return liftSpark1;
    }
}
