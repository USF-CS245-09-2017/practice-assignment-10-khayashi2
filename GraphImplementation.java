import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class GraphImplementation implements Graph{
	private int[][] edges;
	private int[] verts;
	private int sizeV;

	/**
	 * Constructor makes 
	 * @param v
	 */
	public GraphImplementation(int v) {
		edges = new int[v][v];
		verts = new int[v];
		for(int i = 0; i < verts.length; i ++) {
			verts[i] = i;
		}
		sizeV = v;
	}

	@Override
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

	@Override
	public List<Integer> topologicalSort() {
		Stack s = new Stack();
		int v;
		//count amount of rows has arrays
		int[] incident = new int[sizeV];
		Arrays.fill(incident, 0);
		
		List<Integer> topSort = new ArrayList<Integer>();
		
		for(int start = 0; start < sizeV; start++) {
			for(int end = 0; end < sizeV; end++)
				if(edges[start][end] == 1)
					incident[start]++;
			//inner for
		}//outer for
		
		for(v = 0; v < sizeV; v++) {
			if(incident[v] == 0)
				s.push(v);
		}

		
		while(!s.empty()) {
			v = (int) s.pop();
			topSort.add(0, v);
			for(int column  = 0; column < sizeV; column++) {
				if(--incident[column] == 0) {
					s.push(column);	
				}
			}
		}
		return topSort;
	}

	@Override
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
