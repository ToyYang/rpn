package io.toyyang.core.operate.real;

import io.toyyang.core.node.Node;
import io.toyyang.core.node.operated.OperatedNode;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class GeneralOperatingHelperTest {
    private GeneralOperatingHelper helper = new GeneralOperatingHelper();

    @Test
    public void doCommonCalc_maxAnd2And3withoutValidator_3() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3")
        ));

        helper.doCommonCalc(list, BigDecimal::max);
        assertEquals(1, list.size());
        assertEquals("3", list.pollLast().getRawValue());
    }

    @Test
    public void doCommonCalc_maxAnd2And3_3() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3")
        ));

        String msg = helper.doCommonCalc(list, BigDecimal::max, null);
        assertNull(msg);
        assertEquals(1, list.size());

        Node node = list.pollLast();
        assertEquals("3", node.getRawValue());
        assertTrue(node instanceof OperatedNode);
    }

    @Test
    public void doCommonCalc_maxAnd2And3AndNonNumber_3() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3"),
                new Node("non-number")
        ));

        String msg = helper.doCommonCalc(list, BigDecimal::max, null);
        assertNull(msg);
        assertEquals(1, list.size());

        Node node = list.pollLast();
        assertEquals("3", node.getRawValue());
        assertTrue(node instanceof OperatedNode);
    }

    @Test
    public void doCommonCalc_maxAnd2And3withVaidationFailed_error() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3")
        ));

        final String error = "Cannot be less than 100";
        String msg = helper.doCommonCalc(list, BigDecimal::max,
                (node1, node2) -> new BigDecimal(node1.getRawValue()).doubleValue() - 100 < 0 ? error : null);
        assertEquals(error, msg);
        assertEquals(2, list.size());
        assertEquals("3", list.pollLast().getRawValue());
        assertEquals("2", list.pollLast().getRawValue());
    }

    @Test
    public void doCommonCalc_maxAnd2And3withVaidationPassed_3() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3")
        ));

        final String error = "Cannot be less than 100";
        String msg = helper.doCommonCalc(list, BigDecimal::max,
                (node1, node2) -> new BigDecimal(node1.getRawValue()).doubleValue() - 0 < 0 ? error : null);
        assertNull(msg);
        assertEquals(1, list.size());
        assertEquals("3", list.pollLast().getRawValue());
    }

    @Test
    public void dragNumberNode_2And3_3() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3")
        ));

        Node node = helper.dragNumberNode(list);
        assertEquals("3", node.getRawValue());
    }

    @Test
    public void dragNumberNode_2And3AndNonNumber_3() {
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("2"),
                new Node("3"),
                new Node("non-number")
        ));

        Node node = helper.dragNumberNode(list);
        assertEquals("3", node.getRawValue());
    }
}