package test;
import solve.DijkstraAlgorithm;
import solve.FloydAlgorithm;
import graph.AdjacencyMatrix1DGraph;
import graph.AdjacencyMatrix2DGraph;

public class TestCaseSolveAdjacencyMatrix1D {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {
		
		FloydAlgorithm2 fAlgor = new FloydAlgorithm2();
		DijkstraAlgorithm dAlgor = new DijkstraAlgorithm();

		int[] oneD1 = {
				INF,19,11,INF,INF,INF,INF,INF,9,INF,INF,INF,INF,INF,INF,INF,INF,INF,11,
				INF,17,INF,11,INF,INF,INF,INF,INF,7,INF,INF,INF,INF,INF,INF,INF,INF,INF,
				INF,6,13,10,INF,INF,6,0
				};
		int[] oneD2 = {
				INF,INF,17,INF,13,INF,INF,INF,INF,INF,INF,INF,INF,INF,18,INF,INF,INF,15,
				INF,INF,INF,17,INF,INF,INF,17,17,INF,12,INF,INF,5,INF,INF,INF,8,INF,7,13,
				INF,INF,INF,INF,INF,INF,INF,INF,5,INF,INF,8,INF,15,INF,INF,INF,9,13,INF,
				INF,11,INF,INF,14,INF
				};
		
		System.out.println("Floyd result:");

		
		System.out.println("Test Case-1");
		fTest1D(7,5,oneD1,fAlgor,1, 10);
		fTest1D(6,1,oneD1,fAlgor,2, 10);
		
		dTest1D(7, 5, oneD1, dAlgor, 1, 10);
		dTest1D(6,1,oneD1, dAlgor, 2, 10);
		System.out.println("Test Case-2");
		fTest1D(0,5,oneD2,fAlgor,1,12);
		fTest1D(4,11,oneD2,fAlgor,2,12);
		dTest1D(0,5,oneD2,dAlgor,1,12);
		dTest1D(4,11,oneD2,dAlgor,2,12);
		
	}
	
	/**
	 * Test function for Floyd Algorithm for 1D graph
	 * @param i
	 * @param j
	 * @param list
	 * @param fAlgor
	 * @param k
	 * @param size
	 */
	private static void fTest1D(int i, int j, int[] list, FloydAlgorithm2 fAlgor, int k,int size) {
		System.out.println("Test" + k + ":");
		System.out.println("Floyd result:");
		AdjacencyMatrix1DGraph graph1D = new AdjacencyMatrix1DGraph();
		graph1D.setEdges(list);
		fAlgor.floydAlgorithm(graph1D, size);
		System.out.print("Path from v" + (i+1) +" to v" + (j+1) + ": ");
		fAlgor.path(i, j);
		if(i>j){
			System.out.println("Weight:" + fAlgor.getDistToList()[i*(i-1)/2+j]);
		}else{
			System.out.println("Weight:" + fAlgor.getDistToList()[j*(j-1)/2+i]);
		}
		printListInLine(fAlgor.getDistToList());
		System.out.println();
	}
	
	/**
	 * Test function for Dijkstra Algorithm for 1D graph
	 * @param start
	 * @param dest
	 * @param matrix must be n*n matrix
	 * @param dij
	 */
	private static void dTest1D(int start, int dest, int[] list, DijkstraAlgorithm dij, int times, int length) {
		System.out.println("Test" + times + ":");
		AdjacencyMatrix1DGraph graph1D = new AdjacencyMatrix1DGraph();
		graph1D.setEdges(list);
		dij.dijkstraAlgorithm(graph1D, length, start);
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
	
	/**
	 * Print list
	 * @param list
	 */
	private static void printListInLine(int[] list) {
		for(int i=0;i<list.length;i++){
			if(list[i] == Integer.MAX_VALUE){
				System.out.print("#"+",");
			}else{
				System.out.print(list[i]+",");
			}
		}
		System.out.println();
	}
}
