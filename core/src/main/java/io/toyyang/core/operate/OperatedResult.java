package io.toyyang.core.operate;

import io.toyyang.core.node.Node;

import java.util.LinkedList;

public class OperatedResult {
    private LinkedList<Node> nodeLinkedList;
    private String errorMsg;

    public OperatedResult(LinkedList<Node> nodeLinkedList, String errorMsg) {
        this.nodeLinkedList = nodeLinkedList;
        this.errorMsg = errorMsg;
    }

    public LinkedList<Node> getNodeLinkedList() {
        return nodeLinkedList;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
