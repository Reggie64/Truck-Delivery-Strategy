import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ConcreteGraph implements Graph {
	
	private ArrayList<Town> nodes;
	private ArrayList<Job> jobsToDo;
	
	public ConcreteGraph() {
		this.nodes = new ArrayList<>();
		this.jobsToDo = new ArrayList<>();
	}
	/**
	 * Adds town to the graph
	 * @precondition: unloadingCost > 0, and town != null
	 */
	public void addNode(String town, int unloadingCost) {
		nodes.add(new Town(town, unloadingCost));
	}
	/**
	 * Checks to see if town exists in list of towns
	 * @precondition town exists
	 */
	public boolean containsNode(ArrayList<Town> nodes, Town town) {
		for(Town t : nodes) {
			if(t.getTown().equals(town)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Adds distance between the two towns
	 * @precondition: travelCost > 0, from and != null
	 */
	public void addEdge(String from, String to, int travelCost) {
		Town job1 = getTown(from);
		Town job2 = getTown(to);
		job1.addEdgeTo(job2, travelCost);
		job2.addEdgeTo(job1, travelCost);
	}
	
	public int getNumberOfNodes() {
		return nodes.size();
	}
	
	private Town getTown(String town) {
		Town t = null;
		for (Town curr : nodes) {
			if(curr.equalsName(town)) {
				t = curr;
			}
		}
		return t;
	}
	/**
	 * Adds job to an arrayList of jobs to be carried out
	 * @precondition: startTown && endTown != null
	 */
	@Override
	public void addJob(String startTown, String endTown) {
		Town townFrom = getTown(startTown);
		Town townTo = getTown(endTown);
		int travelCost = 0;
		ArrayList<Edge> edges = townFrom.getEdgeConnections();
		for(Edge e : edges) {
			if(e.getConnectedTown(townFrom).equals(townTo)) {
				travelCost = e.getCost(); 
			}
		}
		jobsToDo.add(new Job(townFrom, townTo, travelCost));
	}
	/**
	 * Algorithm to find the most optimal solution in carrying out the jobs
	 * @precondition Starts from Sydney
	 */
	public void aStar() {
		
		Queue<State> priorityQ = new PriorityQueue<>();
		ArrayList<State> visited = new ArrayList<>();
		int costTotal = 0;
		int nodesExpanded = 0;
		Strategy strats = new ZeroHeuristic();
		priorityQ.add(new State(null,getTown("Sydney"), 0, jobsToDo, strats));
			
		while (!priorityQ.isEmpty()) {
			State currTown = priorityQ.poll();
			nodesExpanded++;
			int total = currTown.getCurrCost();
			if(currTown.jobsToDoIsEmpty()) {
				System.out.println(nodesExpanded + " "+ "nodes expanded");
				for(Job j : jobsToDo) {
					total += j.getTownTo().getUnloadingCost();
				}
				System.out.println("cost = " + total);
				currTown.printJobs(jobsToDo);
				break;
			} else {
				ArrayList<Edge> edges = currTown.getTown().getEdgeConnections();
				visited.add(currTown);
				for(Edge currEdge : edges) {
					Town townTo = currEdge.getConnectedTown(currTown.getTown());
					costTotal = currTown.getCurrCost() + currEdge.getCost();
					State toAdd = new State(currTown, townTo, costTotal, currTown.getJobs(), strats);
					toAdd.updateTrips();
					priorityQ.add(toAdd);
				}	
			}
		}
	}
}
