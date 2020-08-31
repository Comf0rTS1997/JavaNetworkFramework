package Server;


public class ServerTester{
   public static void main(String[] args) throws Exception{
       clientFinder cf = new clientFinder(8080);
       Thread cft = new Thread(cf);
       cft.start();
       while(true){
         try {
            for(clientHand cli: cf.SocketList){
               System.out.println(cli.soc.getRemoteSocketAddress().toString() + " : " + cli.read());
            }
         } catch (Exception e) {
            // ignore this cycle
         }
         Thread.sleep(100);
       }
    }
}
