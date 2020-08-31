package Client;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client{
    
    private Socket soc;
    private InputStream is;
    private OutputStream os;
    private PrintWriter pw;
    private Scanner sc;

    public Client(String address, int port) throws Exception{
        this.soc = new Socket(address, port);
        is = soc.getInputStream();
        os = soc.getOutputStream();
        sc = new Scanner(is);
        pw = new PrintWriter(os);
    }

    public Client(int port) throws Exception{
        this("127.0.0.1",port);
    }


    public void write(String content) throws Exception{
        pw.println(content);
        pw.flush();
        os.flush();
    }

    public String read(){
        String result = "NULL";
        try {
            while(!sc.hasNextLine()){
                Thread.sleep(500);
            }
            result = sc.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void close() throws Exception{
        sc.close();
        pw.close();
        os.close();
        is.close();
        soc.close();
    }
}