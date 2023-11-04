
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class creates a thread and handles the output to the client program depending
 * on the command. 
 *
 */

public class myThread extends Thread{

	private Socket socket;
	private int command;
	private InputStream input;
	private BufferedReader reader;
	private OutputStream output;
	private PrintWriter writer;
	private String hostName;
	private int port;
	private long clientStart;
	private long clientEnd;

	public myThread(int command, String hostName, int port)
	{
		this.command = command;
		this.hostName = hostName;
		this.port = port;
		
	}
	
	public long getElapsedTime()
	{
		return clientEnd - clientStart;
	}
		

	public void run()
	{
		clientStart = System.currentTimeMillis(); //Taking client start time.
		
		
		
		//client connected
		//System.out.println("Thread Started; " + this.getName());
		try{
			
			socket = new Socket(hostName, port);
			
			input = socket.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(input));
	        output = socket.getOutputStream();
	        writer = new PrintWriter(output, true);
	        
	        // Sending command to server
	        writer.println(command);
			
	        // Reading the date and time sent from the server and outputting the result
			if(command == 1)
			{
				String time = reader.readLine();
				System.out.println(time);
			}

			// Reading the uptime sent from the server and outputting the result
			if(command == 2)
			{
				String upTime = reader.readLine();
				System.out.println(upTime);
			}

			// Reading the memory use sent from the server and outputting the result
			if(command == 3)
			{
				String memoryUse = reader.readLine();
				System.out.println(memoryUse);
			}

			// Reading the netstat sent from the server and outputting the result
			if(command == 4)
			{
				String netstat = reader.readLine();
				System.out.println(netstat);
			}

			// Reading the current users sent from the server and outputting the result
			if(command == 5)
			{
				String currentUsers = reader.readLine();
				System.out.println(currentUsers);
				
			}

			// Reading the running processes sent from the server and outputting the result
			if(command == 6)
			{
				String runningProcess = reader.readLine();
				System.out.println(runningProcess);
			}
			
			
			socket.close();
			
			
		}

		catch (UnknownHostException ex) {

			System.out.println("Server not found: " + ex.getMessage());
		}   
		catch (IOException ex) {

			System.out.println("I/O error: " + ex.getMessage());
		}
		clientEnd = System.currentTimeMillis(); // taking client end time.
	}
}