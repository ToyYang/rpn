package io.toyyang.core.calculator;

import io.toyyang.core.GlobalConstants;
import io.toyyang.core.node.Node;
import io.toyyang.core.node.Operator;
import io.toyyang.core.operate.ErrorMsg;
import io.toyyang.core.operate.OperatedResult;
import io.toyyang.core.operate.Operating;
import io.toyyang.core.operate.OperatingRegistry;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    /**
     * Calculate on the specified calculated node sequence and new number or operator sequence.<br/>
     * <Strong>Notice:</Strong> if and only if the parameter <Strong><code>calculatedNodes</code></Strong> is an instance of LinkedList,
     * it will be directly modified.
     * @param calculatedNodes nodes sequence of last calculation
     * @param line number or operator sequence inputted by user
     * @return calculated result
     */
    public CalculatedResult calculate(List<Node> calculatedNodes, String line) {
        LinkedList<Node> batch = ensureLinkedList(calculatedNodes);
        Pattern nonBlank = Pattern.compile("[\\S]+");
        Matcher matcher = nonBlank.matcher(line);
        while (matcher.find()) {
            Node node = new Node(matcher.group());
            if (node.isNumber()) {
                batch.add(node);
                continue;
            }

            OperatedResult rs = doCalculating(batch, Operator.getBySymbol(node.getRawValue()));
            if (rs.getErrorMsg() != null) {
                return convertToCalculatedResult(rs, node.getRawValue(), matcher.start() + 1);
            }
        }

        return new CalculatedResult(batch, null);
    }

    private OperatedResult doCalculating(LinkedList<Node> batch, Operator operator) {
        if (operator == null) {
            return new OperatedResult(batch, ErrorMsg.ILLEGAL_OPERATOR);
        }

        Operating operating = OperatingRegistry.of().getOperating(operator);
        if (operating == null) {
            return new OperatedResult(batch, ErrorMsg.UNSUPPORTED_OPERATOR);
        }

        return operating.perform(batch);
    }

    private CalculatedResult convertToCalculatedResult(OperatedResult operatedResult, String operator, int position) {
        return new CalculatedResult(operatedResult.getNodeLinkedList(),
                String.format(GlobalConstants.ERROR_MSG_TEMPLATE, operator, position, operatedResult.getErrorMsg()));
    }

    private LinkedList<Node> ensureLinkedList(List<Node>calculatedNodes) {
        if (calculatedNodes instanceof LinkedList) {
            return (LinkedList<Node>)calculatedNodes;
        }

        return new LinkedList<>(calculatedNodes);
    }
}
