package io.toyyang.core.operate.real;

import io.toyyang.core.node.Node;
import io.toyyang.core.node.Operator;
import io.toyyang.core.node.operated.OperatedNode;
import io.toyyang.core.node.operated.OriginNode;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

import static io.toyyang.core.GlobalConstants.NUMBER_FORMAT;

public class GeneralOperatingHelper {
    public void doCommonCalc(LinkedList<Node> nodeLinkedList, BiFunction<BigDecimal, BigDecimal, BigDecimal> fun) {
        doCommonCalc(nodeLinkedList, fun, null);
    }

    public String doCommonCalc(LinkedList<Node> nodeLinkedList, BiFunction<BigDecimal, BigDecimal, BigDecimal> fun,
                               BiFunction<Node, Node, String> biValidator) {
        Node node2, node1;
        node2 = dragNumberNode(nodeLinkedList);
        node1 = dragNumberNode(nodeLinkedList);

        //do validating
        String errorMsg = Optional.ofNullable(biValidator)
                .map(v -> v.apply(node1, node2))
                .orElse(null);
        if (errorMsg != null) {
            nodeLinkedList.add(node1);
            nodeLinkedList.add(node2);
            return errorMsg;
        }

        //do calculating
        BigDecimal calculatedResult = fun.apply(new BigDecimal(Objects.requireNonNull(node1).getRawValue()),
                new BigDecimal(Objects.requireNonNull(node2).getRawValue()));

        //assemble origin nodes
        OriginNode origin = new OriginNode(node1);
        origin.setNext(new OriginNode(node2));
        //build result
        nodeLinkedList.add(OperatedNode.Builder.of()
                .withRawValue(NUMBER_FORMAT.format(calculatedResult))
                .withOperator(Operator.MINUS)
                .withIsNumber(true)
                .withKind(Node.Kind.ARGUMENT)
                .withOrigin(origin)
                .build());

        return null;
    }

    public Node dragNumberNode(LinkedList<Node> nodeLinkedList) {
        Node node;
        while ((node = nodeLinkedList.pollLast()) != null) {
            if (node.isNumber()) {
                return node;
            }
        }

        return null;
    }
}
