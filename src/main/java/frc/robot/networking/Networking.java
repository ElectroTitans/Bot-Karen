package frc.robot.networking;

import com.victoryforphil.victoryconnect.Client;
import com.victoryforphil.victoryconnect.listeners.ClientListener;
import com.victoryforphil.victoryconnect.listeners.MDNSListener;

import frc.robot.utils.PathfinderGeneration;

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
        vcClient.setListener(new ClientListener(){
            @Override
            public void ready() {
                
            }
        });
        
        vcClient.enableMDNS(new MDNSListener(){
        
            @Override
            public void onService(String arg0, String ip, String port) {
                if(arg0 == "TCP"){
                    vcClient.enableTCP(ip, port);
                }
            }
        });
    }


}