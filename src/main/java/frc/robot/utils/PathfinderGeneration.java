package frc.robot.utils;

import com.victoryforphil.victoryconnect.Client;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.RunPathCommand;
import frc.robot.networking.Networking;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;


public class PathfinderGeneration {

    public static Trajectory generateLocal(Waypoint[] points) {
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
                Trajectory.Config.SAMPLES_FAST,0.05,RobotMap.DriveProfile.maxVelocity, RobotMap.DriveProfile.maxAccel, RobotMap.DriveProfile.maxJerk);
                
       return Pathfinder.generate(points, config);
       
    }

    public static void generateNetwork() {

    }

    public static void bindNetwork(Client client) {
        Networking.vcClient.subscribe("pathfinder/trajectory", packet -> {

        });
    }

  
}