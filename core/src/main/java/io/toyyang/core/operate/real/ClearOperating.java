package io.toyyang.core.operate.real;

import io.toyyang.core.node.Node;
import io.toyyang.core.node.Operator;
import io.toyyang.core.node.operated.OperatedNode;
import io.toyyang.core.node.operated.OriginNode;
import io.toyyang.core.operate.AbstractedOperating;
import io.toyyang.core.operate.OperatedResult;

import java.util.LinkedList;


public class ClearOperating extends AbstractedOperating {
    @Override
    protected int getRequiredNumberCount() {
        return 0;
    }

    @Override
    protected OperatedResult execute(LinkedList<Node> nodeLinkedList) {
        if (nodeLinkedList.isEmpty()) {
            return new OperatedResult(nodeLinkedList, null);
        }

        OperatedNode operatedNode = new OperatedNode(false);
        operatedNode.setOperator(Operator.CLEAR);
        Node node;
        OriginNode originTail = null;
        while ((node = nodeLinkedList.pollFirst()) != null) {
            OriginNode originNode = new OriginNode(node);
            if (originTail == null) {
                operatedNode.setOrigin(originNode);
                originTail = operatedNode.getOrigin();
            } else {
                originTail.setNext(originNode);
                originTail = originTail.getNext();
            }
        }

        nodeLinkedList.add(operatedNode);

        return new OperatedResult(nodeLinkedList, null);
    }
}
