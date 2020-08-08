package io.toyyang.core.operate;

import io.toyyang.core.node.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class AbstractedBinaryOperatingTest {

    @Test
    public void getRequiredNumberCount_nothing_2() {
        AbstractedOperating op = new AbstractedBinaryOperating(){
            @Override
            public OperatedResult execute(LinkedList<Node> nodeLinkedList) {
                return null;
            }
        };

        Assert.assertEquals(op.getRequiredNumberCount(), 2);
    }
}