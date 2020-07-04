package application;

import application.algorithm.Graph;
import application.gui.MainWindow;
import application.stepper.Stepper;

public class Application {
    private static final Stepper stepper = new Stepper();

    public static void main(String[] args)
    {
        MainWindow.execute(args);
    }

    public static void setNewGraph(Graph graph)
    {
        System.out.println("New graph setted.");
    }

    public static void loadGraphFromFile(String path)
    {
        System.out.println("Loading graph from file: " + path);
    }

    public static void saveGraphToFile(String path)
    {
        System.out.println("Saving graph to file: " + path);
    }

    public static Stepper getStepper()
    {
        return stepper;
    }
}
