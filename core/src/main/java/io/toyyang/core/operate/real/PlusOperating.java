package io.toyyang.core.operate.real;

import io.toyyang.core.node.Node;
import io.toyyang.core.operate.AbstractedBinaryOperating;
import io.toyyang.core.operate.OperatedResult;
import io.toyyang.core.operate.Operating;

import java.math.BigDecimal;
import java.util.LinkedList;


public class PlusOperating extends AbstractedBinaryOperating implements Operating {
    @Override
    protected OperatedResult execute(LinkedList<Node> nodeLinkedList) {
        getOperatingHelper().doCommonCalc(nodeLinkedList, BigDecimal::add);
        return new OperatedResult(nodeLinkedList, null);
    }
}
