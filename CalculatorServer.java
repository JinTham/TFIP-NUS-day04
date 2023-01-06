package day04;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;

public class CalculatorServer {
    public static void main(String[] args) throws IOException{
        System.out.println("Starting server on port 3000");
        //Create a server socket and listen to a specific port
        ServerSocket server = new ServerSocket(3000);
        //Wait for a connection
        System.out.println("Waiting for incoming connection");
        Socket sConn = server.accept();
        System.out.println("Got a connection");
        //Get the input stream from the client
        while (true){
            InputStream is = sConn.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            String line = ois.readUTF();
            if (line.equals("exit")){
                System.out.println(">>> Client want to exit application.");
                break;
            }
            System.out.printf(">>> from client: %s\n",line);
            //Convert client input into operand and operator, and perform calculation
            String[] input = line.split(" ");
            Float operand1 = 0.0f;
            Float operand2 = 0.0f;
            Boolean invalid = false;
            try {
                operand1 = Float.parseFloat(input[0].trim());
            } catch (Exception e){
                System.out.println("Invalid operand1!");
                invalid = true;
            }
            try {
                operand2 = Float.parseFloat(input[2].trim());
            } catch (Exception e){
                System.out.println("Invalid operand2!");
                invalid = true;
            }
            OutputStream os = sConn.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            if (!invalid){
                String operator = input[1].trim();
                Calculation calculation = new Calculation(operand1,operand2,operator);
                String result = calculation.getResult();
                oos.writeUTF(result);
            }
            //Send result back to client
            oos.writeUTF("Invalid operand or format");
            oos.flush();
        }
        //Close the connection
        sConn.close();
        server.close();
    }
}
