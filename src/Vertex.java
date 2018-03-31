import java.util.ArrayList;

/**
 * Represents a vertex on a graph containing
 * a label, an indicator of whether it has been visited,
 * and a list of adjacent vertices
 * 
 * @author Daniel Zamoshchin
 */
public class Vertex {

	/**
	 * Each vertex carries a label/name
	 */
	private String label;
	
	/**
	 * List of adjacent Vertices
	 */
	private ArrayList<Vertex> adjList;
	
	/**
	 * An indicator of whether or not this vertex
	 * has been visited
	 */
	private boolean visited;
	
	/**
	 * Constructor to set the label
	 * @param l name
	 */
	public Vertex(String l) {
		label = l;
		adjList = new ArrayList<Vertex>();
	}
	
	/**
	 * Getter method for visited indicator
	 * @return visited indicator
	 */
	public boolean getVisited() {
		return visited;
	}
	
	/**
	 * Setter method to change visited indicator
	 * @param val boolean value to set to
	 */
	public void setVisited(boolean val) {
		visited = val;
	}
	
	/**
	 * Getter method to get label
	 * @return
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Returns the adjacency list
	 * @return adjacent vertices
	 */
	public ArrayList<Vertex> getAdjacent() {
		return adjList;
	}
	
	/**
	 * Adds an adjacent vertex if it isn't already added
	 * @param e vertex to add
	 */
	public void addEdge(Vertex e) {
		if(!adjList.contains(e)) {
			adjList.add(e);
		}
	}
	
	/**
	 * Gives the name of the vertex followed by a list of its neighbors
	 */
	public String toString() {
		String output = "";
		output+= label + " [";
		for(Vertex v: adjList) {
			output += v.getLabel() + ", ";
		}
		output = output.substring(0, output.length()-2); //remove last part of ", "
		output+= "]";
		return output;
	}

}
