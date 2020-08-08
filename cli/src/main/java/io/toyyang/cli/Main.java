package io.toyyang.cli;

import io.toyyang.core.calculator.CalculatedResult;
import io.toyyang.core.calculator.Calculator;
import io.toyyang.core.node.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Node> calculatedNodeList = new LinkedList<>();

        System.out.println("You can input some numbers and operators(+,-,*,/,sqrt,clear,undo), split them by space:");

        String sequence;
        Calculator calculator = new Calculator();
        while((sequence = bufferedReader.readLine()) != null) {
            CalculatedResult rs = calculator.calculate(calculatedNodeList, sequence);
            System.out.println(rs.getWholeMessage());
        }
    }
}
