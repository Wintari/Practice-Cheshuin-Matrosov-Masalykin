package application.algorithm;

import application.Application;
import application.stepper.*;

import  java.util.*;

public class BridgesFinder{
    private final HashMap<String, Boolean> used = new HashMap<>();
    private int timer;
    private final HashMap<String, Integer> tin = new HashMap<>();
    private final HashMap<String, Integer> fup = new HashMap<>();

    private void dfs(Graph.Node node, Graph.Node parent){
        used.put(node.getName(), true);
        tin.put(node.getName(), timer);
        fup.put(node.getName(), timer);

        timer += 1;

        for (Graph.Node edge : node.getEdges()) {
            if (edge == parent) continue;
            if (used.get(edge.getName())) {
              
                Application.stepper.newStep(null,
                        new EdgeAction(EdgeAction.Action.EDGE_STARTED, edge, node));

                int newFup = Math.min(fup.get(node.getName()), tin.get(edge.getName()));

                NodeAction fupUpdate = new NodeAction(NodeAction.Action.TIME_UPDATED,
                        node, tin.get(node.getName()), newFup, tin.get(node.getName()),
                        fup.get(node.getName()));

                fup.put(node.getName(), newFup);

                Application.stepper.newStep(fupUpdate,
                        new EdgeAction(EdgeAction.Action.EDGE_FINISHED_COMMON, edge, node));
            }
            else {
                Application.stepper.newStep(new NodeAction(NodeAction.Action.NODE_STARTED,
                                edge, timer, timer, -1, -1),
                        new EdgeAction(EdgeAction.Action.EDGE_STARTED, edge, node));

                dfs(edge, node);
                int newFup = Math.min(fup.get(node.getName()), fup.get(edge.getName()));

                NodeAction fupUpdate = new NodeAction(NodeAction.Action.TIME_UPDATED,
                        node, tin.get(node.getName()), newFup, tin.get(node.getName()),
                        fup.get(node.getName()));

                fup.put(node.getName(), newFup);
                if (fup.get(edge.getName()) > tin.get(node.getName())) {

                    Application.stepper.newStep(fupUpdate,
                            new EdgeAction(EdgeAction.Action.EDGE_FINISHED_BRIDGE, edge, node));

                    System.out.println("BRIDGE: " + edge.getName() + " -- " + node.getName());
                }
                else
                {
                    Application.stepper.newStep(fupUpdate,
                            new EdgeAction(EdgeAction.Action.EDGE_FINISHED_COMMON, edge, node));
                }
            }
        }

        Application.stepper.newStep(new NodeAction(NodeAction.Action.NODE_FINISHED,
                node, tin.get(node.getName()), fup.get(node.getName()),
                tin.get(node.getName()), fup.get(node.getName())), null);
    }

    public void findBridges(Graph graph){
        timer = 0;

        for(Graph.Node node : graph.getNodes()){
            used.put(node.getName(), Boolean.FALSE);
        }

        for(Graph.Node node : graph.getNodes()){
            if(!used.get(node.getName())) {
                Application.stepper.newStep(new NodeAction(NodeAction.Action.NODE_STARTED,
                        node, timer, timer, -1, -1), null);
                dfs(node, null);
            }
        }
    }
}