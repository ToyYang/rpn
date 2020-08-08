package io.toyyang.core.operate.real;

import io.toyyang.core.node.Node;
import io.toyyang.core.operate.OperatedResult;
import io.toyyang.core.operate.real.undo.*;
import junit.framework.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Objects;

import static org.junit.Assert.*;

public class UndoOperatingTest {

    private static final String SPECIAL_NODE_FOR_TEST_1 = "special-node-for-test-1";
    private static final String SPECIAL_NODE_FOR_TEST_2 = "special-node-for-test-2";

    @Test
    public void testReverterOrder() {
        OperatingReverter[] reverters = new OperatingReverter[]{
                new UndoNodeReverter(),
                new ClearNodeReverter(),
                new OperatorNodeReverter(),
                new CommonCalcNodeReverter(),
                new InputNodeReverter()
        };

        UndoOperating undoOperating = new UndoOperating();
        for (int i = 0; i < reverters.length; i++) {
            Assert.assertSame(reverters[i].getClass(), undoOperating.reverters[i].getClass());
        }
    }

    @Test
    public void perform_1stRevertReturnTrue_2ndCanNotBeCalled() {
        UndoOperating undoOperating = new UndoOperating();
        undoOperating.reverters = new OperatingReverter[] {
                (nodeLinkedList, tempUndoNodes) -> true,
                (nodeLinkedList, tempUndoNodes) -> {
                    nodeLinkedList.add(new Node(SPECIAL_NODE_FOR_TEST_1));
                    return true;
                }
        };

        OperatedResult rs = undoOperating.perform(new LinkedList<>());
        Assert.assertTrue(rs.getNodeLinkedList().isEmpty());
    }

    @Test
    public void perform_1st2ndRevertReturnFalse_3rdCanBeCalled() {
        UndoOperating undoOperating = new UndoOperating();
        undoOperating.reverters = new OperatingReverter[] {
                (nodeLinkedList, tempUndoNodes) -> false,
                (nodeLinkedList, tempUndoNodes) -> false,
                (nodeLinkedList, tempUndoNodes) -> {
                    nodeLinkedList.add(new Node(SPECIAL_NODE_FOR_TEST_2));
                    return true;
                }
        };

        OperatedResult rs = undoOperating.perform(new LinkedList<>());
        Assert.assertEquals(SPECIAL_NODE_FOR_TEST_2, Objects.requireNonNull(rs.getNodeLinkedList().pollLast()).getRawValue());
    }
}