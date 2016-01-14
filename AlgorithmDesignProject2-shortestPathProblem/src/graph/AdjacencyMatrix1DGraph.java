package graph;

import java.util.ArrayList;
import java.util.Random;


/**
 * @author babylu
 *
 */

public class AdjacencyMatrix1DGraph {
	private ArrayList<Integer> vertices = new ArrayList<Integer>();
	private int[] edges;
	private static final int INF = Integer.MAX_VALUE;
	
	/**
	 * Generate the 1D Adjacency Matrix Graph
	 * @param n
	 * @param typeOfMatrix
	 */
	public void GraphGenerator(int n,int typeOfMatrix){
		edges = oneDMatrixGenerator(n,typeOfMatrix);
		for (int i = 0; i < n; i++){
			vertices.add(i);
		}
	}
	
	/**
	 * Generate random 1D Matrix as a set of edge in a graph
	 * @param n
	 * @param typeOfMatrix
	 * @return edgeList
	 */
	public int[] oneDMatrixGenerator(int n,int typeOfMatrix) {
 		int[] list = new int[n*(n-1)/2];
 		
 		//Generate the basic graph matrix
		ArrayList<Integer> choosenSet = new ArrayList<Integer>();
		ArrayList<Integer> unchoosedSet = new ArrayList<Integer>();
		for (int i = 0; i < n; i++){
			unchoosedSet.add(i);
		}
		Random random = new Random();
		while(!unchoosedSet.isEmpty()){
			int s = random.nextInt(unchoosedSet.size());
			if (choosenSet.size() > 0){
				int t = random.nextInt(choosenSet.size());
				if(choosenSet.get(t) < unchoosedSet.get(s)){
					list[unchoosedSet.get(s)*(unchoosedSet.get(s)-1)/2+choosenSet.get(t)] = random.nextInt(9)+1;
				}else{
					list[choosenSet.get(t)*(choosenSet.get(t)-1)/2+unchoosedSet.get(s)] = random.nextInt(9)+1;
				}
			}
			choosenSet.add(unchoosedSet.get(s));
			unchoosedSet.remove(s);
		}
		
		//Generate different kinds of adjacency matrixes
		if(typeOfMatrix == 1){
			//1 - normal
			for(int i=0;i<list.length;i++){
				if(list[i] == 0){
					list[i] = random.nextInt(10);
					if(list[i] == 0){
						list[i] = INF;
					}
				}
			}
		}else if(typeOfMatrix == 2){
			//2 - sparse
			for(int i=0;i<list.length;i++){
				if(list[i] == 0){
					list[i] = INF;
				}
			}
		}else if(typeOfMatrix == 3){
			//3 - complete
			for(int i=0;i<list.length;i++){
				if(list[i] == 0){
					list[i] = random.nextInt(9)+1;
				}
			}
		}
		return list;
	}



	public ArrayList<Integer> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Integer> vertices) {
		this.vertices = vertices;
	}

	public int[] getEdges() {
		return edges;
	}

	public void setEdges(int[] edges) {
		this.edges = edges;
	}
}
