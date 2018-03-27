import java.util.ArrayList;

/*
 * Class for each seperate town
 */
public class Town {
	private String town;
	private ArrayList<Edge> connectedTo;
	private int unloadingCost;
	
	public Town(String town, int unloadingCost) {
		this.town = town;
		this.unloadingCost = unloadingCost;
		this.connectedTo = new ArrayList<>();
	}
	
	public void addEdge(Edge e) {
		connectedTo.add(e);
	}
	
	public String getTown() {
		return this.town;
	}
	
	public int getUnloadingCost() {
		return this.unloadingCost;
	}
	
	public void addEdgeTo(Town to, int cost) {
		connectedTo.add(new Edge(this, to, cost));
	}
	
	//checks if curr town is connected to another town
	public boolean hasEdge(Town to) {
		for(Edge e : connectedTo) {
			if (e.getConnectedTown(to).equals(to)) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Edge> getEdgeConnections() {
		return connectedTo;
	}

	public boolean equalsName(String townInput) {
		if(townInput.equals(town)) {
			return true;
		}
		return false;
	}
	
}
