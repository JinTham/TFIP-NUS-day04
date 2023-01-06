package day04;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CalculatorClient {
    public static void main(String[] args) throws IOException{
        //Conenct to the server listening on port 3000
        //IP address: 127.0.0.1 or localhost means my local computer, in real life cases need to find out the actual IP address
        Socket clientConn = new Socket("127.0.0.1",3000);
        System.out.println("Connected to server on localhost:3000");
        Console cons = System.console();
        String line = "";
        while (!line.equals("exit")){
            line = cons.readLine("What would you like to calculate today?\nFormat:(operand1[space]operator[space]operand2): ");
            //Send input to server through output stream
            OutputStream os = clientConn.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeUTF(line);
            oos.flush();
            //Receive the reply from server and printout
            InputStream is = clientConn.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            String reply = ois.readUTF();
            System.out.printf(">>> from server: %s\n",reply);
        }
        //Close the connection
        clientConn.close();
    }
}
