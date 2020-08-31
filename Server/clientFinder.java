package Server;

import java.util.*;
import java.net.*;

public class clientFinder implements Runnable{
    public LinkedList<clientHand> SocketList;
    private int port;
    public boolean found = false;

    public clientFinder(int port){
        this.port = port;    
        this.SocketList = new LinkedList<>();
    }

    public void run(){
        boolean isRunning = true;
        while(isRunning){
            try {
                ServerSocket ss = new ServerSocket(port);
                clientHand currentClient = new clientHand(ss.accept());
                SocketList.add(currentClient);
                ss.close();
                currentClient.write("test");
                found = true;
            } catch (Exception e) {
                isRunning = false;
                e.printStackTrace();
            }
            purge();
        }
    }

    public void purge(){
        for(clientHand cli: SocketList){
            try {
                cli.write("");
            } catch (Exception e) {
                SocketList.remove(cli);
            }
        }
    }

}