package application.algorithm;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class GraphLoader {

    public static Graph LoadGraph(String path) throws FileNotFoundException {
        Graph graph = new Graph();
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(path))));
        String currString;
        scanner.useDelimiter("\n");
        currString = scanner.next();
        String[] subStr;
        while (!currString.equals("")) {
            subStr = currString.split(" ");
            System.out.println(subStr[0] + " " + subStr[1] + " " + subStr[2]);
            graph.addNode(subStr[0], Integer.parseInt(subStr[1]), Integer.parseInt(subStr[2]));
            currString = scanner.next();
        }
        while (scanner.hasNext()) {
                String dst, src;
                currString = scanner.next();
                subStr = currString.split(" ");
                if(subStr[0].equals(""))
                    break;
                src = subStr[0];
                dst = subStr[1];
                System.out.println(src + " " + dst);
                System.out.println(graph.addEdge(src, dst));
            }
            scanner.close();
        return graph;
    }

    public static void saveGraph(String path, Graph graph) throws IOException {
        FileWriter writer = new FileWriter(path, false);
        Iterator<Graph.Node> i = graph.getNodes().iterator();
        while(i.hasNext()){
            Graph.Node curr = i.next();
            writer.write(curr.getName() + " " + curr.getX() + " " + curr.getY() + "\n");
        }
        writer.write("\n");
        i = graph.getNodes().iterator();
        while(i.hasNext()){
            Graph.Node curr = i.next();
            for (Graph.Node node : curr.getEdges()) {
                writer.write(curr.getName() + " " + node.getName() + "\n");
            }
        }
        writer.close();
    }
}

