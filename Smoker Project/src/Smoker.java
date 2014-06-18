import java.util.*;

public class Smoker extends Thread {
	private final int ingredient;
	private Table table;
	private Agent agent;

	public Smoker(int a, Table table, Agent agent) {
		this.table = table;
		this.agent = agent;
		this.ingredient = a;
	}

	@Override
	public void run() {
		try {
			this.sleep(1000);
			attemptSmoke();
		} catch (Exception ex) {

		}
	}

	public synchronized void attemptSmoke() throws InterruptedException {
		while (true) {
			boolean temp = table.checkTable(this.ingredient);
			if (temp) {
				System.out.println("Smoker #" + ingredient + " found necessary ingredients");
				this.sleep(1000);
				table.clearTable();
				System.out.println("Smoker #" + ingredient + " rolls and smokes");
				this.sleep(1000);
				table.tableNotify();
				table.tableWait();
			} else {
				table.tableNotify();
				table.tableWait();
			}
		}
	}

}
