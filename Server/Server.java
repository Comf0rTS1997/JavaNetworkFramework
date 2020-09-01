package Server;

import java.util.concurrent.CopyOnWriteArrayList;
import java.net.*;

public class Server implements Runnable{
    public CopyOnWriteArrayList<clientHand> SocketList;
    private int port;
    public boolean found = false;

    public Server(int port){
        this.port = port;    
        this.SocketList = new CopyOnWriteArrayList<>();
    }
    /**
     * Start looking for client
     */
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
    /**
     * Remove all invalid socket
     */
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