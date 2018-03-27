import java.util.ArrayList;

public class State implements Comparable<State> {
	private State prevTown;
	private Town town;
	private int gCost;
	private ArrayList<Job> jobsLeft;
	private Strategy strats;
	
	public State(State prevTown, Town currTown, int gCost, ArrayList<Job> jobs, Strategy strats) {
		this.prevTown = prevTown;
		this.town = currTown;
		this.gCost = gCost;
		this.jobsLeft = new ArrayList<>();
		jobsLeft.addAll(jobs);
		this.strats = strats;
	}
	/**
	 * Funciton which determines which edge to take depending on distance
	 * @precondition State o != null
	 */
	@Override
	public int compareTo(State o) {
		return calculateFCost() - o.calculateFCost();
	}
	
	public int getCurrCost() {
		return gCost;
	}
	
	/**
	 * Calculates totalCost including zero heurisitic
	 * @return
	 */
	private int calculateFCost() {
		return gCost + strats.calculateHeuristic(this);
	}
	
	public Town getTown() {
		return town;
	}
	
	public ArrayList<Job> getJobs() {
		return jobsLeft;
	}
	
	public boolean jobsToDoIsEmpty() {
		if(jobsLeft.isEmpty()) {
			return true;
		}
		return false;
	} 
	/**
	 * @precondition: jobs exists
	 * @param jobs
	 */
	public void printJobs(ArrayList<Job> jobs) {
		String s = "";
		s = recursiveTracePath(s);
		String[] sArray = s.split(" ");
		int size =  sArray.length;
		//figure out how to differentate prints
		for(int i = 1; i < size - 1; i++) {
			if(!checkIfJobIsEmpty(sArray[i], sArray[i+1], jobs)) {
				System.out.print("Empty");
			} else {
				System.out.print("Job");
			}
			System.out.println(" "+ sArray[i] + " " + "to" + " " + sArray[i + 1]);
		}
	}
	/**
	 * Checks if the town from and town to have a job existing
	 * @param townFrom
	 * @param townTo
	 * @param jobs
	 * @return
	 */
	public boolean checkIfJobIsEmpty(String townFrom, String townTo, ArrayList<Job> jobs) {
		for(Job j : jobs) {
			if(j.getTownFrom().getTown().equals(townFrom) && j.getTownTo().getTown().equals(townTo)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Recusive funciton to constantly pass through the strings of towns
	 * @param s
	 * @return
	 */
	public String recursiveTracePath(String s) {
		if(prevTown != null) {
			s = prevTown.recursiveTracePath(s);
		}
		s = s + " " + town.getTown();
		return s;
	}
	
	/**
	 * Updates the array list of jobs to do
	 */
	public void updateTrips() {
		Job toRemove = null;
		for(Job j : jobsLeft) {
			if (town.equals(j.getTownTo()) && prevTown.getTown().equals(j.getTownFrom())) {
				toRemove = j;
				break;
			}
		}
		if(toRemove != null) {
			jobsLeft.remove(toRemove);
		}
	}	
}