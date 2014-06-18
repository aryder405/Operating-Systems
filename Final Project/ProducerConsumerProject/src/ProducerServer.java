import java.net.*;
import java.io.*;
/**
 * @author Adam Ryder
 * Producer Consumer Project
 * This class acts as a server to the consumers.
 * When a consumer connects to this server, a handler thread
 * is spawned to interact with the client.
 * The handler class is a static class nested inside this class.
 */
public class ProducerServer extends Thread {

	private static final int PORT = 14733;
	private static ServerSocket listener;
	private static Socket clientSocket;
	private int num_producers;
	private static Buffer buffer;
	private static int count = 0;

	/**
	 * This constructor creates the server and also creates the appropriate
	 * number of producers that was specified by the user.
	 * @param a = the number of producers.
	 * @param b = the resource buffer.
	 */
	public ProducerServer(int a, Buffer b) {
		num_producers = a;
		buffer = b;
		System.out.println("Server spawning producer threads...");
		spawnProducers();
		this.start();
	}

	/**
	 * Spawns the appropriate number of producer threads.
	 */
	public void spawnProducers() {

		for (int a = 0; a < num_producers; a++) {
			new Producer(a, buffer);
		}
	}

	/**
	 *  Opens the server port and listens for clients to connect.
	 *  Spawns a handler thread when a client connects.
	 */
	public void run() {
		try {
			System.out.println("Opening listen port");
			listener = new ServerSocket(PORT);
			System.out.println("Server listening...");
			while (true) {
				clientSocket = listener.accept();
				new Handler(clientSocket, count++).start();
				System.out.println("Client Connected");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) throws Exception {

	}

	/**
	 * Handlers threads are spawned from the listening loop and are responsible
	 * for a dealing with a single client
	 */
	private static class Handler extends Thread {

		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		private int ID;

		// Constructor
		public Handler(Socket socket, int ID) {
			this.socket = socket;
			this.ID = ID;
			// System.out.println("Handler # " + ID + " has spawned.");

		}

		/**
		 * This method dictates the actions of the handler threads.
		 * When a client sends a "consume" message, the handler interacts
		 * with the buffer to consume a resource. If the consume was a success,
		 * it sends a "success" message to the client.
		 */
		public void run() {
			while (true) {
				try {
					// Create character streams for the socket.
					in = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
					out = new PrintWriter(socket.getOutputStream(), true);
					if (in.readLine().equals("consume")) {
						buffer.consume(ID);
						out.println("success");
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}
	}
}
