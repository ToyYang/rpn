package io.toyyang.core.operate;

import io.toyyang.core.node.Node;
import org.junit.Test;

import java.util.LinkedList;

import static junit.framework.Assert.assertEquals;


public class AbstractedUnaryOperatingTest {

    @Test
    public void getRequiredNumberCount_nothing_1() {
        AbstractedUnaryOperating operating = new AbstractedUnaryOperating() {
            @Override
            public OperatedResult execute(LinkedList<Node> nodeLinkedList) {
                return null;
            }
        };

        assertEquals(1, operating.getRequiredNumberCount());
    }
}