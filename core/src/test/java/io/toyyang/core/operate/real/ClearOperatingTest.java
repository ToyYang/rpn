package io.toyyang.core.operate.real;

import io.toyyang.core.node.Node;
import io.toyyang.core.node.Operator;
import io.toyyang.core.node.operated.OperatedNode;
import io.toyyang.core.operate.OperatedResult;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ClearOperatingTest {
    private ClearOperating clearOperating = new ClearOperating();

    @Test
    public void getRequiredNumberCount() {
        assertEquals(0, clearOperating.getRequiredNumberCount());
    }

    @Test
    public void execute() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3")
        ));

        OperatedResult rs = clearOperating.execute(list);
        assertEquals(1, rs.getNodeLinkedList().size());
        Node node = rs.getNodeLinkedList().get(0);
        assertTrue(node instanceof OperatedNode);

        OperatedNode opNode = (OperatedNode)node;
        assertEquals(Operator.CLEAR, opNode.getOperator());
        assertEquals("2", opNode.getOrigin().getValue().getRawValue());
        assertEquals("3", opNode.getOrigin().getNext().getValue().getRawValue());
    }
}