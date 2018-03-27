
public class Job {
	private Town from;
	private Town to;
	private int cost;
	
	public Job(Town from, Town to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	public Town getTownFrom() {
		return this.from;
	}
	
	public Town getTownTo() {
		return this.to;
	}
	
	public int getCost() {
		return this.cost;
	}
}
