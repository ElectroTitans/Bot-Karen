package frc.robot.networking;

import com.victoryforphil.victoryconnect.Client;

public class Networking {

    public static Client vcClient;
    private static String id = "wpi-client";
    private static String name = "WPI Default Client";

    public static void setClientSettings(String clientId, String clientName){
        id = clientId;
        name = clientName;
    }

    public static void startVictoryConnect(String ip, String port, int tickRate){
        vcClient = new Client(id, name);
        vcClient.enableTCP(ip, port);
    }
}