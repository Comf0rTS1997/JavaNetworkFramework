package Server;


public class ServerTester{
   public static void main(String[] args) throws Exception{
      // Prepare 
      Server cf = new Server(8080);
      Thread cft = new Thread(cf);
      cft.start();

       // Application
       while(true){
         for(clientHand cli: cf.SocketList){   
            try {
               System.out.println(cli.soc.getRemoteSocketAddress().toString() + " : " + cli.read());
            } catch (Exception e) {
            }
         }
         Thread.sleep(100);
       }
    }
}
