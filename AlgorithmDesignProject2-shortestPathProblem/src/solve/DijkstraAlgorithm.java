package solve;

import java.util.ArrayList;

import graph.AdjacencyMatrix1DGraph;
import graph.AdjacencyMatrix2DGraph;
import graph.LinkList;
import graph.LinkedGraph;
import graph.Vertex;
import graph.LinkList.Node;

/**
 * @author babylu
 *
 */
public class DijkstraAlgorithm {
	private boolean[] flag;
	private int[] dist;
	private static final int INF = Integer.MAX_VALUE;
	private int[] prev;
	private int start;
	
	/**
	 * Dijkstra Algorithm using 2D Adjacency Matrix
	 * @param graph
	 * @param n
	 * @param startpoint
	 */
	public void dijkstraAlgorithm(AdjacencyMatrix2DGraph graph, int n,int startpoint) {
		start = startpoint;
		flag = new boolean[n];
		dist = new int[n];
		prev = new int[n];
		
		//Initial data
		for(int i=0;i<n;i++){
			flag[i] = false;
			dist[i] = graph.getEdges()[start][i];
			prev[i] = start;
		}
		flag[start] = true;
		
		int position = 0;
		for (int i = 1; i < dist.length; i++) {
			int min = INF;
			for(int j=0;j<dist.length;j++){
				if(dist[j]<min && !flag[j]){
					min = dist[j];
					position = j;
				}
			}
			flag[position] = true;
			for(int j=0;j<dist.length;j++){
				if(graph.getEdges()[position][j] != INF && graph.getEdges()[position][j]+dist[position] < dist[j]){
					dist[j] = graph.getEdges()[position][j]+dist[position];
					prev[j] = position; 
				}
			}
		}
	}

	/**
	 * Dijkstra Algorithm using 1D Adjacency Matrix
	 * @param graph
	 * @param n
	 * @param startpoint
	 */
	public void dijkstraAlgorithm(AdjacencyMatrix1DGraph graph, int n,int startpoint) {
		start = startpoint;
		flag = new boolean[n];
		dist = new int[n];
		prev = new int[n];
		
		int q;
		//Initial data
		for(int i=0;i<n;i++){
			flag[i] = false;
			if(i == start){
				dist[i] = 0;
			}else{
				q = getPositionIn1D(start,i);
				dist[i] = graph.getEdges()[q];
			}
			prev[i] = start;
		}
		flag[start] = true;
		
		
		int position = 0;
		int p;
		for (int i = 1; i < dist.length; i++) {
			int min = INF;
			for(int j=0;j<dist.length;j++){
				if(dist[j]<min && !flag[j]){
					min = dist[j];
					position = j;
				}
			}
			flag[position] = true;
			for(int j=0;j<dist.length;j++){
				if(position == j) continue;
				p = getPositionIn1D(j,position);
				if(graph.getEdges()[p] != INF && graph.getEdges()[p]+dist[position] < dist[j]){
					dist[j] = graph.getEdges()[p]+dist[position];
					prev[j] = position; 
				}
			}
		}
	}
	
	/**
	 * Dijkstra Algorithm using Adjacency List
	 * @param graph
	 * @param n
	 * @param startpoint
	 */
	public void dijkstraAlgorithm(LinkedGraph graph, int n,int startpoint) {
		start = startpoint;
		flag = new boolean[n];
		dist = new int[n];
		prev = new int[n];
		
		
		//Initial data
		ArrayList<LinkList<Vertex>> al = new ArrayList<LinkList<Vertex>>();
		al = graph.getAl();
		LinkList<Vertex> linkStart = al.get(start);
		Node<Vertex> curr = linkStart.getHead();
		while(curr != null){
			dist[curr.getData().getVertexname()] = curr.getData().getWeight();
			curr = curr.getNext();
		}
		for(int i=0;i<n;i++){
			if(dist[i] == 0 && i!= startpoint){
				dist[i] = INF;
			}
			prev[i] = start;
		}
		flag[start] = true;
		
		int position = 0;
		for (int i = 1; i < dist.length; i++) {
			int min = INF;
			for(int j=0;j<dist.length;j++){
				if(dist[j]<min && !flag[j]){
					min = dist[j];
					position = j;
				}
			}
			flag[position] = true;
			
			int weight;
			for(int j=0;j<dist.length;j++){
				if(position == j) continue;
				weight = getWeightBetweenTwoNodes(position,j,al);
				if(weight != INF && weight+dist[position] < dist[j]){
					dist[j] = weight+dist[position];
					prev[j] = position; 
				}
			}
		}
	}
	
	/**
	 * Get the routine string as normal thought, 0 is translated into 1 and so on
	 * @param dest
	 * @return routine
	 */
	public String getRoutine(int dest){
		String s = "";
		ArrayList<Integer> routineList = new ArrayList<Integer>();
		int j = dest;
		while(j != start){
			routineList.add(j);
			j = prev[j];
		}
		routineList.add(j);
		for (int i = routineList.size()-1; i > 0; i--){
			s += "v" + (routineList.get(i) + 1) + "->";
		}
		s += "v" + (routineList.get(0) + 1);
		return s;
	}
	
	/**
	 * Get the weight between two nodes when using Adjacency List
	 * @param i
	 * @param j
	 * @param al
	 * @return weight
	 */
	private int getWeightBetweenTwoNodes(int i, int j, ArrayList<LinkList<Vertex>> al) {
		if(i == j){
			return 0;
		}
		LinkList<Vertex> link = al.get(i);
    		Node<Vertex> curr = link.getHead();
		while(curr != null){
			if(curr.getData().getVertexname() == j){
				return curr.getData().getWeight();
			}
			curr = curr.getNext();
		}
		return INF;
	}	
	
	/**
	 * Get the position of edge between i and j in 1D Adjacency Matrix
	 * @param i
	 * @param j
	 * @return positionij
	 */
	private int getPositionIn1D(int i, int j) {
		int positionij;
		if(i>j){
    		positionij = i*(i-1)/2+j;
		}else{
			positionij = j*(j-1)/2+i;
		}
		return positionij;
	}
	
	public int[] getDist() {
		return dist;
	}


	public void setDist(int[] dist) {
		this.dist = dist;
	}


	public int[] getPrev() {
		return prev;
	}


	public void setPrev(int[] prev) {
		this.prev = prev;
	}


	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}
	
}
