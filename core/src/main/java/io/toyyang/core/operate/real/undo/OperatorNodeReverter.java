package io.toyyang.core.operate.real.undo;

import io.toyyang.core.node.Node;

import java.util.LinkedList;

public class OperatorNodeReverter implements OperatingReverter {
    @Override
    public boolean revert(LinkedList<Node> nodeLinkedList, LinkedList<Node> tempUndoNodes) {
        Node node;
        while (((node = nodeLinkedList.pollLast()) != null) && !node.isNumber()) {
            tempUndoNodes.add(0, node);
        }

        if (node != null) {
            nodeLinkedList.add(node);
        }

        return node == null;
    }
}
