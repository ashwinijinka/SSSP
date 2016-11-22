import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

public class SSSP {

	public static final int MAX_VERTICES = 20;
	public static final int MIN_VERTICES = 10;
	public static final int MAX_COMVERTICES = 5;
	public static ArrayList<Integer> masterVerticeList;
	public static int[][] masterAdjMatrix;

	public static void main(String[] args) {

		HashMap<Integer, Graph> graphs = new HashMap<Integer, Graph>();
		int nGraph = 2;
		graphs = generateGraphs(nGraph);

		mergeGraph(graphs);

	}

	public static HashMap<Integer, Graph> generateGraphs(int nGraph) {
		int nVertices = 0;
		int comVertices = 5;
		int tempVertice = 0;
		int count = 0;

		ArrayList<Integer> verticeList = new ArrayList<Integer>();
		HashMap<Integer, ArrayList<Integer>> matrices = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> comVerticeList = new ArrayList<Integer>();
		int[][] comVertWmatrix = new int[MAX_COMVERTICES][MAX_COMVERTICES];
		ArrayList<Integer> tempComVertList = new ArrayList<Integer>();
		HashMap<Integer, Graph> graphs = new HashMap<>();

		for (int k = 1; k <= MAX_COMVERTICES; k++) {
			tempComVertList.add(k);
		}
		comVertWmatrix = matrixGenerator(comVerticeList, comVertWmatrix, tempComVertList);

		for (int i = 0; i < nGraph; i++) {

			count = 0;
			nVertices = randomInteger(MIN_VERTICES, MAX_VERTICES);
			System.out.println("nvertices: " + nVertices);
			comVertices = randomInteger(1, 5);
			System.out.println("comVertices: " + comVertices);
			/*
			 * while(!checkPosVertices(comVertices,nVertices,matrices)){
			 * nVertices = randomInteger(10, 20);
			 * System.out.println("nvertices: " + nVertices); comVertices =
			 * randomInteger(0, 5); System.out.println("comVertices: " +
			 * comVertices); }
			 */
			tempVertice = randomInteger(1, MAX_COMVERTICES);
			while (count != comVertices) {

				if (comVerticeList.contains(tempVertice)) {
					tempVertice = randomInteger(1, MAX_COMVERTICES);
				} else {
					comVerticeList.add(tempVertice);
					verticeList.add(tempVertice);
					masterVerticeList.add(tempVertice);
					System.out.print(tempVertice);
					System.out.print(" ");
					count++;
				}
			}
			
			tempVertice = randomInteger(1, 500);
			while (nVertices != count) {
				if (verticeList.contains(tempVertice)||masterVerticeList.contains(tempVertice)) {
					tempVertice = (randomInteger(1, 200));
				} else {
					// tempVertice
					verticeList.add(tempVertice);
					masterVerticeList.add(tempVertice);
					System.out.print(tempVertice);
					System.out.print(" ");

					count++;
				}
			}
			// matrices.put(i, verticeList);
			// tempMat=new int[verticeList.size()][verticeList.size()];
			// tempMat=
			Graph graph = new Graph(verticeList, matrixGenerator(verticeList, comVertWmatrix, tempComVertList),comVerticeList);
			System.out.println("");
			//0 1 2 3 4 5 6 7
			/*int[][] adj1={  {0,8,0,3,0,0,0,0},
			                {8,0,8,0,0,0,0,0},
			                {0,8,0,0,0,9,0,0},
			                {3,0,0,0,0,9,0,0},
			                {0,0,0,0,0,0,5,3},
			                {0,0,9,9,0,0,0,0},
			                {0,0,0,0,5,0,0,0},
			                {0,0,0,0,3,0,0,0},};
			int[][] adj={	{0, 2,3,10,5},
	                		{2, 0,1, 2,3},
	                		{3, 1,0, 5,4},
	                		{10,2,5, 0,6},
	                		{5, 3,4, 6,0},
	                		};
			int[][] adj2=new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
				{4, 0, 8, 0, 0, 0, 0, 11, 0},
				{0, 8, 0, 7, 0, 4, 0, 0, 2},
				{0, 0, 7, 0, 9, 14, 0, 0, 0},
				{0, 0, 0, 9, 0, 10, 0, 0, 0},
				{0, 0, 4, 14, 10, 0, 2, 0, 0},
				{0, 0, 0, 0, 0, 2, 0, 1, 6},
				{8, 11, 0, 0, 0, 0, 1, 0, 7},
				{0, 0, 2, 0, 0, 0, 6, 7, 0}
				};
			verticeList.clear();
			for (int k = 1; k <= adj.length; k++) {
				verticeList.add(k);
			}
			Graph graph=new Graph(verticeList,adj);*/
			printGraph(graph);
			Dijkstra.findSSSP(graph);
			graphs.put(i, graph);
		
			comVerticeList.clear();
			verticeList.clear();

		}
		return graphs;
	}

	public static int[][] matrixGenerator(ArrayList<Integer> vertices, int[][] comVertmatrix,
			ArrayList<Integer> tempComVertList) {

		int weight = 0;

		int[][] tempMat = new int[vertices.size()][vertices.size()];

		if (vertices.isEmpty()) {
			tempMat = new int[MAX_COMVERTICES][MAX_COMVERTICES];
			for (int a = 0; a < MAX_COMVERTICES; a++) {
				for (int b = 0; b < MAX_COMVERTICES; b++) {
					if (a == b) {
						weight = 0;
					} else {
						if (tempMat[b][a] == 0) {
							weight = randomInteger(1, 15);
						} else {
							weight = tempMat[b][a];
						}
					}
					tempMat[a][b] = weight;
				}
			}

			return tempMat;
		}

		for (int x = 0; x < vertices.size(); x++) {
			for (int y = 0; y < vertices.size(); y++) {

				if (tempComVertList.contains(vertices.get(x)) && tempComVertList.contains(vertices.get(y))) {
					weight = comVertmatrix[vertices.get(x) - 1][vertices.get(y) - 1];
				} else {
					if(x==y){
						weight=0;
					}
					else{
						if(tempMat[y][x]==0){
						weight = randomInteger(1, 15);
						}
						else{
							
							weight=tempMat[y][x];
						}
					}
					
				}
				tempMat[x][y] = weight;
			}
		}

		return tempMat;

	}

	private static Graph mergeGraph(HashMap<Integer, Graph> G) {
		// TODO Auto-generated method stub
		HashMap<Integer, Graph> unMergedGraph= G;
		masterAdjMatrix=new int[masterVerticeList.size()][masterVerticeList.size()];
		Graph masterGraph=new Graph();
		int maxComVert=0;
		int maxGraph=99;
		int temp=0;
		int nMergedG=0;
		while(nMergedG!=G.size()){
		if (maxGraph == 99) {
			masterGraph.setAdjMatrix(masterAdjMatrix);
			masterGraph.setVertices(masterVerticeList);
		}
		else{
			for (int i = 0; i < G.size(); i++) {
				temp = G.get(i).getComVertices().size();
				if (maxComVert < temp) {
					maxComVert = temp;
					maxGraph = i;
					
				}
			}
			for(G.getVer)
			masterGraph.getVertices().add(e)
			
		}
		}
		
		return null;
	}

	private static void printGraph(Graph graph) {
		// TODO Auto-generated method stub
		int nVert = (int) graph.getVertices().size();
		int[][] tempGraph = new int[nVert][nVert];
		tempGraph = graph.getAdjMatrix();

		String header = "\t|\t";
		String headerLine = "-";
		for (int k = 0; k < nVert; k++) {
			header += graph.getVertices().get(k) + "\t";

		}
		for (int l = 0; l < 9 * nVert; l++) {
			headerLine = headerLine + "-";
		}

		System.out.println(header);
		System.out.println(headerLine);
		try {
			String str;
			for (int i = 0; i < nVert; i++) {
				str = graph.getVertices().get(i) + "\t|\t";
				for (int j = 0; j < nVert; j++) {
					str += tempGraph[i][j] + "\t";
				}

				System.out.println(str);
				// str = graph.getVertices().get(i+1)+"\t"+"|\t";
			}

		} catch (Exception e) {
			System.out.println("Matrix is empty!!");
		}

	}
	
	

	public static int randomInteger(int min, int max) {

		int randomNum = min + (int) (Math.random() * ((max - min) + 1));
		return randomNum;
	}

	/*
	 * public static boolean checkPosVertices(int cV, int nV, HashMap<Integer,
	 * Set> map) { if (!map.isEmpty()) { for (int i = 0; i < map.size(); i++) {
	 * 
	 * if (((map.get(i).size()) + nV - cV) >= MAX_VERTICES) { return false; } }
	 * }
	 * 
	 * return true;
	 * 
	 * }
	 */

}
