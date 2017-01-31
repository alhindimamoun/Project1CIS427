import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by CTPP on 1/30/2017.
 */
public class ServerMain
{

    public static void main(String[] args) throws IOException
    {
        String clientInput;
        String serverOut;
        Scanner fileRead;


        System.out.println("Server has started");
        ServerSocket welcomeSocket = new ServerSocket(1234);
        System.out.println("server is waiting for client");
        Socket socket = welcomeSocket.accept();
        System.out.println("client has connected");

        BufferedReader clientReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        DataOutputStream serverResponse = new DataOutputStream(socket.getOutputStream());


       while(true)
       {
           System.out.println("waiting for input");
           clientInput = clientReader.readLine();
           System.out.println("Input from client: " + clientInput);
           String lowerClientInput = clientInput.toLowerCase();
           System.out.println("lower input: " + lowerClientInput);

           if(lowerClientInput.equals("query"))
           {
               try
               {
                   fileRead = new Scanner(new File("src/alice30.txt"));
                   int lineLengthQuery = 0;
                   serverResponse.writeBytes(Integer.toString(lineLengthQuery)+ "\n");

                   while(fileRead.hasNext())
                   {
                       String line = fileRead.nextLine();
                       byte[] lineBytes = line.getBytes();
                       lineLengthQuery = 4 + lineBytes.length + lineLengthQuery;
                       serverOut = Integer.toString(lineLengthQuery);
                       serverResponse.writeBytes(serverOut + "\n");
                   }

                   serverResponse.writeBytes("end" + "\n");

               }

               catch (Exception e)
               {
                   serverResponse.writeBytes("could not find file" + "\n");
                   serverResponse.writeBytes("end" + "\n");

               }

           }

           else if(lowerClientInput.equals("download"))
           {
               serverResponse.writeBytes("this has not yet ben implemented" + "\n");
               serverResponse.writeBytes("end" + "\n");

           }

           else
           {
               serverResponse.writeBytes("input was not recognized" + "\n");
               serverResponse.writeBytes("end" + "\n");

           }
       }


    }
}
