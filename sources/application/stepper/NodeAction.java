package application.stepper;

import application.algorithm.Graph;

public class NodeAction
{
    public Action getAction() {
        return action;
    }

    public Graph.Node getNode() {
        return node;
    }

    public int getTin() {
        return tin;
    }

    public int getFup() {
        return fup;
    }

    private final Action action;
    private final Graph.Node node;
    private final int tin;
    private final int fup;
    private final int oldTin;
    private final int oldFup;

    public NodeAction(Action action, Graph.Node node, int tin, int fup, int oldTin, int oldFup) {
        this.action = action;
        this.node = node;
        this.tin = tin;
        this.fup = fup;
        this.oldTin = oldTin;
        this.oldFup = oldFup;
    }

    public NodeAction back()
    {
        return new NodeAction(Action.back(action), node, oldTin, oldFup, tin, fup);
    }

    public enum Action
    {
        NONE,
        BASE,
        TIME_UPDATED,
        NODE_STARTED,
        NODE_FINISHED;

        public static Action back(Action action)
        {
            switch (action)
            {
                case NODE_STARTED -> {
                    return BASE;
                }
                case NODE_FINISHED -> {
                    return NODE_STARTED;
                }
                case TIME_UPDATED -> {
                    return TIME_UPDATED;
                }
                case NONE -> {
                    return NONE;
                }
                default -> throw new IllegalStateException("Unexpected value: " + action);
            }
        }
    }
}
