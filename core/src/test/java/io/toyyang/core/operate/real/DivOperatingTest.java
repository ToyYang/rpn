package io.toyyang.core.operate.real;

import io.toyyang.core.node.Node;
import io.toyyang.core.operate.ErrorMsg;
import io.toyyang.core.operate.OperatedResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class DivOperatingTest {
    private DivOperating divOperating = new DivOperating();

    @Test
    public void execute_2_insucfficentParameters() {
        LinkedList<Node> list = new LinkedList<>(Collections.singletonList(new Node("2")));

        OperatedResult rs = divOperating.perform(list);
        Assert.assertEquals(ErrorMsg.INSUFFICIENT_PARAMETERS, rs.getErrorMsg());
        Assert.assertEquals(1, rs.getNodeLinkedList().size());
        Assert.assertEquals("2", rs.getNodeLinkedList().pollLast().getRawValue());
    }

    @Test
    public void execute_2div3_6() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3")
        ));

        OperatedResult rs = divOperating.perform(list);
        Assert.assertNull(rs.getErrorMsg());
        Assert.assertEquals(1, rs.getNodeLinkedList().size());
        Assert.assertEquals("0.6666666666", rs.getNodeLinkedList().pollLast().getRawValue());
    }

    @Test
    public void execute_2div0_error() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("0")
        ));

        OperatedResult rs = divOperating.perform(list);
        Assert.assertEquals(ErrorMsg.DIVIDER_IS_0, rs.getErrorMsg());
        Assert.assertEquals(2, rs.getNodeLinkedList().size());
        Assert.assertEquals("2", rs.getNodeLinkedList().pollFirst().getRawValue());
        Assert.assertEquals("0", rs.getNodeLinkedList().pollFirst().getRawValue());
    }

    @Test
    public void execute_negative5div7_negative35() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("-5"),
                new Node("7")
        ));

        OperatedResult rs = divOperating.perform(list);
        Assert.assertNull(rs.getErrorMsg());
        Assert.assertEquals(1, rs.getNodeLinkedList().size());
        Assert.assertEquals("-0.7142857142", rs.getNodeLinkedList().pollLast().getRawValue());
    }
}