//Freight System which generates optimal solution, z5113254 Regan Edward Fung
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FreightSystem {
	private ArrayList<Town> towns = new ArrayList<>();
	String startTown = null;
	
	public static void main(String args[]) {
		FreightSystem fs = new FreightSystem();
		fs.processInputFile(args);
	}
	
	private void processInputFile(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0])); 
			ConcreteGraph g = new ConcreteGraph();
			while (sc.hasNext()) {
				String nextLine = sc.next();
				if (nextLine.startsWith("Unloading")) {
					processUnloading(sc, g);
				} else if(nextLine.startsWith("Cost")) {
					processCost(sc, g);
				} else if (nextLine.startsWith("Job")) {
					processJob(sc, g);
				} else if (nextLine.startsWith("#")) {
					sc.nextLine();
				}
			}
			g.aStar();
	    }
	    catch (FileNotFoundException e) {
	    	System.out.printf("File not Found");
	    }
	    finally {
	    	if (sc != null) sc.close();
	    }
	}
	
	private void processUnloading(Scanner sc, ConcreteGraph g) {
		int unloadingCost = sc.nextInt();
		String town = sc.next();
		if(!containsTown(towns, town)) {
			g.addNode(town, unloadingCost);
		}	
	}
	//Checks towns in arraylist
	private boolean containsTown(ArrayList<Town> towns, String town) {
		for(Town t : towns) {	
			if(t.getTown().equals(town)) {
				return true;
			}
		}
		return false;
	}
	
	//Creates Edge in graph
	private void processCost(Scanner sc, ConcreteGraph g) {
		int travelCost = sc.nextInt();
		String town1 = sc.next();
		String town2 = sc.next();
		g.addEdge(town1, town2, travelCost);	
	}
	
	private void processJob(Scanner sc, ConcreteGraph g) {
		String fromTown = sc.next();
		String toTown = sc.next();
		g.addJob(fromTown, toTown);
	}
}
