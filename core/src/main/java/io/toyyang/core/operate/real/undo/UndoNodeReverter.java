package io.toyyang.core.operate.real.undo;

import io.toyyang.core.node.Node;
import io.toyyang.core.node.Operator;
import io.toyyang.core.node.operated.OperatedNode;

import java.util.LinkedList;

public class UndoNodeReverter implements OperatingReverter {
    @Override
    public boolean revert(LinkedList<Node> nodeLinkedList, LinkedList<Node> tempUndoNodes) {
        Node node;
        OperatedNode opNode;
        //skip all existing UNDO nodes
        while (((node = nodeLinkedList.pollLast()) != null) && (node instanceof OperatedNode)
                && (opNode = (OperatedNode)node).getOperator() == Operator.UNDO) {
            tempUndoNodes.add(0, opNode);
        }

        if (node != null) {
            nodeLinkedList.add(node);
        }

        return node == null;
    }
}
