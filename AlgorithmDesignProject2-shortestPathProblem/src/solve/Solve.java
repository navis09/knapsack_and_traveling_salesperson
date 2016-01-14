package solve;

import graph.AdjacencyMatrix1DGraph;
import graph.AdjacencyMatrix2DGraph;
import graph.LinkedGraph;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Solve {

	public static void main(String[] args) {
		System.out.println("Please input the number of vertices:");
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		System.out.println("Please input the type of graph(1-normal 2-sparse 3-complete):");
		int typeOfGraph = in.nextInt();
		System.out.println("Please input the type of data structure(1-2DAdjacencyMatrix 2-1DAdjacencyMatrix 3-linkList):");
		int dataStructure = in.nextInt();

		
		AdjacencyMatrix2DGraph graph2D = new AdjacencyMatrix2DGraph();
		AdjacencyMatrix1DGraph graph1D = new AdjacencyMatrix1DGraph();
		LinkedGraph graphLinkList = new LinkedGraph();
		FloydAlgorithm floydAlgorithm = new FloydAlgorithm();
		DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
		Random random = new Random();
		int startPoint = random.nextInt(n);
		
		if(dataStructure == 1){
			//1 - 2DAdjacencyMatrix
			Date date1 = new Date();
			graph2D.GraphGenerator(n, typeOfGraph);
			Date date2 = new Date();
//			System.out.println("Adjacency 2-D matrix:");
//			printMatrixInLine(graph2D.getEdges(),n);
			
			//Floyd
			Date date3 = new Date();
			floydAlgorithm.floydAlgorithm(graph2D, n);
			Date date4 = new Date();
//			System.out.println("Floyd result:");
//			printMatrixInLine(floydAlgorithm.getDistToMatrix(),n);
			
			//Dijkstra
			Date date5 = new Date();
			dijkstraAlgorithm.dijkstraAlgorithm(graph2D, n, startPoint);
			Date date6 = new Date();
//			printDijkstraResult(dijkstraAlgorithm.getStart(),dijkstraAlgorithm.getDist());
			
			//Time cost
			System.out.println("complete!");
			long generateGraph = date2.getTime() - date1.getTime();
			long floydTakeTime = date4.getTime() - date3.getTime();
			long dijkstraTakeTime = date6.getTime() - date5.getTime();
			System.out.println("Generate graph take time:"+generateGraph+" ms");
			System.out.println("Floyd take time:"+floydTakeTime+" ms");
			System.out.println("Dijkstra take time:"+dijkstraTakeTime+" ms");
			
		}else if(dataStructure == 2){
			//2 - 1DAdjacencyMatrix
			Date date1 = new Date();
			graph1D.GraphGenerator(n, typeOfGraph);
			Date date2 = new Date();
//			System.out.println("Adjacency 1-D matrix:");
//			printListInLine(graph1D.getEdges());
			
			//Floyd
			Date date3 = new Date();
			floydAlgorithm.floydAlgorithm(graph1D, n);
			Date date4 = new Date();
//			System.out.println("Floyd result:");
//			printListInLine(floydAlgorithm.getDistToList());
			
			//Dijkstra
			Date date5 = new Date();
			dijkstraAlgorithm.dijkstraAlgorithm(graph1D, n, startPoint);
			Date date6 = new Date();
//			printDijkstraResult(dijkstraAlgorithm.getStart(),dijkstraAlgorithm.getDist());
			
			//Time cost
			System.out.println("complete!");
			long generateGraph = date2.getTime() - date1.getTime();
			long floydTakeTime = date4.getTime() - date3.getTime();
			long dijkstraTakeTime = date6.getTime() - date5.getTime();
			System.out.println("Generate graph take time:"+generateGraph+" ms");
			System.out.println("Floyd take time:"+floydTakeTime+" ms");
			System.out.println("Dijkstra take time:"+dijkstraTakeTime+" ms");
			
		}else{
			//3 - linkList
			Date date1 = new Date();
			graphLinkList.linkedGraph(n, typeOfGraph);
			Date date2 = new Date();
//			System.out.println("Adjacency list:");
//			graphLinkList.printGraph();
//			System.out.println();
			
			//Floyd
			Date date3 = new Date();
			floydAlgorithm.floydAlgorithm(graphLinkList, n);
			Date date4 = new Date();
//			System.out.println("Floyd result:");
//			floydAlgorithm.getDistToLink().printGraph();
//			System.out.println();
			
//			//Dijkstra
			Date date5 = new Date();
			dijkstraAlgorithm.dijkstraAlgorithm(graphLinkList, n, startPoint);
			Date date6 = new Date();
//			printDijkstraResult(dijkstraAlgorithm.getStart(),dijkstraAlgorithm.getDist());
//			System.out.println();
			
			//Time cost
			System.out.println("complete!");
			long generateGraph = date2.getTime() - date1.getTime();
			long floydTakeTime = date4.getTime() - date3.getTime();
			long dijkstraTakeTime = date6.getTime() - date5.getTime();
			System.out.println("Generate graph take time:"+generateGraph+" ms");
			System.out.println("Floyd take time:"+floydTakeTime+" ms");
			System.out.println("Dijkstra take time:"+dijkstraTakeTime+" ms");
		}
	}

	private static void printDijkstraResult(int start, int[] dist) {
		System.out.println("Dijkstra result:");
		System.out.print("Start vertice selected from 0 to n-1:");
		System.out.println(start);
		printListInLine(dist);
		System.out.println();
	}

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

