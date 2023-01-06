package day04;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.BufferedReader;

public class Client {
    public static void main(String[] args) throws IOException{
        //Conenct to the server listening on port 3000
        //IP address: 127.0.0.1 or localhost means my local computer, in real life cases need to find out the actual IP address
        Socket clientConn = new Socket("127.0.0.1",3000);
        System.out.println("Connected to server on localhost:3000");
        Console cons = System.console();
        String line = cons.readLine("What would you like to uppercase today? ");
        //Send sentence to server through output stream
        OutputStream os = clientConn.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        int count = 4;
        oos.writeInt(count);
        oos.writeUTF(line);
        oos.flush();
        //-----------Option 2: writer-------
        //Writer writer = new OutputStreamWriter(os);
        //writer.write(line);
        //writer.flush();
        //----------------------------------
        //Receive the uppercase reply from server and printout
        InputStream is = clientConn.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
        String reply = ois.readUTF();
        //-----------Option 2: reader-------
        //Reader reader = new InputStreamReader(is);
        //BufferedReader bReader = new BufferedReader(reader);
        //String reply = bReader.readLine();
        //----------------------------------
        for (int i=0;i<count;i++){
            System.out.printf(">>> from server: %s\n",reply);
        }
            //Close the connection
        clientConn.close();
    }
}