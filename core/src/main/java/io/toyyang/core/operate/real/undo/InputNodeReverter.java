package io.toyyang.core.operate.real.undo;

import io.toyyang.core.node.Node;
import io.toyyang.core.node.Operator;
import io.toyyang.core.node.operated.OperatedNode;
import io.toyyang.core.node.operated.OriginNode;

import java.util.LinkedList;

public class InputNodeReverter implements OperatingReverter {
    @Override
    public boolean revert(LinkedList<Node> nodeLinkedList, LinkedList<Node> tempUndoNodes) {
        Node node = nodeLinkedList.pollLast();

        if (node != null) {
            OperatedNode rolledOutNode = new OperatedNode(false);
            rolledOutNode.setOrigin(new OriginNode(node));
            rolledOutNode.setOperator(Operator.UNDO);
            tempUndoNodes.add(rolledOutNode);
            nodeLinkedList.addAll(tempUndoNodes);
        }

        return true;
    }
}
