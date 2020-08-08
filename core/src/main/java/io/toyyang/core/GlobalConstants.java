package io.toyyang.core;

import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GlobalConstants {
    public static final MathContext MATH_CONTEXT_15 = new MathContext(15);
    public static final NumberFormat NUMBER_FORMAT;
    public static final String ERROR_MSG_TEMPLATE = "operator %s (position: %d): %s";
    public static final String CALCULATED_INFO_TEMPLATE = "stack: %s";
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    static {
        NUMBER_FORMAT = new DecimalFormat("#,###.##########");
        NUMBER_FORMAT.setRoundingMode(RoundingMode.DOWN);
    }
}
