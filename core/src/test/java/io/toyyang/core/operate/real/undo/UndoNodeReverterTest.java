package io.toyyang.core.operate.real.undo;

import io.toyyang.core.node.Node;
import io.toyyang.core.node.Operator;
import io.toyyang.core.node.operated.OperatedNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class UndoNodeReverterTest {

    private UndoNodeReverter undoNodeReverter = new UndoNodeReverter();

    @Test
    public void revert_1and2_noChange() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3")
        ));

        LinkedList<Node> tempNodeList = new LinkedList<>();
        undoNodeReverter.revert(list, tempNodeList);
        assertEquals(2, list.size());
        assertEquals(0, tempNodeList.size());

        assertEquals("2", list.pollFirst().getRawValue());
        assertEquals("3", list.pollFirst().getRawValue());
    }

    @Test
    public void revert_1and2andUndo_undoNodePoped() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3"),
                OperatedNode.Builder.of()
                        .withOperator(Operator.UNDO)
                        .withRawValue("a")
                        .build()
        ));

        LinkedList<Node> tempNodeList = new LinkedList<>();
        undoNodeReverter.revert(list, tempNodeList);
        assertEquals(2, list.size());
        assertEquals(1, tempNodeList.size());

        assertEquals("2", list.pollFirst().getRawValue());
        assertEquals("3", list.pollFirst().getRawValue());

        assertEquals("a", tempNodeList.pollFirst().getRawValue());
    }

    @Test
    public void revert_1and2andUndoUndo_undoUndoPoped() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3"),
                OperatedNode.Builder.of()
                        .withOperator(Operator.UNDO)
                        .withRawValue("a")
                        .build(),
                OperatedNode.Builder.of()
                        .withOperator(Operator.UNDO)
                        .withRawValue("b")
                        .build()
        ));

        LinkedList<Node> tempNodeList = new LinkedList<>();
        undoNodeReverter.revert(list, tempNodeList);
        assertEquals(2, list.size());
        assertEquals(2, tempNodeList.size());

        assertEquals("2", list.pollFirst().getRawValue());
        assertEquals("3", list.pollFirst().getRawValue());

        assertEquals("a", tempNodeList.pollFirst().getRawValue());
        assertEquals("b", tempNodeList.pollFirst().getRawValue());
    }
}