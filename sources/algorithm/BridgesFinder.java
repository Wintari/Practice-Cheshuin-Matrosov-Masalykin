package application.algorithm;

import  java.util.*;
import application.algorithm.Graph;


public class BridgesFinder{

    private int min(int a, int b){
        if (a<b)
            return a;
        else
            return b;
    }

    private HashMap<String, Boolean> used = new HashMap<>();
    private int timer;
    private HashMap<String, Integer> tin = new HashMap<>();
    private HashMap<String, Integer> fup = new HashMap<>();

    private void dfs(Graph.Node v, Graph.Node p){
        used.put(v.getName(), true);
        tin.put(v.getName(), timer);
        fup.put(v.getName(), timer);
        timer++;
        Iterator<Graph.Node> iter = v.getEdges().iterator();
        while(iter.hasNext()){
            Graph.Node t0 = iter.next();
            if(t0 == p) continue;
            if(used.get(t0.getName())){
                fup.put(v.getName(), min(fup.get(v.getName()), tin.get(t0.getName())));
            }
            else{
                dfs(t0, v);
                fup.put(v.getName(), min(fup.get(v.getName()), fup.get(t0.getName())));
                if(fup.get(t0.getName()) > tin.get(v.getName()))
                    System.out.println("BRIDGE: " + t0.getName()+" -- "+ v.getName());
            }
        }
    }

    public void findBridges(Graph graph){
        timer = 0;
        Set<Graph.Node> keys = graph.getNodes();
        Iterator<Graph.Node> i = keys.iterator();
        while(i.hasNext()){
            used.put(i.next().getName(), Boolean.FALSE);
        }
        Graph.Node curr;
        i = keys.iterator();
        while(i.hasNext()){
            curr = i.next();
            if(!used.get(curr.getName()))
                dfs(curr, null);
        }
    }
}