
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;


/**
 * This class will act as a server to process multiple client threads and send them back to the client for output
 * 
 * A server socket will be used to create the connection between the server and client.
 * 
 *
 *
 */

public class Server {


	public static void main(String[] args) throws InterruptedException {

		if (args.length < 1) return;

		int portNumber = Integer.parseInt(args[0]);

		
		ArrayList<myServerThread> serverThreadList = new ArrayList<myServerThread>();
		int i = 0;
		
//		while(i < 5) 
//		{
			myServerThread serverThread = new myServerThread(portNumber);
//			serverThread.start();
			serverThreadList.add(serverThread);
			i++;
			
//		}
		
		// Starting the execution of threads
    	for(Thread t : serverThreadList)
    	{
    		t.run();
    	}
    	
    	// Specifying that previous thread must finish before next one is started
    	for(Thread t : serverThreadList) {
    		t.join();
    	}

}
}