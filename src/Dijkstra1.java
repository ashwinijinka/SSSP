
import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
 
public class Dijkstra1
{
    private int distances[];
    private Set<Integer> settled;
    private PriorityQueue<Node> priorityQueue;
    private int number_of_nodes;
    private int adjacencyMatrix[][];
 
    public  Dijkstra(int number_of_nodes)
    {
        this.number_of_nodes = number_of_nodes;
        distances = new int[number_of_nodes + 1];
        settled = new HashSet<Integer>();
        priorityQueue = new PriorityQueue<Node>(number_of_nodes,new Node());
        adjacencyMatrix = new int[number_of_nodes + 1][number_of_nodes + 1];
    }
 
    public void dijkstra_algorithm(int adjacency_matrix[][], int source)
    {
        int evaluationNode;
        for (int i = 1; i <= number_of_nodes; i++)
            for (int j = 1; j <= number_of_nodes; j++)
                adjacencyMatrix[i][j] = adjacency_matrix[i][j];
 
        for (int i = 1; i <= number_of_nodes; i++)
        {
            distances[i] = Integer.MAX_VALUE;
        }
 
        priorityQueue.add(new Node(source, 0));
        distances[source] = 0;
        while (!priorityQueue.isEmpty())
        {
            evaluationNode = getNodeWithMinimumDistanceFromPriorityQueue();
            settled.add(evaluationNode);
            evaluateNeighbours(evaluationNode);
        }
    } 
 
    private int getNodeWithMinimumDistanceFromPriorityQueue()
    {
        int node = priorityQueue.remove().node;
        return node;
    }
 
    private void evaluateNeighbours(int evaluationNode)
    {
        int edgeDistance = -1;
        int newDistance = -1;
 
        for (int destinationNode = 1; destinationNode <= number_of_nodes; destinationNode++)
        {
            if (!settled.contains(destinationNode))
            {
                if (adjacencyMatrix[evaluationNode][destinationNode] != Integer.MAX_VALUE)
                {
                    edgeDistance = adjacencyMatrix[evaluationNode][destinationNode];
                    newDistance = distances[evaluationNode] + edgeDistance;
                    if (newDistance < distances[destinationNode])
                    {
                        distances[destinationNode] = newDistance;
                    }
                    priorityQueue.add(new Node(destinationNode,distances[destinationNode]));
                }   
            }
        }
    }
 
    public static void findSSSP(Graph graph)
    {
        int adjacency_matrix[][]=graph.getAdjMatrix();
        int number_of_vertices=graph.getVertices().size();
        int source = graph.getVertices().get(SSSP.randomInteger(0, number_of_vertices-1));
        try
        {
  
            Dijkstra dijkstrasPriorityQueue = new Dijkstra(number_of_vertices);
            dijkstrasPriorityQueue.dijkstra_algorithm(adjacency_matrix, source);
 
            System.out.println("The Shorted Path to all nodes are ");
            for (int i = 1; i <= dijkstrasPriorityQueue.distances.length - 1; i++)
            {
                System.out.println(source + " to " + i + " is " + dijkstrasPriorityQueue.distances[i]);
            }
        } catch (InputMismatchException inputMismatch)
        {
            System.out.println("Wrong Input Format");
        }
    }	
}
class Node implements Comparator<Node>
{
    public int node;
    public int cost;
 
    public Node()
    {
    }
 
    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }
 
    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}