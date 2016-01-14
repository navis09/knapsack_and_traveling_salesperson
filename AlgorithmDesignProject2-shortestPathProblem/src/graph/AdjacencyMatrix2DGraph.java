package graph;

import java.util.ArrayList;
import java.util.Random;


/**
 * @author babylu
 *
 */

public class AdjacencyMatrix2DGraph {
	private ArrayList<Integer> vertices = new ArrayList<Integer>();
	private int[][] edges;
	private static final int INF = Integer.MAX_VALUE;
	
	/**
	 * Generate the 2D Adjacency Matrix Graph
	 * @param n
	 * @param typeOfMatrix
	 */
	public void GraphGenerator(int n,int typeOfMatrix){
		edges = twoDMatrixGenerator(n,typeOfMatrix);
		for (int i = 0; i < n; i++){
			vertices.add(i);
		}
	}
	
	/**
	 * Generate random 2D Matrix as a set of edge in a graph
	 * @param n
	 * @param typeOfMatrix
	 * @return edgeMatrix
	 */
	public int[][] twoDMatrixGenerator(int n,int typeOfMatrix) {
 		int[][] matrix = new int[n][n];
 		
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
				matrix[choosenSet.get(t)][unchoosedSet.get(s)] = random.nextInt(9)+1;
				matrix[unchoosedSet.get(s)][choosenSet.get(t)] = matrix[choosenSet.get(t)][unchoosedSet.get(s)];
			}
			choosenSet.add(unchoosedSet.get(s));
			unchoosedSet.remove(s);
		}
		
		//Generate different kinds of adjacency matrixes
		if(typeOfMatrix == 2){
			//sparse
			for(int i = 0; i < matrix.length; i++){
				for (int j = 0; j < i; j++){
					if(matrix[i][j] == 0 && i != j){
						matrix[i][j] = matrix[j][i] = INF;
					}
				}
			}
			return matrix;
		}else if(typeOfMatrix == 1){
			//normal
			for(int i = 0; i < matrix.length; i++){
				for (int j = 0; j < i; j++){
					if(matrix[i][j] == 0 && i != j){
						matrix[i][j] = matrix[j][i] = random.nextInt(10);
						if(matrix[i][j] == 0){
							matrix[i][j] = matrix[j][i] = INF;
						}
					}
				}
			}
			return matrix;
		}else if(typeOfMatrix == 3){
			//complete
			for(int i = 0; i < matrix.length; i++){
				for (int j = 0; j < i; j++){
					if(matrix[i][j] == 0 && i != j){
						matrix[i][j] = matrix[j][i] = random.nextInt(9)+1;
					}
				}
			}
			return matrix;
		}
		return matrix;
	}



	public ArrayList<Integer> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Integer> vertices) {
		this.vertices = vertices;
	}

	public int[][] getEdges() {
		return edges;
	}

	public void setEdges(int[][] edges) {
		this.edges = edges;
	}
}
