package frc.coltonrobotics.util;

public class TitanPIDSettings{
    private double kP;
    private double kI;
    private double kD;
    private String name;

    public TitanPIDSettings(){
        init(0.5,0,0);
    }

    public TitanPIDSettings(double kP){
        init(kP, 0, 0);
    }

    public TitanPIDSettings(double kP, double kI, double kD){
        init(kP, kI, kD);
    }

    private void init(double p, double i, double d){
        this.kP = p;
        this.kI = i;
        this.kD = d;

        name = "DefaultPID";
    }

    public double getP(){
        return kP;
    }

    public double getI(){
        return kI;
    }

    public double getD(){
        return kD;
    }

    public double[] getArray(){
        return new double[]{kP, kI, kD};
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return name + " - (P: " + getP() + ", I" + getI() + ", D" + getD()+")"; 
    }

}