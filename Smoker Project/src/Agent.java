/*
*This agent class is always trying to add
*ingredients to the table. If the table is empty
*agent will add ingredients. When ingredients are added
*the agent will notify() the table and then tell table to wait()
*/
public class Agent extends Thread {
	private Table table = new Table();

	public Agent(Table t) {
		this.table = t;
	}

	@Override
	public void run() {
		try {
			addIngredients();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Agent keeps checking if the table is empty. If it is empty then the agent
	 * adds ingredients to the table. When ingredients are added, notify() the table then
	 * tell the table to wait(). Otherwise, notify() then wait().
	 */
	public synchronized void addIngredients() throws InterruptedException {
		while (true) {
			if (table.checkEmpty()) {
				System.out.println("Agent adding ingredients to table...");
				table.add();
				table.tableNotify();
				table.tableWait();
			} else {
				table.tableNotify();
				table.tableWait();
			}
		}
	}

}
