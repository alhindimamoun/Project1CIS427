import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by CTPP on 1/30/2017.
 */
public class clientMain
{
    public static void main(String[] args) throws IOException
    {
        String userIn;
        String serverOut;

        BufferedReader userInput = new BufferedReader(
                new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost",1234);

        DataOutputStream outToServer = new DataOutputStream(
                clientSocket.getOutputStream());

        BufferedReader serverOutput = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

        while (true)
        {
            System.out.println("please input for server");
            userIn = userInput.readLine();
            System.out.println("sending to server");
            outToServer.writeBytes(userIn + '\n');

            serverOut = serverOutput.readLine();

            do
            {
                System.out.println("From Server: " + serverOut);
                serverOut = serverOutput.readLine();
            }
            while(!serverOut.equals("end"));




        }


    }
}
