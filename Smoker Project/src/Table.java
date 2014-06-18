/**
	This table class is shared by the smoker processes
	and the agent. The table has a HashSet of integers
	that represent the ingredients.
	0 = papers
	1 = tobacco
	2 = matches
	The table put two random ingredients on the table 
	at a time. Smokers can ask the table if they are 
	the necessary ingredients for the smoker to smoke.
	The agent is always making sure that the table is 
	not empty.
	This class also has a wait() and notify() method which
	only the smoker and agent can call.
**/
import java.util.*;

public class Table extends Thread {
	private Set<Integer> table = new HashSet<Integer>();
	Random ran = new Random(3);

	// add two random ingredients to table
	public void add() {
		int temp = ran.nextInt(3);
		int temp2 = (temp + 1) % 3;
		this.table.add(temp);
		this.table.add(temp2);
		System.out.println("Adding ingredients to Table: " + temp + " and "
				+ temp2);
	}

	// clear table of ingredients
	public void clearTable() {
		System.out.println("Removing ingredients from table");
		this.table.clear();

	}
	
	// Wait() method so it is possible for smoker and agent to call
	public synchronized void tableWait() throws InterruptedException {
		this.wait();
	}
	//notify() method makes it possible for agent and smoker to call.
	public synchronized void tableNotify() {
		this.notify();
	}

	// check for empty table
	public boolean checkEmpty() {
		if (table.isEmpty()) {
			// System.out.println("table is empty");
			return true;

		} else
			return false;
	}

	// check table for matching ingredients based on the smoker's ID
	public synchronized boolean checkTable(int smokerID) {
		boolean temp = false;
		switch (smokerID) {
		case 0:
			if (this.table.contains(1) && this.table.contains(2)) {
				temp = true;
			}
			break;
		case 1:
			if (this.table.contains(2) && this.table.contains(0)) {
				temp = true;
			}
			break;
		case 2:
			if (this.table.contains(0) && this.table.contains(1)) {
				temp = true;
			}
			break;
		}
		return temp;

	}
}
