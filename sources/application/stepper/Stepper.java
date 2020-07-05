package application.stepper;

import java.util.ArrayList;

public class Stepper {
    private final ArrayList<Step> steps = new ArrayList<>();
    private int cursor = -1;

    public void newStep(NodeAction nodeAction, EdgeAction edgeAction)
    {
        steps.add(new Step(nodeAction, edgeAction));
    }

    public Step nextStep()
    {
        if (cursor + 1 < steps.size())
        {
            cursor += 1;

            return steps.get(cursor);
        }
        else
        {
            return null;
        }
    }

    public Step prevStep()
    {
        if (cursor > 0)
        {
            cursor -= 1;
            return steps.get(cursor + 1).back();
        }
        else
        {
            return null;
        }
    }

    public int getStepCount()
    {
        return steps.size();
    }

}
