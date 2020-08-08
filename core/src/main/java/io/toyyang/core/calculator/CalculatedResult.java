package io.toyyang.core.calculator;

import io.toyyang.core.GlobalConstants;
import io.toyyang.core.node.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CalculatedResult {
    private String errorMsg;
    private List<Node> calculatedNodes;

    public CalculatedResult(LinkedList<Node> calculatedNodes, String errorMsg) {
        this.calculatedNodes = calculatedNodes;
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public List<Node> getCalculatedNodes() {
        return calculatedNodes;
    }

    public String getCalculatedInformation() {
        return String.format(GlobalConstants.CALCULATED_INFO_TEMPLATE,
                getCalculatedNodes()
                        .stream()
                        .filter(Node::isNumber)
                        .map(Node::getRawValue)
                        .collect(Collectors.joining(" ")));
    }

    public String getWholeMessage() {
        return Optional.ofNullable(getErrorMsg())
                .map(s -> s + GlobalConstants.LINE_SEPARATOR)
                .orElse("") + getCalculatedInformation();
    }
}
