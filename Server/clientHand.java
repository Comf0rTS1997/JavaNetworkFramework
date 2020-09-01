package Server;

import java.io.*;
import java.util.*;
import java.net.*;

public class clientHand{
    public Socket soc;
    public InputStream is;
    public OutputStream os;

    //read and write
    public Scanner s;
    public PrintWriter pw;


    public clientHand(Socket soc) throws Exception{
        this.soc = soc;
        this.is = soc.getInputStream();
        this.os = soc.getOutputStream();
        this.s = new Scanner(is);
        this.pw = new PrintWriter(os);    
    }
    
    public String read(){
        String result = s.nextLine();
        return result;
    }

    public void write(String content) throws Exception{
        pw.println(content);
        pw.flush();
        os.flush();
    }

    public void close() throws Exception{
        pw.close();
        s.close();
        os.close();
        is.close();
        soc.close();
    }
}