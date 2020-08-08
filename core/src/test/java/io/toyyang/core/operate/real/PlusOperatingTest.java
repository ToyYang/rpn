package io.toyyang.core.operate.real;

import io.toyyang.core.node.Node;
import io.toyyang.core.operate.ErrorMsg;
import io.toyyang.core.operate.OperatedResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class PlusOperatingTest {
    private PlusOperating plusOperating = new PlusOperating();

    @Test
    public void execute_2_insufficient() {
        LinkedList<Node> list = new LinkedList<>(Collections.singletonList(new Node("2")));

        OperatedResult rs = plusOperating.perform(list);
        Assert.assertEquals(ErrorMsg.INSUFFICIENT_PARAMETERS, rs.getErrorMsg());
        Assert.assertEquals(1, rs.getNodeLinkedList().size());
        Assert.assertEquals("2", rs.getNodeLinkedList().pollLast().getRawValue());
    }

    @Test
    public void execute_2andNonNumber_insufficient() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("non-number")
        ));

        OperatedResult rs = plusOperating.perform(list);
        Assert.assertEquals(ErrorMsg.INSUFFICIENT_PARAMETERS, rs.getErrorMsg());
        Assert.assertEquals(2, rs.getNodeLinkedList().size());
        Assert.assertEquals("non-number", rs.getNodeLinkedList().pollLast().getRawValue());
    }

    @Test
    public void execute_2add3_5() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3")
        ));

        OperatedResult rs = plusOperating.perform(list);
        Assert.assertNull(rs.getErrorMsg());
        Assert.assertEquals(1, rs.getNodeLinkedList().size());
        Assert.assertEquals("5", rs.getNodeLinkedList().pollLast().getRawValue());
    }
}