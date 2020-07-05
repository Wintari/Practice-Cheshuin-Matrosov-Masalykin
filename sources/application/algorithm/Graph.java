package application.algorithm;

import java.util.*;

/**
 * Represents an undirected graph that contains many unique nodes.
 * A Node can contain many edges to other nodes.
 */
public class Graph {
    /**
     * Represents an Node of graph with coordinates and set of edges.
     */
    public static class Node
    {
        final private String name;
        private final HashMap<Node, Node> edges = new HashMap<>();

        /**
         * Create a new object of this class..
         * @param name This Node's name.
         */
        Node(String name)
        {
            this.name = name;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }

            if (o == null || getClass() != o.getClass())
            {
                return false;
            }

            Node node = (Node) o;

            return name.equals(node.name);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(name);
        }

        /**
         * Return the name of this Node.
         * @return name of this Node.
         */
        public String getName()
        {
            return name;
        }

        /**
         * Return the set of edges of this Node.
         * @return Set of edges of this Node.
         */
        public Set<Node> getEdges()
        {
            return edges.keySet();
        }
    }

    private final HashMap<Node, Node> nodes = new HashMap<>();

    /**
     * Add new node to the graph.
     * @param name new Node's name.
     */
    public void addNode(String name)
    {
        Node node = new Node(name);
        nodes.putIfAbsent(node, node);
    }

    /**
     * Delete node from graph.
     * @param name name of Node that we want to delete
     */
    public void deleteNode(String name)
    {
        Node node = nodes.get(new Node(name));
        if(node != null)
        {
            for (Node toNode : node.edges.keySet())
            {
                toNode.edges.remove(node);
            }

            node.edges.clear();
            nodes.remove(node);
        }
    }

    /**
     * Add new edge to the graph.
     * @param from first node name.
     * @param to second node name position.
     * @return True, if edge added, and False if error has occurred.
     */
    public boolean addEdge(String from, String to)
    {
        if(nodes.containsKey(new Node(from)) && nodes.containsKey(new Node(to)))
        {
            Node fromNode = nodes.get(new Node(from));
            Node toNode = nodes.get(new Node(to));

            fromNode.edges.putIfAbsent(toNode, toNode);
            toNode.edges.putIfAbsent(fromNode, fromNode);

            return true;
        }

        return false;
    }

    /**
     * Delete edge from graph.
     * @param from first node name.
     * @param to second node name position.
     */
    public void deleteEdge(String from, String to)
    {
        if(nodes.containsKey(new Node(from)) && nodes.containsKey(new Node(to)))
        {
            Node fromNode = nodes.get(new Node(from));
            Node toNode = nodes.get(new Node(to));

            fromNode.edges.remove(toNode);
            toNode.edges.remove(fromNode);
        }
    }

    /**
     * Return Node by it's name.
     * @param name name of Node.
     * @return Node, if it is exist and null, if not exist.
     */
    public Node getNode(String name)
    {
        return nodes.get(new Node(name));
    }

    /**
     * Return set of Nodes, that graph contains.
     * @return Set of Nodes.
     */
    public Set<Node> getNodes()
    {
        return nodes.keySet();
    }

    public LinkedList<String> toStringList()
    {
        LinkedList<String> list = new LinkedList<>();
        for(Node node : getNodes())
        {
            list.add(node.name);
        }

        HashSet<Map.Entry<String, String>> edges = new HashSet<>();

        for (Node node : getNodes())
        {
            for(Node edge : node.edges.keySet()) {
                Map.Entry<String, String> dEdge = Map.entry(node.name, edge.name);
                Map.Entry<String, String> rEdge = Map.entry(edge.name, node.name);


                if (!edges.contains(dEdge) && !edges.contains(rEdge))
                {
                    edges.add(Map.entry(node.name, edge.name));
                }
            }
        }

        for(Map.Entry<String, String> edge : edges)
        {
            list.add(edge.getKey() + " -- " + edge.getValue());
        }

        return list;
    }
}
