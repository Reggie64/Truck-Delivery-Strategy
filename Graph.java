import java.util.ArrayList;

public interface Graph {
	
	public void addNode(String town, int cost);

	public boolean containsNode(ArrayList<Town> n, Town town);
	
	public void addEdge(String from, String to, int cost);
	
	public int getNumberOfNodes();
	
	public void addJob(String from, String to);
	
	public void aStar();
}
