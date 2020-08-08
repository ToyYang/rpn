package io.toyyang.core.operate.real.undo;

import io.toyyang.core.node.Node;
import io.toyyang.core.node.Operator;
import io.toyyang.core.node.operated.OperatedNode;
import io.toyyang.core.node.operated.OriginNode;

import java.util.LinkedList;

public class ClearNodeReverter implements OperatingReverter {
    @Override
    public boolean revert(LinkedList<Node> nodeLinkedList, LinkedList<Node> tempUndoNodes) {
        Node node;
        OperatedNode opNode;
        if ((node = nodeLinkedList.pollLast()) != null &&
                (node instanceof OperatedNode) && (opNode = (OperatedNode)node).getOperator() == Operator.CLEAR) {
            OriginNode origin = opNode.getOrigin();
            //restore all from the origin chain
            while (origin != null) {
                Node value = origin.getValue();
                nodeLinkedList.add(value);
                origin = origin.getNext();
            }

            nodeLinkedList.addAll(tempUndoNodes);
            return true;
        }

        if (node != null) {
            nodeLinkedList.add(node);
        }
        return node == null;
    }
}
