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

public class FloydAlgorithm {
	private int[][] distToMatrix;
	private static final int INF = Integer.MAX_VALUE;
	private int[] distToList;
	private ArrayList<LinkList<Vertex>> al = new ArrayList<LinkList<Vertex>>();
	private LinkedGraph distToLink = new LinkedGraph();
	private ArrayList<Integer>[][] pathToMatrix;
	
	/**
	 * Floyd Algorithm using 2D Adjacency Matrix
	 * @param AdjacencyMatrix2DGraph graph
	 * @param n
	 */
	public void floydAlgorithm(AdjacencyMatrix2DGraph graph, int n){
		distToMatrix = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
            	distToMatrix[i][j]=graph.getEdges()[i][j];
            }
        }
		for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (distToMatrix[i][j] == 0) continue; // optimization
                for (int k=0;k<j;k++) {
                    if (distToMatrix[j][i] != INF && distToMatrix[i][k] !=INF && distToMatrix[j][k] > distToMatrix[j][i] + distToMatrix[i][k]) {
                    	distToMatrix[j][k] = distToMatrix[j][i] + distToMatrix[i][k];
                    	distToMatrix[k][j] = distToMatrix[j][i] + distToMatrix[i][k];
                    }
                }
            }
        }
	}
	
	/**
	 * Floyd Algorithm using 1D Adjacency Matrix
	 * @param AdjacencyMatrix1DGraph graph
	 * @param n
	 */
	public void floydAlgorithm(AdjacencyMatrix1DGraph graph, int n){
		distToList = new int[n*(n-1)/2];
		for(int i=0;i<distToList.length;i++){
			distToList[i] = graph.getEdges()[i];
		}
		int positionij;
		int positionik;
		int positionjk;
		for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
            	if(i == j) continue;
            	positionij = getposition(i,j);
            	for(int k=0;k<j;k++){
            		if(i!=k){
            			positionik = getposition(i,k);
            			positionjk = getposition(j,k);
	                    if (distToList[positionij] != INF && distToList[positionik] !=INF && distToList[positionjk] > distToList[positionij] + distToList[positionik]) {
	                    	distToList[positionjk] = distToList[positionij] + distToList[positionik];
	                    }
            		}
            	}
            }
		}
	}

	/**
	 * Floyd Algorithm using Adjacency List
	 * @param LinkedGraph graph
	 * @param n
	 */
	public void floydAlgorithm(LinkedGraph graph, int n){
		distToMatrix = new int[n][n];
		ArrayList<LinkList<Vertex>> al = graph.getAl();
		
		int distij;
		int distik;
		int distjk;
		for(int i=0;i<n;i++){
			for (int j=0;j<n;j++){
				if (i == j) continue;// optimization
				for (int k=0;k<j;k++) {
					distij = getWeightBetweenTwoNodes(i,j,al);
					distik = getWeightBetweenTwoNodes(i,k,al);
					distjk = getWeightBetweenTwoNodes(j,k,al);
					if (distij != INF && distik !=INF && distik != 0 && distjk > distij + distik) {
						al = setWeightBetweenTwoNodes(i,j,k,al,distjk,distij,distik);
						al = setWeightBetweenTwoNodes(i,k,j,al,distjk,distij,distik);
                    }
				}
			}
		}
		distToLink.setAl(al);
	}

	/**
	 * Set the weight between two nodes when using Adjacency List
	 * @param i
	 * @param j
	 * @param k
	 * @param al
	 * @param distjk
	 * @param distij
	 * @param distik
	 * @return arraylist after set
	 */
	private ArrayList<LinkList<Vertex>> setWeightBetweenTwoNodes(int i, int j, int k, ArrayList<LinkList<Vertex>> al, int distjk, int distij, int distik) {
		LinkList<Vertex> element = al.get(j);
		Node<Vertex> curr = element.getHead();
		if(distjk != INF){
			while(curr != null){
				if(curr.getData().getVertexname() == k){
					Vertex dataDelete = curr.getData();
					element.delete(dataDelete);
					break;
				}
				curr = curr.getNext();
			}
		}
		Vertex dataAdd = new Vertex(k,distij+distik);
		element.insert(dataAdd);
		al.set(j, element);
		return al;
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
	private int getposition(int i, int j) {
		int positionij;
		if(i>j){
    		positionij = i*(i-1)/2+j;
		}else{
			positionij = j*(j-1)/2+i;
		}
		return positionij;
	}

	public int[][] getDistToMatrix() {
		return distToMatrix;
	}

	public void setDistToMatrix(int[][] distToMatrix) {
		this.distToMatrix = distToMatrix;
	}

	public int[] getDistToList() {
		return distToList;
	}

	public void setDistToList(int[] distToList) {
		this.distToList = distToList;
	}

	public ArrayList<LinkList<Vertex>> getAl() {
		return al;
	}

	public void setAl(ArrayList<LinkList<Vertex>> al) {
		this.al = al;
	}
	
	public LinkedGraph getDistToLink() {
		return distToLink;
	}

	public void setDistToLink(LinkedGraph distToLink) {
		this.distToLink = distToLink;
	}

}
