import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will display the menu of options to the user and create new threads with the class "myThread" based on the commands inputed by the user
 *
 * This class will also take the time, total time, and average time of each thread
 */

public class Client {
	
	

	public static void main(String[] args) throws InterruptedException {
		if (args.length < 2) return;
		 
        String hostName = args[0];
        int port = Integer.parseInt(args[1]); 
        
        	while(true) {
            
            @SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);

            int command = 0;
            
            while(command != 7)
            {
            	ArrayList<myThread> threadList = new ArrayList<myThread>();
            	System.out.println("Enter one of the following commands:\n"
         				+  "1      - Host current date/time \n"
         				+  "2      - Host uptime \n"
         				+  "3      - Host memory use \n"
         				+  "4      - Host netstat \n"
         				+  "5      - Host current users \n"
         				+  "6      - Host running processes \n"
         				+  "CTRL-C - Quit");
            	
                command = scanner.nextInt();
                
                // Sending the command number to the server for it to process the request
                //writer.println(command);
                
            	System.out.println("How many clients?");
            	
            	int numClients = scanner.nextInt();
            	
            	int i = 0;
            	@SuppressWarnings("unused")
				int totalTime = 0;
                @SuppressWarnings("unused")
				int avgTime = 0;
                @SuppressWarnings("unused")
				int time = 0;
            	
            	// Adding threads to the ArrayList
            	while(i < numClients)
            	{
	            	myThread thread = new myThread(command,hostName,port);
	            	//start timer
	            	threadList.add(thread);
	            	i++;
            	}
            	
            	// Starting the execution of threads
            	for(Thread t : threadList)
            	{
            		t.start();
            	}
            	
            	// Specifying that previous thread must finish before next one is started
            	for(Thread t : threadList) {
            		t.join();
            	}
            	
            	System.out.println("Thread time elapsed:");
            	long sumClientTime = 0;
            	long averClientTime = 0;
            	
            	//Start Client time elapsed calculations
            	for( myThread t : threadList)
            	{
            		System.out.println(t.getName() + ": " + t.getElapsedTime() + "ms");
            		sumClientTime = sumClientTime + t.getElapsedTime();
            	}
            	System.out.println("Total Turn-around Time " + sumClientTime + "ms");
            	averClientTime = sumClientTime / threadList.size();
            	System.out.println("Average Turn-around Time " + averClientTime + "ms");
            	
            } // end while loop
        }
	} // end main
            
} // end class Client
    