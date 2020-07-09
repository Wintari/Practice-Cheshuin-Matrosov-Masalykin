package application;

import application.algorithm.BridgesFinder;
import application.algorithm.Graph;
import application.algorithm.GraphLoader;
import application.gui.MainWindow;
import application.stepper.Stepper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class Application {
    public static final Stepper stepper = new Stepper();
    public static Graph graph = new Graph();

    public static void main(String[] args)
    {
        MainWindow.execute(args);
    }

    public static void setNewGraph(Graph graph)
    {
        Application.graph = graph;

        BridgesFinder finder = new BridgesFinder();
        finder.findBridges(graph);
    }

    public static void loadGraphFromFile(String path) throws FileNotFoundException {
        Application.graph = GraphLoader.LoadGraph(path);

        BridgesFinder finder = new BridgesFinder();
        finder.findBridges(graph);


        LinkedList<String> arr = graph.toStringList();
        System.out.println("OK");
    }

    public static void saveGraphToFile(String path) throws IOException {
        GraphLoader.saveGraph( path, Application.graph);
    }
}
