package io.toyyang.core.node;

import java.util.regex.Pattern;

public class Node {
    public static final Pattern NUM_REG = Pattern.compile("^[+-]?(?:\\d+\\.?\\d*|\\d*\\.\\d+)$");
    protected String rawValue;
    private boolean isNumber;
    private Kind kind;

    public Node(boolean isNumber, Kind kind) {
        this.isNumber = isNumber;
        this.kind = kind;
    }

    public Node(String v) {
        isNumber = NUM_REG.matcher(v).matches();//TODO
        this.kind = isNumber ? Kind.ARGUMENT : Kind.OPERATOR;
        this.rawValue = v;
    }

    public String getRawValue() {
        return rawValue;
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }

    public boolean isNumber() {
        return isNumber;
    }

    public Kind getKind() {
        return kind;
    }

    public boolean isOperator() {
        return kind == Kind.OPERATOR;
    }

    public enum Kind {
        OPERATOR, ARGUMENT
    }
}
