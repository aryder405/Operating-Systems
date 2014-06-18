import java.util.*;

/**
 * @author Adam Ryder
 * Producer Consumer Project
 * This is the driver class which asks the 
 * user how many producers and consumer threads to spawn.
 * Also asks how big to make the buffer.
 */

public class ConsumerMonitor {

	private int num_clients;
	private int num_producers;
	private int buffer_size;

	/**
	 * Constructor. Gets the input of the user and sets the number of producers,
	 * consumers, and buffer size.
	 */
	public ConsumerMonitor() {
		boolean loop = true;
		Scanner keys = new Scanner(System.in);
		while (loop) {
			try {
				System.out.println("Enter number of consumers...");
				num_clients = keys.nextInt();
				System.out.println("Enter number of producers...");
				num_producers = keys.nextInt();
				System.out.println("Enter size of buffer...");
				buffer_size = keys.nextInt();
				loop = false;
			} catch (InputMismatchException e) {
				System.out.println("Input not valid, try again");
				loop = true;
				keys.nextLine();
			}
		}
	}

	/**
	 * Spawn the appropriate number of consumers.
	 */
	public void spawnConsumers() {
		for (int a = 0; a < num_clients; a++) {
			new Consumer(a);
		}

	}

	public static void main(String[] args) {

		ConsumerMonitor t = new ConsumerMonitor();
		Buffer b = new Buffer(t.buffer_size);
		new ProducerServer(t.num_producers, b);
		t.spawnConsumers();

	}

}
