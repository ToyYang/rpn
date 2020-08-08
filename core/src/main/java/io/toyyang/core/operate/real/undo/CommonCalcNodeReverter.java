package io.toyyang.core.operate.real.undo;

import io.toyyang.core.node.Node;
import io.toyyang.core.node.Operator;
import io.toyyang.core.node.operated.OperatedNode;
import io.toyyang.core.node.operated.OriginNode;

import java.util.LinkedList;

public class CommonCalcNodeReverter implements OperatingReverter {
    @Override
    public boolean revert(LinkedList<Node> nodeLinkedList, LinkedList<Node> tempUndoNodes) {
        Node node = nodeLinkedList.pollLast();
        OperatedNode opNode;
        if (node instanceof OperatedNode) {
            opNode = (OperatedNode)node;
            OriginNode origin = opNode.getOrigin();
            Operator lastOperator = null;
            //restore all from the origin chain
            while (origin != null) {
                Node value = origin.getValue();
                tempUndoNodes.add(value);
                origin = origin.getNext();
                if (lastOperator == null) {
                    lastOperator = opNode.getOperator();
                }
            }

            //restore operator for the future REDO operation
            if (lastOperator != null) {
                OperatedNode rolledOutNode = new OperatedNode(false);
                rolledOutNode.setOrigin(new OriginNode(opNode));
                rolledOutNode.setOperator(lastOperator);
                tempUndoNodes.add(rolledOutNode);
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
