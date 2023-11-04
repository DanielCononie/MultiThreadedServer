import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

// This class handldes the server side of spawning multiple client sessions

public class myServerThread extends Thread {
	
	private int portNumber;
	private ServerSocket serverSocket;
	private Socket socket;
	private OutputStream output;
	private InputStream input;
	private PrintWriter writer;
	private BufferedReader reader;

	public myServerThread(int portNumber) {
		
		this.portNumber = portNumber;
	}
	
	public void run() {
		
		try {
			
			serverSocket= new ServerSocket(portNumber);
	
			System.out.println("Server is listening on port " + portNumber);
	
			while (true) {
	
				// Accepting the connection
				socket = serverSocket.accept();
	
				output = socket.getOutputStream();
				input = socket.getInputStream();
				reader = new BufferedReader(new InputStreamReader(input));
				writer = new PrintWriter(output, true);
	
				// Accepting the command sent over by client
				int command = Integer.parseInt(reader.readLine());
	
	
				System.out.println("Command:" + command);
	
				if (command < 7)
				{
	
					System.out.println("New client connected");
	
					// Send Date and Time to Client
					if(command == 1) {
						writer.println(new Date().toString());   
						System.out.println("Date sent to client");
					}
	
					// Send Uptime to Client
					if(command == 2)
					{	
						Process process = Runtime.getRuntime().exec("uptime");
						InputStreamReader inputReader = new InputStreamReader(process.getInputStream());
						BufferedReader buffReader = new BufferedReader(inputReader);
	
						String line="";
						String newLine = "";
	
						while((line = buffReader.readLine()) != null)
						{
							newLine += line;
						}
	
						buffReader.close();
						inputReader.close();
	
						// Write uptime to client
						writer.println(newLine); 
						line="";
	
						System.out.println("Uptime sent to client");
	
	
					}
	
					// Send memory use to client
					if(command == 3)
					{                	
						Process process = Runtime.getRuntime().exec("free -h");
						InputStreamReader inputReader = new InputStreamReader(process.getInputStream());
						BufferedReader buffReader = new BufferedReader(inputReader);
	
						String line="";
						String newLine = "";
	
						while((line = buffReader.readLine()) != null)
						{
							newLine += line;
						}
	
						buffReader.close();
						inputReader.close();
	
						writer.println(newLine); 
						line="";
	
						System.out.println("Memory use sent to client");
					}
	
					// send Netstat to client
					if(command == 4)
					{
						Process process = Runtime.getRuntime().exec("netstat");
						InputStreamReader inputReader = new InputStreamReader(process.getInputStream());
						BufferedReader buffReader = new BufferedReader(inputReader);
	
						String line="";
						String newLine = "";
	
						while((line = buffReader.readLine()) != null)
						{
							newLine += line;
						}
	
						buffReader.close();
						inputReader.close();
	
						writer.println(newLine); 
						line="";
	
						System.out.println("Netstat sent to client");
	
	
					}
					// Send current users to client
					if(command == 5)
					{
						Process process = Runtime.getRuntime().exec("who");
						InputStreamReader inputReader = new InputStreamReader(process.getInputStream());
						BufferedReader buffReader = new BufferedReader(inputReader);
	
						String line="";
						String newLine = "";
	
						while((line = buffReader.readLine()) != null)
						{
							newLine += line;
						}
	
						buffReader.close();
						inputReader.close();
	
						writer.println(newLine); 
						line=""; 
	
						System.out.println("Current users sent to client");
	
					}
	
					// send running processes to client
					if(command == 6)
					{
	
	
						Process process = Runtime.getRuntime().exec("ps -ax");
						InputStreamReader inputReader = new InputStreamReader(process.getInputStream());
						BufferedReader buffReader = new BufferedReader(inputReader);
	
						String line="";
						String newLine = "";
	
						while((line = buffReader.readLine()) != null)
						{
							newLine += line;
						}
	
						buffReader.close();
						inputReader.close();
	
						writer.println(newLine); 
						line="";
	
						System.out.println("Host running processes sent to client");

				}

			}
				
//			socket.close();
//			serverSocket.close();
			
		}
	}
		

	 catch(IOException ex) {
		System.out.println("Server exception: " + ex.getMessage());
		ex.printStackTrace();
	}
	}


}

