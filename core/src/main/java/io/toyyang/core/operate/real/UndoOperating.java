package io.toyyang.core.operate.real;

import io.toyyang.core.node.Node;
import io.toyyang.core.operate.OperatedResult;
import io.toyyang.core.operate.Operating;
import io.toyyang.core.operate.real.undo.*;

import java.util.LinkedList;

public class UndoOperating implements Operating {

    OperatingReverter[] reverters;

    public UndoOperating() {
        reverters = new OperatingReverter[]{
                new UndoNodeReverter(),
                new ClearNodeReverter(),
                new OperatorNodeReverter(),
                new CommonCalcNodeReverter(),
                new InputNodeReverter()
        };
    }

    @Override
    public OperatedResult perform(LinkedList<Node> calculatedNodes) {
        LinkedList<Node> tempUndoNodes = new LinkedList<>();
        for (OperatingReverter reverter : reverters) {
            if (reverter.revert(calculatedNodes, tempUndoNodes)) {
                return new OperatedResult(calculatedNodes, null);
            }
        }

        return new OperatedResult(calculatedNodes, null);
    }
}
