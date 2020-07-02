import java.util.*;

public class Application {
    public static void main(String[] args)
    {
        Graph graph = new Graph();

        graph.addNode("A",0, 0);
        graph.addNode("B",1, 1);
        graph.addNode("C",0, 1);

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");

        Graph.Node a = graph.getNode("A");
        Graph.Node b = graph.getNode("B");
        Graph.Node c = graph.getNode("C");

        graph.deleteNode("C");
        graph.deleteEdge("A", "B");

        Set<Graph.Node> nodes = graph.getNodes();

        System.out.println("Application started");
    }
}
