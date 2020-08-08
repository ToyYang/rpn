package io.toyyang.core.node.operated;

import io.toyyang.core.node.Node;

public class OriginNode extends Node {
    private Node value;
    private OriginNode next;

    private OriginNode(boolean isNumber, Kind kind) {
        super(isNumber, kind);
    }

    public OriginNode(Node value) {
        this(value.isNumber(), value.getKind());
        this.value = value;
    }

    public Node getValue() {
        return value;
    }

    public OriginNode getNext() {
        return next;
    }

    public void setNext(OriginNode next) {
        this.next = next;
    }
}
