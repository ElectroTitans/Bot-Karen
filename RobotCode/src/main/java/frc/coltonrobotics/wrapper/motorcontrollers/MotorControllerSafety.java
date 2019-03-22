package frc.electrotitans.wrapper.motorcontrollers;

import java.util.ArrayList;
import java.util.List;



public class MotorControllerSafety {
    private static ArrayList<Integer> sparkMaxCanIds = new ArrayList<>();

    public static boolean registerSparkMax(int canId){
        if(checkListForId(sparkMaxCanIds, canId)){
            return false;
        }else{
            sparkMaxCanIds.add(canId);
            return true;
        }
    }

    private static boolean checkListForId(List list, int id){
        for (Object cur : list) {
            if((int)cur == id){
                return true;
            }
        }

        return false;
    }
}