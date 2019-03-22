package frc.electrotitans.wrapper.drive;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.electrotitans.util.TitanMath;
import frc.electrotitans.wrapper.motorcontrollers.TitanSparkMax;

public class TitanDifferentialDrive{
    private TitanMath.PositionState position;
    private TitanSparkMax[] allMaxes;
    private TitanSparkMax[] leftMaxes;
    private TitanSparkMax[] rightMaxes;

    private SpeedControllerGroup leftGroup;
    private SpeedControllerGroup rightSpeed;

    public TitanDifferentialDrive(int... canIds){
        allMaxes = new TitanSparkMax[canIds.length];
        int sideLenght = canIds.length / 2;
        int[] leftMaxIds = new int[sideLenght];
        int[] rightMaxIds = new int[sideLenght];
        for(int i=0;i<sideLenght;i++){
            leftMaxIds[i] = canIds[i];
        }

        for(int i=sideLenght - 1;i<canIds.length;i++){
            rightMaxIds[i] = canIds[i];
        }

        setLeftMotorCAN(leftMaxIds);
        setRightMotorCAN(rightMaxIds);

    }

    public void setLeftMotorCAN(int... canIds){

    }

    public void setRightMotorCAN(int... canIds){

    }

    public void arcadeDrive(double throttle, double turn){

    }

    public void stop(){

    }

    public void setOdometry(boolean enabled){

    }

    public void resetOdometry(){

    }

    public void correctOdometry(TitanMath.PositionState newState){
        resetOdometry();
        position = newState;
    }


}