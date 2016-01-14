package test;

import graph.LinkedGraph;
import solve.DijkstraAlgorithm;

public class TestCaseSolveLinkList {
	private static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) {
		FloydAlgorithm2 floydAlgorithm = new FloydAlgorithm2();
		DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
		
		int[][] twoD1 = {
				{0,INF,19,INF,INF,INF,INF,INF,7,INF},
				{INF,0,11,INF,INF,INF,INF,11,INF,INF},
				{19,11,0,INF,9,INF,INF,INF,INF,INF},
				{INF,INF,INF,0,INF,INF,11,INF,INF,6},
				{INF,INF,9,INF,0,INF,INF,INF,INF,13},
				{INF,INF,INF,INF,INF,0,17,INF,INF,10},
				{INF,INF,INF,11,INF,17,0,INF,INF,INF},
				{INF,11,INF,INF,INF,INF,INF,0,INF,INF},
				{7,INF,INF,INF,INF,INF,INF,INF,0,6},
				{INF,INF,INF,6,13,10,INF,INF,6,0}
				};
		int[][] twoD2 = {
				{0,INF,INF,INF,INF,INF,INF,INF,INF,8,INF,INF},
				{INF,0,17,13,INF,INF,INF,17,12,INF,INF,INF},
				{INF,17,0,INF,INF,INF,INF,INF,INF,7,INF,9},
				{INF,13,INF,0,INF,INF,15,INF,INF,13,5,13},
				{INF,INF,INF,INF,0,18,INF,INF,5,INF,INF,INF},
				{INF,INF,INF,INF,18,0,INF,17,INF,INF,INF,INF},
				{INF,INF,INF,15,INF,INF,0,17,INF,INF,8,11},
				{INF,17,INF,INF,INF,17,17,0,INF,INF,INF,INF},
				{INF,12,INF,INF,5,INF,INF,INF,0,INF,15,INF},
				{8,INF,7,13,INF,INF,INF,INF,INF,0,INF,14},
				{INF,INF,INF,5,INF,INF,8,INF,15,INF,0,INF},
				{INF,INF,9,13,INF,INF,11,INF,INF,14,INF,0}
		};
		
//		int length1 = twoD1.length;
//		LinkedGraph graphLinkList1 = new LinkedGraph();
//		graphLinkList1.linkedGraph(length1, twoD1);
//		int length2 = twoD2.length;
//		LinkedGraph graphLinkList2 = new LinkedGraph();
//		graphLinkList2.linkedGraph(length2, twoD2);
//		
//		floydAlgorithm.floydAlgorithm(graphLinkList1, length1);
		System.out.println("Test Case-1 Floyd result:");
		fTestLinkedList(7,5,twoD1,floydAlgorithm,1);
		fTestLinkedList(6,1,twoD1,floydAlgorithm,1);
		System.out.println("Test Case-2 Floyd result:");
		fTestLinkedList(0,5,twoD2,floydAlgorithm,1);
		fTestLinkedList(4,11,twoD2,floydAlgorithm,1);
//		floydAlgorithm.getDistToLink().printGraph();
//		System.out.println();
//		floydAlgorithm.floydAlgorithm(graphLinkList2, length2);
//		System.out.println("Test Case-2 Floyd result:");
//		floydAlgorithm.getDistToLink().printGraph();
//		System.out.println();
		
		//Dijkstra
		System.out.println("Test Case-1");
		dTestLinkedGraph(7,5,twoD1,dijkstraAlgorithm,1);
		dTestLinkedGraph(6,1,twoD1,dijkstraAlgorithm,2);
		System.out.println("Test Case-2");
		dTestLinkedGraph(0,5,twoD2,dijkstraAlgorithm,1);
		dTestLinkedGraph(4,11,twoD2,dijkstraAlgorithm,2);
		
	}
	
	/**
	 * Test function for Floyd Algorithm for Linkedgraph
	 * @param start
	 * @param dest
	 * @param matrix must be n*n matrix
	 * @param dij
	 */
	private static void fTestLinkedList(int i, int j, int[][] matrix, FloydAlgorithm2 fAlgor, int k) {
		System.out.println("Test" + k + ":");
		System.out.println("Floyd result:");
		LinkedGraph graphLinkList = new LinkedGraph();
		graphLinkList.linkedGraph(matrix.length, matrix);
		fAlgor.floydAlgorithm(graphLinkList, matrix.length);
		System.out.print("Path from v" + (i+1) +" to v" + (j+1) + ": ");
		fAlgor.path(i, j);
		System.out.println("Weight:" + fAlgor.getWeightBetweenTwoNodes(i, j, graphLinkList.getAl()));
		System.out.println();
	}
	
	/**
	 * Test function for Dijkstra Algorithm for Linkedgraph
	 * @param start
	 * @param dest
	 * @param matrix must be n*n matrix
	 * @param dij
	 */
	private static void dTestLinkedGraph(int start, int dest, int[][] matrix, DijkstraAlgorithm dij, int times) {
		System.out.println("Test" + times + ":");
		int length = matrix.length;
		LinkedGraph graphLinkList = new LinkedGraph();
		graphLinkList.linkedGraph(length, matrix);
		dij.dijkstraAlgorithm(graphLinkList, length, start);
		printDijkstraResult(start, dest, dij);
	}
	
	/**
	 * Print the result of Dijkstra Algorithm
	 * @param start
	 * @param dest
	 * @param dAlgor
	 */
	private static void printDijkstraResult(int start, int dest, DijkstraAlgorithm dAlgor) {
		System.out.println("Dijkstra result:");
		System.out.println("Routine from vertex " + (start + 1)  + " to vertex " + (dest + 1) +": "  + dAlgor.getRoutine(dest));
		System.out.println("Weight:" + dAlgor.getDist()[dest]);
		System.out.println();
	}
}
