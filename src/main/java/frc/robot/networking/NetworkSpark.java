package frc.robot.networking;

import com.victoryforphil.victoryconnect.listeners.PacketListener;
import com.victoryforphil.victoryconnect.listeners.TopicSource;
import com.victoryforphil.victoryconnect.networking.Packet;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class NetworkSpark extends Spark {
    private String path;
    private boolean isNetControlled;
    public NetworkSpark(String path, int port){
        super(port);

        this.path = path;
        setupSource();
        isNetControlled = false;
    }

    public void enableNetcontrol(){
        isNetControlled = true;
    }

    public void disableNetcontrol(){
        isNetControlled = false;
    }

    private void setupSource(){
        Spark thisSpark = (Spark) this;

        //PWM Setting

        Networking.vcClient.addSource(new TopicSource(){
            @Override
            public String getConnection() {
                return "TCP";
            }

            @Override
            public Object getData() {
                return thisSpark.getInverted();
            }

            @Override
            public String getPath() {
                return path + "/inverted";
            }
        });

        Networking.vcClient.addSource(new TopicSource(){
            @Override
            public String getConnection() {
                return "TCP";
            }

            @Override
            public Object getData() {
                return thisSpark.getSpeed();
            }

            @Override
            public String getPath() {
                return path + "/speed";
            }
        });

        Networking.vcClient.addSource(new TopicSource(){
            @Override
            public String getConnection() {
                return "TCP";
            }

            @Override
            public Object getData() {
                return thisSpark.getChannel();
            }

            @Override
            public String getPath() {
                return path + "/channel";
            }
        });
    }

    private void setupSubscriptions(){
        Spark thisSpark = (Spark) this;
        Networking.vcClient.subscribe(path + "/speed", new PacketListener(){
			@Override
			public void onCommand(Packet arg0) {
                int speedVal = Integer.parseInt(arg0.data[0]);
                if(isNetControlled){
                    thisSpark.set(speedVal);
                }
			}
        });
    }

    
}