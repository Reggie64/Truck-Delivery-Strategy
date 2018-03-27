
public class Edge {
	private Town startTown;
	private Town endTown;
	private int cost;
	
	public Edge(Town start, Town end, int cost) {
		this.startTown = start;
		this.endTown= end;
		this.cost = cost;
	}
	/**
	 * returns the other connected town to supplied town
	 * @param town
	 * @precondition: town != null and exists
	 * @return
	 */
	public Town getConnectedTown(Town town) {
		if(town.equals(startTown)) {
			return endTown;
		} else if (town.equals(endTown)) {
			return startTown;
		} 
		return null;
	}
	
	public int getCost() {
		return cost;
	}
}
