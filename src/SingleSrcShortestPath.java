import java.sql.NClob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SingleSrcShortestPath {

	public static final int MAX_VERTICES = 20;

	public static void main(String[] args) {
		
		HashMap<Integer, Set> matrices = new HashMap<Integer, Set>();
		// TODO Auto-generated method stub
	graphGenerator(matrices);

	}

	public static int randomInteger(int min, int max) {
		
		int randomNum = min + (int) (Math.random() * ((max - min) + 1));
		return randomNum;
	}

	public static boolean checkPosVertices(int cV,int nV, HashMap<Integer, Set> map) {
		if (!map.isEmpty()) {
			for (int i = 0; i < map.size(); i++) {

				if (((map.get(i).size()) + nV - cV) >= MAX_VERTICES) {
					return false;
				}
			}
		}

		return true;

	}
	
	public static void graphGenerator(HashMap<Integer, Set> mat){
		int nVertices = 10;
		int comVertices=5;
	
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < 10; i++) {
			nVertices = randomInteger(10, 20);
			System.out.println("nvertices: " + nVertices);
			comVertices = randomInteger(1, 5);
			System.out.println("comVertices: " + comVertices);
			/*while(!checkPosVertices(comVertices,nVertices,matrices)){
				nVertices = randomInteger(10, 20);
				System.out.println("nvertices: " + nVertices);
				 comVertices = randomInteger(0, 5);
				 System.out.println("comVertices: " + comVertices);
			}*/
			

			int tempVertice = 0;
			int count = 0;

			tempVertice = randomInteger(1, nVertices);
			while (nVertices != count) {
				if (set.contains(tempVertice)) {
					tempVertice = (randomInteger(1, 20));
				} else {
					// tempVertice
					set.add(tempVertice);

					System.out.print(tempVertice);
					System.out.print(" ");

					count++;
				}
			}
			mat.put(i, set);
			set.clear();
			System.out.println("");
		}
	}
	
	

}
