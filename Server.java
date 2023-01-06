package day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting server on port 3000");
        //Create a server socket and listen to a specific port
        ServerSocket server = new ServerSocket(3000);
        
        //Wait for a connection
        System.out.println("Waiting for incoming connection");
        Socket conn = server.accept();
        System.out.println("Got a connection");
        //Get the input stream, read the data from the client
        InputStream is = conn.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
        int count = ois.readInt();
        String line = ois.readUTF();
        //-----------Option 2: reader-------
        //Reader reader = new InputStreamReader(is);
        //BufferedReader bReader = new BufferedReader(reader);
        //String line = bReader.readLine();
        //----------------------------------
        System.out.printf(">>> from client: %s\n",line);
        //Convert client input to uppercase and send back to client
        OutputStream os = conn.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        for (int i=0;i<count;i++){
            oos.writeUTF("%d: %s".formatted(i,line.toUpperCase()));
        }
        oos.flush();
        //-----------Option 2: writer-------
        //Writer writer = new OutputStreamWriter(os);
        //writer.write(line.toUpperCase());
        //writer.flush();
        //----------------------------------
        //Close the connection
        conn.close();
        server.close();
    }
}
