package io.toyyang.core.operate.real;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.toyyang.core.GlobalConstants;
import io.toyyang.core.node.Node;
import io.toyyang.core.node.Operator;
import io.toyyang.core.node.operated.OperatedNode;
import io.toyyang.core.node.operated.OriginNode;
import io.toyyang.core.operate.AbstractedUnaryOperating;
import io.toyyang.core.operate.ErrorMsg;
import io.toyyang.core.operate.OperatedResult;
import io.toyyang.core.operate.Operating;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Objects;

import static io.toyyang.core.GlobalConstants.NUMBER_FORMAT;

public class SqrtOperating extends AbstractedUnaryOperating implements Operating {

    @Override
    protected OperatedResult execute(LinkedList<Node> nodeLinkedList) {
        Node node = getOperatingHelper().dragNumberNode(nodeLinkedList);
        BigDecimal num = new BigDecimal(Objects.requireNonNull(node).getRawValue());
        if (num.compareTo(BigDecimal.ZERO) < 0) {
            nodeLinkedList.add(node);
            return new OperatedResult(nodeLinkedList, ErrorMsg.SQUARE_ROOT_FOR_NEGATIVE_NUMBER);
        }

        nodeLinkedList.add(OperatedNode.Builder.of()
                .withRawValue(NUMBER_FORMAT.format(BigDecimalMath.sqrt(num, GlobalConstants.MATH_CONTEXT_15)))
                .withOperator(Operator.SQRT)
                .withIsNumber(true)
                .withKind(Node.Kind.ARGUMENT)
                .withOrigin(new OriginNode(node))
                .build());

        return new OperatedResult(nodeLinkedList, null);
    }
}
