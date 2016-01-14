package graph;

public class Vertex {
	private int vertexname;
	private int weight;
	
	public Vertex(int c, int i){
		vertexname = c;
		weight = i;
	}

	public int getVertexname() {
		return vertexname;
	}

	public void setVertexname(int vertexname) {
		this.vertexname = vertexname;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String toString(){
		String s = "";
		s = "name:" + (vertexname+1) + " weight:" + weight; 
		return s;
	}
}
