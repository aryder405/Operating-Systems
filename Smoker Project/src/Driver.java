
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
				
		Table table = new Table();
		Agent agent = new Agent(table);
		Smoker smoker0 = new Smoker(0, table, agent);
		Smoker smoker1 = new Smoker(1, table, agent);
		Smoker smoker2 = new Smoker(2, table, agent);
		agent.start();
		smoker0.start();
		smoker1.start();
		smoker2.start();
	}

}
