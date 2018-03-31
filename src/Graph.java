import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Represents a simple, undirected graph
 * containing a set of vertices.
 * 
 * @author Daniel Zamoshchin
 */
public class Graph {

	/**
	 * HashMap that stores the Vertices in the Graph 
	 **/
	private HashMap<String, Vertex> map; 
	
	/**
	 * Default constructor to create an empty set of Vertices
	 */
	public Graph() {
		map = new HashMap<String, Vertex>(); //initialize HashMap
	}
	
	/**
	 * Adds an edge between the vertices of the given names.
	 * @param startLabel first vertex
	 * @param endLabel second vertex
	 */
	public void addEdge(String startLabel, String endLabel) {
		Vertex first = new Vertex(startLabel);
		Vertex second = new Vertex(endLabel);
		//put the vertices into the map if they do not already exist
		map.putIfAbsent(startLabel, first); 
		map.putIfAbsent(endLabel, second);
		//adjust the adjacency lists of the vertices from the map
		map.get(startLabel).addEdge(map.get(endLabel));
		map.get(endLabel).addEdge(map.get(startLabel));
	}
	
	/**
	 * Reads from the file of specified name and adds the
	 * edges listed in the file.
	 * @param filename specified file
	 */
	public void addEdgesFromFile(String filename) {
		File file = new File(filename);
		try {
			Scanner reader = new Scanner(file);
			while(reader.hasNext()) {
				String[] labels = reader.nextLine().split(" ");
				//add an edge between the two vertex labels on each line
				addEdge(labels[0], labels[1]);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File \"" + filename + "\" was not found. Please try again.");
		}
	}
	
	/**
	 * Generates a list of vertices that is a valid
	 * depth first traversal of all vertices
	 * @param label starting vertex
	 * @return list of vertices in order of visitation
	 */
	public ArrayList<Vertex> depthFirstTraversal(String label) {
		ArrayList<Vertex> traversal = depthFirst(label); //perform core depth first traversal
		resetVisitedVertices(); //reset visited indicators
		return traversal;
	}
	
	/**
	 * This recursive helper method performs the core
	 * depth first traversal
	 * @param label current vertex
	 * @return list of vertices in order of visitation
	 */
	private ArrayList<Vertex> depthFirst(String label) {
		ArrayList<Vertex> traversal = new ArrayList<Vertex>();
		Vertex vertex = map.get(label);
		if(vertex == null) {
		  return traversal;
		}
		if(!vertex.getVisited())  { //if the current vertex hasn't been visited 
		  vertex.setVisited(true);
		  traversal.add(vertex); //add it to list
		  
		  for(Vertex adj: vertex.getAdjacent()) {
		    ArrayList<Vertex> sub = depthFirst(adj.getLabel()); 
		    //find the traversal for the adjacent nodes and add them
		    for(Vertex v: sub)
		      traversal.add(v);
		  }
		}
		return traversal;
	}
	
	/**
	 * Generates a list of vertices that is a valid
	 * breadth first traversal of all vertices
	 * @param label starting vertex
	 * @return list of vertices in order of visitation
	 */
	public ArrayList<Vertex> breadthFirstTraversal(String label) {
		ArrayList<Vertex> traversal = new ArrayList<Vertex>();
		Vertex firstVertex = map.get(label);
		//if label doesn't exist
		if(firstVertex == null) {
			return traversal;
		}
		firstVertex.setVisited(true);	
		//BFS uses a queue to process vertices
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.add(firstVertex);
		
		while(!q.isEmpty()) {
			//get the first thing from the queue
			Vertex vertex = q.poll();
			traversal.add(vertex);
			
			//go through the adjacent vertices, visit them, and add them to the queue
			for(Vertex adj: vertex.getAdjacent()) {
				if(!adj.getVisited()) {
					adj.setVisited(true);
					q.add(adj);
				}
			}
		}
		resetVisitedVertices();
		return traversal;
	}
	
	/**
	 * This helper method resets all of the visited vertices
	 * by changing the indicator of visited to false
	 */
	private void resetVisitedVertices() {
		for(Vertex v: map.values()) {
			v.setVisited(false); //set visited to false
		}
	}

	/**
	 * Returns information about each of the vertices 
	 * contained in the Graph
	 */
	public String toString() {
		String output = "";
		for(Vertex v: map.values()) { //traverse through the HashMap
			output += v + "\n";
		}
		return output;		
	}

}
