package graph;

import java.util.ArrayList;
import java.util.Random;

public class LinkedGraph {
	
	private ArrayList<LinkList<Vertex>> al;
	private static final int INF = Integer.MAX_VALUE;
	
	/**
	 * print graph
	 */
	public void printGraph() {
		for(int i = 0; i < al.size(); i++){
			LinkList<Vertex> testLinkList = al.get(i);
			testLinkList.printLinkList();
		}
	}
	
	/**
	 * Generate the Adjacency List Graph randomly
	 * @param n
	 * @param typeOfMatrix
	 */
	public void linkedGraph(int n,int typeOfMatrix){
		int[][] x = twoDMatrixGenerator(n,typeOfMatrix);
		al = new ArrayList<LinkList<Vertex>>();
		int length = x[1].length;
		for(int i = 0; i < length; i++){
			LinkList<Vertex> ll = new LinkList<Vertex>();
			ll.addHead(new Vertex(i,0));
			for(int j=0;j<length;j++){
				if(x[i][j] < INF && i != j){
					ll.insert(new Vertex(j,x[i][j]));
				}
			}
			al.add(ll);
		}
	}
	
	/**
	 * Generate the Adjacency List Graph using matrix x
	 * @param n
	 * @param x
	 */
	public void linkedGraph(int n,int[][] x){
		al = new ArrayList<LinkList<Vertex>>();
		int length = x[1].length;
		for(int i = 0; i < length; i++){
			LinkList<Vertex> ll = new LinkList<Vertex>();
			ll.addHead(new Vertex(i,0));
			for(int j=0;j<length;j++){
				if(x[i][j] < INF && i != j){
					ll.insert(new Vertex(j,x[i][j]));
				}
			}
			al.add(ll);
		}
	}
	
	/**
	 * Generate random 2D Matrix as a set of edge in a graph
	 * @param n
	 * @param typeOfMatrix
	 * @return edgeMatrix
	 */
	public static int[][] twoDMatrixGenerator(int n,int typeOfMatrix) {
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

	public ArrayList getAl() {
		return al;
	}
	public void setAl(ArrayList al) {
		this.al = al;
	}
}
