package io.toyyang.core.operate.real;

import io.toyyang.core.GlobalConstants;
import io.toyyang.core.node.Node;
import io.toyyang.core.operate.AbstractedBinaryOperating;
import io.toyyang.core.operate.ErrorMsg;
import io.toyyang.core.operate.OperatedResult;
import io.toyyang.core.operate.Operating;

import java.math.BigDecimal;
import java.util.LinkedList;

public class DivOperating extends AbstractedBinaryOperating implements Operating {
    @Override
    protected OperatedResult execute(LinkedList<Node> nodeLinkedList) {
        String errorMsg = getOperatingHelper().doCommonCalc(nodeLinkedList,
                (b1, b2) -> b1.divide(b2, GlobalConstants.MATH_CONTEXT_15),
                (node1, node2) -> new BigDecimal(node2.getRawValue()).compareTo(BigDecimal.ZERO) == 0 ?
                        ErrorMsg.DIVIDER_IS_0 : null);

        return new OperatedResult(nodeLinkedList, errorMsg);
    }
}