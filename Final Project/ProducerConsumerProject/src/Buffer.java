import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
 * @author Adam Ryder
 * Producer Consumer project
 * This class acts as a resource buffer that is shared 
 * between the server and the producers.
 * It is a blocking queue of integers.
 */
public class Buffer {

	private BlockingQueue<Integer> buffer;
	private int bufferSize;
	private int count = 0;

	/**
	 * Constructor creates the resource buffer which is a blocking queue
	 * of integers.
	 * @param size = the size of the resource buffer as declared by the user.
	 */
	public Buffer(int size) {
		bufferSize = size;
		buffer = new ArrayBlockingQueue<Integer>(bufferSize);
	}

	// check to see if buffer is full of resources.
	public boolean isFull() {
		if (buffer.size() == bufferSize) {
			return true;
		} else
			return false;
	}
	// check to see if buffer is empty.
	public boolean isEmpty() {
		if (buffer.size() == 0) {
			return true;
		} else
			return false;
	}

	/**
	 * This method puts a resource into the buffer if the buffer is not full.
	 * If the buffer is full, the blocking queue will wait automatically
	 * till there is room to put a resource in the buffer.
	 * The resource is an integer that keeps incrementing.
	 * @param id = The id of the producer.
	 */
	public void produce(int id) throws InterruptedException {
		int t = count;
		buffer.put(count++);
		System.out.println("Producer # " + id + " added " + t + " to buffer");
	}

	/**
	* This method takes a resource from the buffer if the buffer is not empty.
	* If the buffer is empty, the blocking queue will wait automatically till
	* there is something in the queue to take.
	*/
	public void consume(int id) throws InterruptedException {
		Integer value = buffer.take();
		System.out.println("Consumer # " + id + " has consumed value " + value);
	}

	public static void main(String[] args) {

	}

}
