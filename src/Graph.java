import java.util.ArrayList;

public class Graph {

	public ArrayList<Integer> vertices = new ArrayList<Integer>();
	public int[][] adjMatrix;
	public ArrayList<Integer> comVertices= new ArrayList<>();
	public Graph(){
		
	}

	public Graph(ArrayList<Integer> vertices, int[][] adjMatrix, ArrayList<Integer> comVertices) {
		this.vertices = vertices;
		this.adjMatrix = adjMatrix;
		this.comVertices=comVertices;
	}

	public ArrayList<Integer> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Integer> vertices) {
		this.vertices = vertices;
	}

	public int[][] getAdjMatrix() {
		return adjMatrix;
	}

	public void setAdjMatrix(int[][] adjMatrix) {
		this.adjMatrix = adjMatrix;
	}

	public ArrayList<Integer> getComVertices() {
		return comVertices;
	}

	public void setComVertices(ArrayList<Integer> comVertices) {
		this.comVertices = comVertices;
	}

}
