package io.toyyang.core.operate.real.undo;

import io.toyyang.core.node.Node;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class OperatorNodeReverterTest {
    private OperatorNodeReverter operatorNodeReverter = new OperatorNodeReverter();
    @Test
    public void revert_numberNodes_noChange() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3")
        ));

        LinkedList<Node> tempNodeList = new LinkedList<>();
        operatorNodeReverter.revert(list, tempNodeList);
        assertEquals(2, list.size());
        assertEquals(0, tempNodeList.size());

        assertEquals("2", list.pollFirst().getRawValue());
        assertEquals("3", list.pollFirst().getRawValue());
    }

    @Test
    public void revert_numberNodesAndOperatorNode_operatorPoped() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3"),
                new Node("*")
        ));

        LinkedList<Node> tempNodeList = new LinkedList<>();
        operatorNodeReverter.revert(list, tempNodeList);
        assertEquals(2, list.size());
        assertEquals(1, tempNodeList.size());

        assertEquals("2", list.pollFirst().getRawValue());
        assertEquals("3", list.pollFirst().getRawValue());
        assertEquals("*", tempNodeList.pollFirst().getRawValue());
    }

    @Test
    public void revert_numberNodesAnd2OperatorNodes_2operatorsPoped() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3"),
                new Node("*"),
                new Node("*")
        ));

        LinkedList<Node> tempNodeList = new LinkedList<>();
        operatorNodeReverter.revert(list, tempNodeList);
        assertEquals(2, list.size());
        assertEquals(2, tempNodeList.size());

        assertEquals("2", list.pollFirst().getRawValue());
        assertEquals("3", list.pollFirst().getRawValue());
        assertEquals("*", tempNodeList.pollFirst().getRawValue());
        assertEquals("*", tempNodeList.pollFirst().getRawValue());
    }
}