package io.toyyang.core.operate.real;

import io.toyyang.core.node.Node;
import io.toyyang.core.operate.ErrorMsg;
import io.toyyang.core.operate.OperatedResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class TimesOperatingTest {
    private TimesOperating timesOperating = new TimesOperating();

    @Test
    public void execute_2_insucfficentParameters() {
        LinkedList<Node> list = new LinkedList<>(Collections.singletonList(new Node("2")));

        OperatedResult rs = timesOperating.perform(list);
        Assert.assertEquals(ErrorMsg.INSUFFICIENT_PARAMETERS, rs.getErrorMsg());
        Assert.assertEquals(1, rs.getNodeLinkedList().size());
        Assert.assertEquals("2", rs.getNodeLinkedList().pollLast().getRawValue());
    }

    @Test
    public void execute_2x3_6() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
           new Node("2"),
           new Node("3")
        ));

        OperatedResult rs = timesOperating.perform(list);
        Assert.assertNull(rs.getErrorMsg());
        Assert.assertEquals(1, rs.getNodeLinkedList().size());
        Assert.assertEquals("6", rs.getNodeLinkedList().pollLast().getRawValue());
    }

    @Test
    public void execute_negative5x7_negative35() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("-5"),
                new Node("7")
        ));

        OperatedResult rs = timesOperating.perform(list);
        Assert.assertNull(rs.getErrorMsg());
        Assert.assertEquals(1, rs.getNodeLinkedList().size());
        Assert.assertEquals("-35", rs.getNodeLinkedList().pollLast().getRawValue());
    }
}