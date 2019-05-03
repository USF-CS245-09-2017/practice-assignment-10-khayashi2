import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class GraphImplementation implements Graph{
	private int[][] edges;
	private int[] verts;
	private int sizeV;

	/**
	 * Constructor creates an adjacent matrices and creates a vertices depending on the parameter. Then initialize all verts to its corresponding index
	 * @param v the amount of nodes/vertices
	 */
	public GraphImplementation(int v) {
		edges = new int[v][v];
		verts = new int[v];
		for(int i = 0; i < verts.length; i ++) {
			verts[i] = i;
		}
		sizeV = v;
	}

	/**
	 * Creates an edge between two vertex 
	 * @param v1 start of and edge
	 * @param v2 the end of an edge
	 */
	public void addEdge(int v1, int v2) {
		//if vertices are out of bounds of array
		if(v1 >= edges.length || v2 >= edges.length)
			throw new ArrayIndexOutOfBoundsException("Vertex out of bounds");
		//if vertices are already an edge
		else if(edges[v1][v2] == 1) {
			System.out.println("This edge already exist between the two");
		}
		//else assign edge to 1;
		else
			edges[v1][v2] = 1;
	}

	/**
	 * sorts the array using topological sorting
	 * @return returns an ordered list
	 */
	public List<Integer> topologicalSort() {
		//create a stack
		Stack s = new Stack();
		//v for vertex
		int v;
		//incident of the amount of edges existing in a row
		int[] incident = new int[sizeV];
		Arrays.fill(incident, 0);
		
		//List to store the ordered integers
		List<Integer> topSort = new ArrayList<Integer>();
		
		//go through the adjacent matrix and increment incident if an edge exist
		for(int start = 0; start < sizeV; start++) {
			for(int end = 0; end < sizeV; end++)
				if(edges[start][end] == 1)
					incident[start]++;
			//inner for
		}//outer for
		
		
		//go through the incident array and if it is a zero, add the index to the stack
		for(v = 0; v < sizeV; v++) {
			if(incident[v] == 0)
				s.push(v);
		}

		//keep doing until the stack is empty
		while(!s.empty()) {
			//pop the stack and store it
			v = (int) s.pop();
			
			//add the popped value to the sorted list
			topSort.add(0, v);
			
			//go through the incident array and decrement. if decrement value is 0, then add that index to the stack
			for(int column  = 0; column < sizeV; column++) {
				if(--incident[column] == 0) {
					s.push(column);	
				}
			}
		}
		return topSort;
	}

	/**
	 * Finds the neighbors of a particular neighbor
	 * @param the vertex to finds its neighbors
	 * @return returns an array of the vertex's neighbors
	 */
	public int[] neighbors(int vertex) {
		List<Integer> neighborsL = new ArrayList<Integer>();
		for(int i = 0; i < sizeV; i++) {
			if(edges[vertex][i] == 1) 
				neighborsL.add(i);
		}
		int[] neighborsA = new int[neighborsL.size()];
		
		for(int i = 0; i < neighborsA.length; i++)
			neighborsA[i] = neighborsL.get(i); 
				
		return neighborsA;
	}

}
