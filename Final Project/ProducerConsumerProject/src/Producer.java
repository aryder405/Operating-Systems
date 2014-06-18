import java.util.*;

/**
 * @author Adam Ryder
 * Producer Consumer Project
 * This class is the producer that keeps checking
 * the buffer to see if they can fill it with 
 * resources.
 */
public class Producer extends Thread {
	private int ID;
	private Buffer buffer;
	Random r = new Random();

	/**
	 * Constructor. Creates a producer with an ID and buffer, then calls the
	 * start() method.
	 * 
	 * @param a = ID.
	 * @param b = resource buffer.
	 */
	public Producer(int a, Buffer b) {
		ID = a;
		buffer = b;
		System.out.println("Producer # " + this.ID + " has spawned.");
		this.start();
	}

	/**
	 * The run method dictates the actions of the single thread. Each producer
	 * thread will keep trying to add items into the buffer.
	 */
	public void run() {
		while (true) {
			try {
				sleep(r.nextInt(4000));
				buffer.produce(this.ID);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
