package application.stepper;

import application.algorithm.Graph;

public class Step
{
    public NodeAction getNodeAction() {
        return nodeAction;
    }

    public EdgeAction getEdgeAction() {
        return edgeAction;
    }

    private final NodeAction nodeAction;
    private final EdgeAction edgeAction;

    public Step(NodeAction nodeAction, EdgeAction edgeAction) {
        this.nodeAction = nodeAction;
        this.edgeAction = edgeAction;
    }

    public Step back()
    {
        NodeAction newNodeAction = null;
        if(nodeAction != null)
        {
            newNodeAction = nodeAction.back();
        }

        EdgeAction newEdgeAction = null;
        if(edgeAction != null)
        {
            newEdgeAction = edgeAction.back();
        }

        return new Step(newNodeAction, newEdgeAction);
    }
}
