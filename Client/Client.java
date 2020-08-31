package Client;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    
    public static void main(String[] args) throws Exception{
        Socket so = new Socket("127.0.0.1",8080);
        InputStream is = so.getInputStream();
        
        OutputStream os = so.getOutputStream();
        PrintWriter pw = new PrintWriter(os);

        //client info
        Random r = new Random();
        int cliNum = r.nextInt(100);

        for(int i = 0; i <= 100; i++){
            String outputString = "Cli#" + cliNum + " Hello World : " + i;
            pw.println(outputString);
            pw.flush();
            System.out.println("Sent" + outputString);
            Thread.sleep(1000);
        }
        pw.close();
        os.close();
        is.close();
        so.close();
    }
}