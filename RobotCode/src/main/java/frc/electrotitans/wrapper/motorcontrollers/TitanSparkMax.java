package frc.electrotitans.wrapper.motorcontrollers;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CAN;
import frc.electrotitans.wrapper.motorcontrollers.MotorControllerSafety;
import frc.electrotitans.*;
import frc.electrotitans.util.TitanPIDSettings;
public class TitanSparkMax{

    private int canPort = 0;
    private boolean brushless = true;
    private boolean reversed = false;
    
    private CANSparkMax sparkMAX;
    private CANEncoder  encoder;
    private CANPIDController pidController;
    
    private boolean ready = false;

    public TitanSparkMax(int CAN){
        this.canPort = CAN;
    }

    private void initMotor(){

       
        boolean regResult = MotorControllerSafety.registerSparkMax(this.canPort);
        if(regResult == false){
            System.out.println("[ET/LitSparkyBoi/initMotor] Failed to init. CAN Port already registered: " + this.canPort + ". This usually means there are two sparks in your code that have the same ID. Double check your RobotMap.");
            return;
        }
        this.sparkMAX = new CANSparkMax(this.canPort,MotorType.kBrushless);
        ready = true;
    }

    // set the raw value of the motor (-1.0 to 1.0)
    public void setRaw(double value){
        if(!ready){
            return;
        }

        this.sparkMAX.set(value);
    }

    public void enablePID(TitanPIDSettings pidSettings) {
        if(!ready){return;}
        pidController = sparkMAX.getPIDController();

        pidController.setP(pidSettings.getP());
        pidController.setI(pidSettings.getI());
        pidController.setD(pidSettings.getD());
    }

    public void setPosition(double pos){

    }

    public void setGearing(double ratio){

    }

    

}