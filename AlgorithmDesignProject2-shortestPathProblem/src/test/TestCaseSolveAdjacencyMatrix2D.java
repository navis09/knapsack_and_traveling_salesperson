package test;
import solve.DijkstraAlgorithm;
import graph.AdjacencyMatrix2DGraph;

public class TestCaseSolveAdjacencyMatrix2D {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {
		
		FloydAlgorithm2 fAlgor = new FloydAlgorithm2();
		DijkstraAlgorithm dAlgor = new DijkstraAlgorithm();

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
		System.out.println("Test Case-1");
		fTest2D(7,5,twoD1,fAlgor,1);
		fTest2D(6,1,twoD1,fAlgor,2);
		dTest2D(7, 5, twoD1, dAlgor, 1);
		dTest2D(6,1,twoD1, dAlgor, 2);
		System.out.println("Test Case-2");
		fTest2D(0,5,twoD2,fAlgor,1);
		fTest2D(4,11,twoD2,fAlgor,2);
		dTest2D(0,5,twoD2,dAlgor,1);
		dTest2D(4,11,twoD2,dAlgor,2);
	}

	/**
	 * Test function for Floyd Algorithm for 2D graph
	 * @param i
	 * @param j
	 * @param list
	 * @param fAlgor
	 * @param k
	 * @param size
	 */
	private static void fTest2D(int i, int j, int[][] matrix, FloydAlgorithm2 fAlgor, int k) {
		System.out.println("Test" + k + ":");
		System.out.println("Floyd result:");
		AdjacencyMatrix2DGraph graph2D = new AdjacencyMatrix2DGraph();
		graph2D.setEdges(matrix);
		fAlgor.floydAlgorithm(graph2D, matrix.length);
		System.out.print("Path from v" + (i+1) +" to v" + (j+1) + ": ");
		fAlgor.path(i, j);
		System.out.println("Weight:" + fAlgor.getDistToMatrix()[i][j]);
		System.out.println();
	}

	/**
	 * Test function for Dijkstra Algorithm for 2D graph
	 * @param start
	 * @param dest
	 * @param matrix must be n*n matrix
	 * @param dij
	 */
	private static void dTest2D(int start, int dest, int[][] matrix, DijkstraAlgorithm dij, int times) {
		System.out.println("Test" + times + ":");
		int length = matrix.length;
		AdjacencyMatrix2DGraph graph2D = new AdjacencyMatrix2DGraph();
		graph2D.setEdges(matrix);
		dij.dijkstraAlgorithm(graph2D, length, start);
		printDijkstraResult(start, dest, dij);
	}

	/**
	 * print matrix
	 * @param matrix
	 * @param n
	 */
	private static void printMatrixInLine(int[][] matrix,int n) {
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if (matrix[i][j] == Integer.MAX_VALUE){
					System.out.print('#'+",");
				}else{
					System.out.print(matrix[i][j]+",");
				}
			}
			System.out.println();
		}
		System.out.println();
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
