package io.toyyang.core.operate.real;

import io.toyyang.core.node.Node;
import io.toyyang.core.operate.ErrorMsg;
import io.toyyang.core.operate.OperatedResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class SqrtOperatingTest {

    private SqrtOperating sqrtOperating = new SqrtOperating();

    @Test
    public void execute_nothing_insufficientParameters() {
        LinkedList<Node> list = new LinkedList<>(Collections.singletonList(new Node("nothing")));

        OperatedResult rs = sqrtOperating.perform(list);
        Assert.assertEquals(ErrorMsg.INSUFFICIENT_PARAMETERS, rs.getErrorMsg());
        Assert.assertEquals(1, rs.getNodeLinkedList().size());
        Assert.assertEquals("nothing", rs.getNodeLinkedList().pollLast().getRawValue());
    }

    @Test
    public void execute_negative2_error() {
        LinkedList<Node> list = new LinkedList<>(Collections.singletonList(new Node("-2")));

        OperatedResult rs = sqrtOperating.perform(list);
        Assert.assertEquals(ErrorMsg.SQUARE_ROOT_FOR_NEGATIVE_NUMBER, rs.getErrorMsg());
        Assert.assertEquals(1, rs.getNodeLinkedList().size());
        Assert.assertEquals("-2", rs.getNodeLinkedList().pollLast().getRawValue());
    }

    @Test
    public void execute_2_1dot4142135624() {
        LinkedList<Node> list = new LinkedList<>(Collections.singletonList(new Node("2")));

        OperatedResult rs = sqrtOperating.perform(list);
        Assert.assertNull(rs.getErrorMsg());
        Assert.assertEquals(1, rs.getNodeLinkedList().size());
        Assert.assertEquals("1.4142135623", rs.getNodeLinkedList().pollLast().getRawValue());
    }

    @Test
    public void execute_2_3_1dot4142135624() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(new Node("2"), new Node("3")));

        OperatedResult rs = sqrtOperating.perform(list);
        Assert.assertNull(rs.getErrorMsg());
        Assert.assertEquals(2, rs.getNodeLinkedList().size());
        Assert.assertEquals("1.7320508075", rs.getNodeLinkedList().pollLast().getRawValue());
    }
}