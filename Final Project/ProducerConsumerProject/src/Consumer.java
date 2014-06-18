import java.io.*;
import java.net.*;
import java.util.Random;

/**
 * @author Adam Ryder
 * Producer Consumer project
 * This class is the client
 */

public class Consumer extends Thread {

	private int ID;
	BufferedReader inFromServer;
	PrintWriter outToServer;
	Random r = new Random();

	public Consumer(int a) {
		ID = a;
		System.out.println("Consumer # " + this.ID + " has spawned");
		try {
			// Connect the client to the socket and enable data streams
			Socket socket = new Socket("localHost", 14733);
			inFromServer = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			outToServer = new PrintWriter(socket.getOutputStream(), true);

		} catch (Exception e) {
			System.out.println(e);
		}
		this.start();
	}

	/*
	 * This method dictates the action of the thread.
	 * Sends "consume" messages to the server. When the server 
	 * responds with "success", the thread goes to sleep.
	 */
	public void run() {
		while (true) {
			try {
				outToServer.println("consume");
				String s = inFromServer.readLine();
				if(s.equals("success")){
					sleep(r.nextInt(3000));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
