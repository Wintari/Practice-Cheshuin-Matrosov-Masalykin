package application.stepper;

import application.Application;
import application.algorithm.Graph;
import application.graphviz.Proba;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Stepper {
    private final ArrayList<HashMap<String, NodeInfo>> nodeSteps = new ArrayList<>();
    private final ArrayList<HashMap<Pair<String, String>, EdgeInfo>> edgeSteps = new ArrayList<>();
    private final ArrayList<ArrayList<String>> stackSteps = new ArrayList<>();
    private int cursor = -1;

    private static class NodeInfo
    {
        private final String name;
        private final String color;
        private final String label;

        public NodeInfo(String name, String color, int tin, int fup) {
            this.name = name;
            this.color = color;
            this.label = "Tin: " + Integer.toString(tin) + '\n' + "Fup: " + Integer.toString(fup);
        }

        public String toStr()
        {
            return name + " [color=\"" + color + "\"" + ", xlabel=\"" + label + "\"" + "]";
        }
    }

    private static class EdgeInfo
    {
        private final String from;
        private final String to;
        private final String color;

        public EdgeInfo(String from, String to, String color) {
            this.from = from;
            this.to = to;
            this.color = color;
        }

        public String toStr()
        {
            return from + " -- " + to + " [color=\"" + color + "\"]";
        }
    }

    public void newStep(NodeAction nodeAction, EdgeAction edgeAction)
    {
        HashMap<String, NodeInfo> nodeStep = new HashMap<>(nodeSteps.get(nodeSteps.size() - 1));
        HashMap<Pair<String, String>, EdgeInfo> edgeStep = new HashMap<>(edgeSteps.get(edgeSteps.size() - 1));
        ArrayList<String> stackStep = new ArrayList<>(stackSteps.get(stackSteps.size() - 1));

        if (nodeAction != null)
        {
            switch (nodeAction.getAction())
            {
                case TIME_UPDATED:
                    nodeStep.put(nodeAction.getNode().getName(),new NodeInfo(nodeAction.getNode().getName(),
                            "yellow", nodeAction.getTin(), nodeAction.getFup()));
                    break;
                case NODE_STARTED:
                    nodeStep.put(nodeAction.getNode().getName(),new NodeInfo(nodeAction.getNode().getName(),
                            "blue", nodeAction.getTin(), nodeAction.getFup()));

                    stackStep.add(nodeAction.getNode().getName());
                    break;
                case NODE_FINISHED:
                    nodeStep.put(nodeAction.getNode().getName(),
                            new NodeInfo(nodeAction.getNode().getName(),
                            "grey", nodeAction.getTin(), nodeAction.getFup()));

                    stackStep.remove(stackStep.size() - 1);
                    break;
            }
        }

        if (edgeAction != null)
        {
            Pair<String, String> dEdge = new Pair<>(edgeAction.getParent().getName(), edgeAction.getNode().getName());
            Pair<String, String> rEdge = new Pair<>(edgeAction.getNode().getName(), edgeAction.getParent().getName());
            Pair<String, String> edge = null;
            if(edgeStep.containsKey(dEdge))
            {
                edge = dEdge;
            }
            else if(edgeStep.containsKey(rEdge))
            {
                edge = rEdge;
            }

            if(edge != null) {
                switch (edgeAction.getAction()) {
                    case EDGE_STARTED:
                        edgeStep.put(edge,
                                new EdgeInfo(edge.getKey(), edge.getValue(), "blue"));
                        break;
                    case EDGE_FINISHED_COMMON:
                        edgeStep.put(edge,
                                new EdgeInfo(edge.getKey(), edge.getValue(), "grey"));
                        break;
                    case EDGE_FINISHED_BRIDGE:
                        edgeStep.put(edge,
                                new EdgeInfo(edge.getKey(), edge.getValue(), "green"));
                        break;
                }
            }
        }

        nodeSteps.add(nodeStep);
        edgeSteps.add(edgeStep);
        stackSteps.add(stackStep);
    }

    private void activeStep(int step)
    {
        if (cursor < stackSteps.size() && cursor >= 0) {
            LinkedList<String> markup = new LinkedList<>();

            for (EdgeInfo edge : edgeSteps.get(step).values())
            {
                markup.push(edge.toStr());
            }

            for (NodeInfo node : nodeSteps.get(step).values())
            {
                markup.push(node.toStr());
            }

            Proba.makeGraph(markup);
        }
    }

    private void fromGraph(Graph graph)
    {
        HashMap<String, NodeInfo> nodeStep = new HashMap<>();
        HashMap<Pair<String, String>, EdgeInfo> edgeStep = new HashMap<>();
        ArrayList<String> stackStep = new ArrayList<>();

        for(Pair<String, String> edge : graph.edgesToList())
        {
            edgeStep.put(edge, new EdgeInfo(edge.getKey(),edge.getValue(), "black"));
        }

        for(String node : graph.nodesToList())
        {
            nodeStep.put(node, new  NodeInfo(node, "black", -1, -1));
        }

        nodeSteps.add(nodeStep);
        edgeSteps.add(edgeStep);
        stackSteps.add(stackStep);

        nextStep();
    }

    public ArrayList<String> nextStep()
    {
        if (cursor + 1 < stackSteps.size())
        {
            cursor += 1;
            activeStep(cursor);
            return stackSteps.get(cursor);
        }
        else
        {
            return new ArrayList<>();
        }
    }

    public ArrayList<String> prevStep()
    {
        if (cursor > 0)
        {
            cursor -= 1;
            activeStep(cursor);
            return stackSteps.get(cursor);
        }
        else
        {
            return new ArrayList<>();
        }
    }

    public int getStepCount()
    {
        return stackSteps.size();
    }

    public void clear()
    {
        nodeSteps.clear();
        edgeSteps.clear();
        stackSteps.clear();

        fromGraph(Application.graph);

        cursor = 0;
    }

}
