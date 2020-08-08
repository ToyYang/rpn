package io.toyyang.core.operate.real.undo;

import io.toyyang.core.node.Node;
import io.toyyang.core.node.Operator;
import io.toyyang.core.node.operated.OperatedNode;
import io.toyyang.core.node.operated.OriginNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class CommonCalcNodeReverterTest {

    private CommonCalcNodeReverter reverter = new CommonCalcNodeReverter();

    @Test
    public void revert_5ofAdding_rollbackTo2And3() {
        OriginNode originNode = new OriginNode(new Node("2"));
        originNode.setNext(new OriginNode(new Node("3")));
        LinkedList<Node> list = new LinkedList<>(Arrays.asList(
                new Node("1"),
                OperatedNode.Builder.of()
                        .withOperator(Operator.PLUS)
                        .withIsNumber(true)
                        .withOrigin(originNode)
                        .withKind(Node.Kind.ARGUMENT)
                        .withRawValue("5")
                        .build()
        ));

        LinkedList<Node> tempNodeList = new LinkedList<>();
        reverter.revert(list, tempNodeList);
        assertEquals(4, list.size());
        assertEquals(3, tempNodeList.size());

        assertEquals("1", list.pollFirst().getRawValue());
        assertEquals("2", list.pollFirst().getRawValue());
        assertEquals("3", list.pollFirst().getRawValue());
        assertEquals(Operator.PLUS, ((OperatedNode)list.pollFirst()).getOperator());
    }
}