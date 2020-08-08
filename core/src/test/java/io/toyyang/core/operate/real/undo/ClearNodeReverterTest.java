package io.toyyang.core.operate.real.undo;

import io.toyyang.core.node.Node;
import io.toyyang.core.node.Operator;
import io.toyyang.core.node.operated.OperatedNode;
import io.toyyang.core.node.operated.OriginNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ClearNodeReverterTest {
    private ClearNodeReverter reverter = new ClearNodeReverter();

    @Test
    public void revert_clearedNodeOfAllNumber_rolledBack() {
        OriginNode originNode = new OriginNode(new Node("2"));
        originNode.setNext(new OriginNode(new Node("3")));

        LinkedList<Node> list = new LinkedList<>(Collections.singletonList(
                OperatedNode.Builder.of()
                        .withOperator(Operator.CLEAR)
                        .withIsNumber(true)
                        .withOrigin(originNode)
                        .build()
        ));

        LinkedList<Node> tempNodeList = new LinkedList<>();
        reverter.revert(list, tempNodeList);
        assertEquals(2, list.size());

        assertEquals("2", list.pollFirst().getRawValue());
        assertEquals("3", list.pollFirst().getRawValue());
    }

    @Test
    public void revert_clearedNode_rolledBack() {
        OriginNode originNode = new OriginNode(new Node("2"));
        originNode.setNext(new OriginNode(new Node("3")));
        originNode.getNext().setNext(new OriginNode(new Node("abc")));

        LinkedList<Node> list = new LinkedList<>(Collections.singletonList(
                OperatedNode.Builder.of()
                        .withOperator(Operator.CLEAR)
                        .withIsNumber(true)
                        .withOrigin(originNode)
                        .withKind(Node.Kind.ARGUMENT)
                        .withRawValue("5")
                        .build()
        ));

        LinkedList<Node> tempNodeList = new LinkedList<>();
        reverter.revert(list, tempNodeList);
        assertEquals(3, list.size());

        assertEquals("2", list.pollFirst().getRawValue());
        assertEquals("3", list.pollFirst().getRawValue());
        assertEquals("abc", list.pollFirst().getRawValue());
    }
}