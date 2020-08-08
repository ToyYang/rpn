package io.toyyang.core.operate.real.undo;

import io.toyyang.core.node.Node;

import java.util.LinkedList;

public interface OperatingReverter {
    /**
     * Check and revert a operation.
     * @param nodeLinkedList the existing stack
     * @param tempUndoNodes the temporary undo nodes
     * @return whether need to terminate the undoing work.
     */
    boolean revert(LinkedList<Node> nodeLinkedList, LinkedList<Node> tempUndoNodes);
}
