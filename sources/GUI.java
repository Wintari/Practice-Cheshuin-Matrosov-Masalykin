public interface GUI {
    default void exit()
    {
        System.out.println("Exit button clicked.");
    }

    default void nextStep()
    {
        System.out.println("Next step button clicked.");
    }

    default void prevStep()
    {
        System.out.println("Prev step button clicked.");
    }

    default void setNewGraph(Graph graph)
    {
        System.out.println("New graph setted.");
    }

    default void loadGraphFromFile(String path)
    {
        System.out.println("Loading graph from file: " + path);
    }

    default void saveGraphToFile(String path)
    {
        System.out.println("Saving graph to file: " + path);
    }

    void pushNodeToStack(Graph.Node node);
    void popNodeFromStack();
    void drawNode(Graph.Node node);
    void drawEdge(String from, String to);
    void highlightNode(Graph.Node node, String htmlColor);
    void highlightEdge(String from, String to, String htmlColor);
    void unhighlightNode(Graph.Node node);
    void unhighlightEdge(String from, String to);
}
