package Client;

import java.util.*;

public class ClientTester {
    
    public static void main(String[] args) throws Exception{
        // Prepare
        Client c = new Client(8080);
        
        // Application
        Random r = new Random();
        String cliNum = "Cli#" + r.nextInt(100);
        for(int i = 0; i <= 100; i++){
            if(!c.validate()){
                System.out.println("Connection Lost");
                break;
            }
            String content = cliNum + " : Hello! " + i;
            c.write(content);
            System.out.println(content);
            Thread.sleep(1000);
        }
        c.close();
    }
}