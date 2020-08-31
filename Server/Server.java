package Server;


public class Server{
   public static void main(String[] args) throws Exception{
       clientFinder cf = new clientFinder(8080);
       Thread cft = new Thread(cf);
       cft.start();
       while(!cf.found){
         Thread.sleep(500);
       }
       while(true){
         try {
            for(clientHand cli: cf.SocketList){
               System.out.println("Received: " + cli.read());
            }
         } catch (Exception e) {
            //TODO: handle exception
         } 
         
       }
    }
} 
