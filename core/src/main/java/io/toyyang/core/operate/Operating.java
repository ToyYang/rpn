package io.toyyang.core.operate;

import io.toyyang.core.node.Node;

import java.util.LinkedList;

public interface Operating {
    OperatedResult perform(LinkedList<Node> calculatedNodes);
}
