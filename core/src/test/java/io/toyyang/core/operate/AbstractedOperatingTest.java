package io.toyyang.core.operate;

import io.toyyang.core.node.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractedOperatingTest {

    private List<Node> nodeList = Arrays.asList(
            new Node(true, Node.Kind.ARGUMENT),
            new Node(true, Node.Kind.ARGUMENT),
            new Node("2"),
            new Node("3"),
            new Node(true, Node.Kind.ARGUMENT)
    );

    private static final String FAKE_ERROR_FOR_TESTING = "fake error for testing.";
    private static AbstractedOperating operating = new AbstractedOperating() {
        @Override
        protected int getRequiredNumberCount() {
            return 5;
        }

        @Override
        protected OperatedResult execute(LinkedList<Node> nodeLinkedList) {
            nodeLinkedList.add(new Node("test-node"));
            return new OperatedResult(nodeLinkedList, FAKE_ERROR_FOR_TESTING);
        }
    };

    @Test
    public void perform_emptyNodeList_errorInsufficient() {
        LinkedList<Node> list = new LinkedList<>();
        OperatedResult rs = operating.perform(list);
        Assert.assertEquals(ErrorMsg.INSUFFICIENT_PARAMETERS, rs.getErrorMsg());
    }

    @Test
    public void perform_4NodesList_errorInsufficient() {
        LinkedList<Node> list = nodeList.stream()
                .limit(4)
                .collect(Collectors.toCollection(LinkedList::new));
        OperatedResult rs = operating.perform(list);
        Assert.assertEquals(ErrorMsg.INSUFFICIENT_PARAMETERS, rs.getErrorMsg());
    }

    @Test
    public void perform_5NodesList_errorInsufficient() {
        LinkedList<Node> list = new LinkedList<>(nodeList);
        OperatedResult rs = operating.perform(list);
        Assert.assertEquals(FAKE_ERROR_FOR_TESTING, rs.getErrorMsg());
    }

    @Test
    public void getArgumentCount_5NumberNodesAnd2NonNumberNodes_5() {
        LinkedList<Node> list = new LinkedList<>(nodeList);
        Assert.assertEquals(5, operating.getArgumentCount(list));
    }
}