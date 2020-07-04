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
    static class Node
    {
        final private String name;
        private final int x;
        private final int y;
        private final HashMap<Node, Node> edges = new HashMap<>();

        /**
         * Create a new object of this class..
         * @param name This Node's name.
         */
        Node(String name)
        {
            this.name = name;
            this.x = 0;
            this.y = 0;
        }

        /**
         * Create a new object of this class..
         * @param name This Node's name.
         * @param x This Node's X position.
         * @param y This Node's Y position.
         */
        Node(String name, int x, int y)
        {
            this.name = name;
            this.x = x;
            this.y = y;
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
         * Return the X posotion of this Node.
         * @return X posotion of this Node.
         */
        public int getX()
        {
            return x;
        }

        /**
         * Return the Y posotion of this Node.
         * @return Y posotion of this Node.
         */
        public int getY()
        {
            return y;
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
     * @param x new Node's X position.
     * @param y new Node's Y position.
     */
    public void addNode(String name, int x, int y)
    {
        Node node = new Node(name, x, y);
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
}
