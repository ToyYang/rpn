package io.toyyang.core.operate.real.undo;

import io.toyyang.core.node.Node;
import io.toyyang.core.node.Operator;
import io.toyyang.core.node.operated.OperatedNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class InputNodeReverterTest {

    private InputNodeReverter reverter = new InputNodeReverter();

    @Test
    public void revert_NumberNode_numberNodePoped() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3")
        ));

        LinkedList<Node> tempNodeList = new LinkedList<>();
        reverter.revert(list, tempNodeList);
        assertEquals(2, list.size());
        assertEquals(1, tempNodeList.size());

        assertEquals("2", list.pollFirst().getRawValue());
        Node node = tempNodeList.pollFirst();
        assertTrue(node instanceof OperatedNode);
        OperatedNode opNode = (OperatedNode)node;
        assertEquals(Operator.UNDO, opNode.getOperator());
        assertEquals("3", opNode.getOrigin().getValue().getRawValue());
        assertNull(opNode.getOrigin().getNext());
    }
}