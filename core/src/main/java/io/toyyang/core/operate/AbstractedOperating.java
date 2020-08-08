package io.toyyang.core.operate;

import io.toyyang.core.node.Node;
import io.toyyang.core.operate.real.GeneralOperatingHelper;

import java.util.LinkedList;

public abstract class AbstractedOperating implements Operating {
    private GeneralOperatingHelper operatingHelper = new GeneralOperatingHelper();

    public OperatedResult perform(LinkedList<Node> nodeLinkedList) {
        if (getArgumentCount(nodeLinkedList) < getRequiredNumberCount()) {
            return new OperatedResult(nodeLinkedList, ErrorMsg.INSUFFICIENT_PARAMETERS);
        }

        return execute(nodeLinkedList);
    }

    protected long getArgumentCount(LinkedList<Node> nodeLinkedList) {
        return nodeLinkedList.stream()
                .filter(Node::isNumber)
                .count();
    }

    protected abstract int getRequiredNumberCount();

    protected abstract OperatedResult execute(LinkedList<Node> nodeLinkedList);

    public GeneralOperatingHelper getOperatingHelper() {
        return operatingHelper;
    }

    public void setOperatingHelper(GeneralOperatingHelper operatingHelper) {
        this.operatingHelper = operatingHelper;
    }
}
