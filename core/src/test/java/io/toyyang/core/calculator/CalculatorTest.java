package io.toyyang.core.calculator;

import io.toyyang.core.GlobalConstants;
import io.toyyang.core.node.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Test cases listed according to the requirement documentation.
 */
public class CalculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    public void calculate_useCase1() {
        List<Node> base = new LinkedList<>();
        Assert.assertEquals("stack: 5 2", calculator.calculate(base, "5 2").getWholeMessage());
    }

    @Test
    public void calculate_useCase2() {
        List<Node> base = new LinkedList<>();
        Assert.assertEquals("stack: 1.4142135623", calculator.calculate(base, "2 sqrt").getWholeMessage());
        Assert.assertEquals("stack: 3", calculator.calculate(base, "clear 9 sqrt").getWholeMessage());
    }

    @Test
    public void calculate_useCase3() {
        List<Node> base = new LinkedList<>();
        Assert.assertEquals("stack: 3", calculator.calculate(base, "5 2 -").getWholeMessage());
        Assert.assertEquals("stack: 0", calculator.calculate(base, "3 -").getWholeMessage());
        Assert.assertEquals("stack: ", calculator.calculate(base, "clear").getWholeMessage());
    }

    @Test
    public void calculate_useCase4() {
        List<Node> base = new LinkedList<>();
        Assert.assertEquals("stack: 5 4 3 2", calculator.calculate(base, "5 4 3 2").getWholeMessage());
        Assert.assertEquals("stack: 20", calculator.calculate(base, "undo undo *").getWholeMessage());
        Assert.assertEquals("stack: 100", calculator.calculate(base, "5 *").getWholeMessage());
        Assert.assertEquals("stack: 20 5", calculator.calculate(base, "undo").getWholeMessage());
    }

    @Test
    public void calculate_useCase5() {
        List<Node> base = new LinkedList<>();
        Assert.assertEquals("stack: 7 6", calculator.calculate(base, "7 12 2 /").getWholeMessage());
        Assert.assertEquals("stack: 42", calculator.calculate(base, "*").getWholeMessage());
        Assert.assertEquals("stack: 10.5", calculator.calculate(base, "4 /").getWholeMessage());
    }

    @Test
    public void calculate_useCase6() {
        List<Node> base = new LinkedList<>();
        Assert.assertEquals("stack: 1 2 3 4 5", calculator.calculate(base, "1 2 3 4 5").getWholeMessage());
        Assert.assertEquals("stack: 1 2 3 20", calculator.calculate(base, "*").getWholeMessage());
        Assert.assertEquals("stack: -1", calculator.calculate(base, "clear 3 4 -").getWholeMessage());
    }

    @Test
    public void calculate_useCase7() {
        List<Node> base = new LinkedList<>();
        Assert.assertEquals("stack: 1 2 3 4 5", calculator.calculate(base, "1 2 3 4 5").getWholeMessage());
        Assert.assertEquals("stack: 120", calculator.calculate(base, "* * * *").getWholeMessage());
    }

    @Test
    public void calculate_useCase8() {
        List<Node> base = new LinkedList<>();
        Assert.assertEquals("operator * (position: 15): insufficient parameters" + GlobalConstants.LINE_SEPARATOR + "stack: 11",
                calculator.calculate(base, "1 2 3 * 5 + * * 6 5").getWholeMessage());
    }

    @Test
    public void calculate_useCase4WithArrayList() {
        List<Node> base = new ArrayList<>();
        CalculatedResult rs = calculator.calculate(base, "5 4 3 2");
        Assert.assertEquals("stack: 5 4 3 2", rs.getWholeMessage());

        rs = calculator.calculate(rs.getCalculatedNodes(), "undo undo *");
        Assert.assertEquals("stack: 20", rs.getWholeMessage());

        rs = calculator.calculate(rs.getCalculatedNodes(),"5 *");
        Assert.assertEquals("stack: 100", rs.getWholeMessage());

        rs = calculator.calculate(rs.getCalculatedNodes(), "undo");
        Assert.assertEquals("stack: 20 5", rs.getWholeMessage());
    }
}