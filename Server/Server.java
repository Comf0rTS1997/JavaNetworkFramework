package Server;

import java.util.concurrent.CopyOnWriteArrayList;
import java.net.*;

public class Server implements Runnable{
    private CopyOnWriteArrayList<clientHand> SocketList;
    private int port;

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

    /**
     * Get a list of all connections
     */
    public CopyOnWriteArrayList<clientHand> getConnectionList(){
        return this.SocketList;
    }

    /**
     * close all connection to current server
     * @throws Exception
     */
    public void stop() throws Exception{
        for (clientHand cli: SocketList){
            cli.s.close();
            cli.pw.close();
            cli.is.close();
            cli.os.close();
            cli.soc.close();
        }
    }

}