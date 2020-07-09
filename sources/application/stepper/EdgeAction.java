package application.stepper;

import application.algorithm.Graph;

public class EdgeAction
{
    public Action getAction() {
        return action;
    }

    public Graph.Node getNode() {
        return node;
    }

    public Graph.Node getParent() {
        return parent;
    }

    private final Action action;
    private final Graph.Node node;
    private final Graph.Node parent;

    public EdgeAction(Action action, Graph.Node node, Graph.Node parent) {
        this.action = action;
        this.node = node;
        this.parent = parent;
    }

    public EdgeAction back()
    {
        return new EdgeAction(Action.back(action), node, parent);
    }

    public enum Action
    {
        NONE,
        BASE,
        EDGE_STARTED,
        EDGE_FINISHED_COMMON,
        EDGE_FINISHED_BRIDGE;

        public static Action back(Action action)
        {
            switch (action)
            {
                case EDGE_STARTED : {
                    return BASE;
                }
                case EDGE_FINISHED_COMMON :
                case EDGE_FINISHED_BRIDGE : {
                    return EDGE_STARTED;
                }
                case NONE : {
                    return NONE;
                }
                default : throw new IllegalStateException("Unexpected value: " + action);
            }
        }

    }
}
