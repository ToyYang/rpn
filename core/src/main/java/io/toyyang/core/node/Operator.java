package io.toyyang.core.node;

public enum Operator {
    PLUS("+", "Addition"),
    MINUS("-", "Subtraction"),
    TIMES("*", "Multiply"),
    DIV("/", "Division"),
    SQRT("sqrt", "Square root"),
    UNDO("undo", "Rollback last operation"),
    CLEAR("clear", "Clear the stack"),

    //currently unsupported
    SIN("sin", "Sine"),
    COS("cos", "Cosine"),
    TAN("tan", "Tangent"),
    COT("cot", "Cotangent"),
    ATAN("atan", "Arc tangent"),
    FACTORIAL("n!", "Factorial"),
    RODO("redo", "Redo"),
    SWAP("swap", "Swap");

    private String symbol;
    private String description;

    Operator(String symbol, String description) {
        this.symbol = symbol;
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    public static Operator getBySymbol(String symbol) {
        for (Operator op : values()) {
            if (op.getSymbol().equalsIgnoreCase(symbol)) {
                return op;
            }
        }

        return null;
    }
}
