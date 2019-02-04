/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.victoryforphil.victoryconnect.listeners.ClientListener;
import com.victoryforphil.victoryconnect.listeners.TopicSource;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.networking.Networking;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class NavigationSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private AHRS navx;
    public NavigationSubsystem(){
        navx = new AHRS(Port.kMXP);
       
    }
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    public void networkReady(){
        Networking.vcClient.addSource(new TopicSource(){
        
            @Override
            public String getPath() {
                return "bot/navx/connected";
            }
        
            @Override
            public Object getData() {
                return navx.isConnected();
            }
        
            @Override
            public String getConnection() {
                return "TCP";
            }
        });

        Networking.vcClient.addSource(new TopicSource(){
        
            @Override
            public String getPath() {
                return "bot/navx/calibrating";
            }
        
            @Override
            public Object getData() {
                return navx.isCalibrating();
            }
        
            @Override
            public String getConnection() {
                return "TCP";
            }
        });

        Networking.vcClient.addSource(new TopicSource(){
        
            @Override
            public String getPath() {
                return "bot/navx/heading";
            }
        
            @Override
            public Object getData() {
                return navx.getFusedHeading();
            }
        
            @Override
            public String getConnection() {
                return "TCP";
            }
        });
    }

    public double getHeadingDegrees(){
        return navx.getFusedHeading();
    }
}
