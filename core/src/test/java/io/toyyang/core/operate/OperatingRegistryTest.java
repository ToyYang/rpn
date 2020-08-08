package io.toyyang.core.operate;

import io.toyyang.core.node.Operator;
import io.toyyang.core.operate.real.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class OperatingRegistryTest {

    @Test
    public void getOperating() {
        Map<Operator, Class<? extends Operating>> map = new HashMap<>();

        map.put(Operator.PLUS, PlusOperating.class);
        map.put(Operator.MINUS, MinusOperating.class);
        map.put(Operator.TIMES, TimesOperating.class);
        map.put(Operator.DIV, DivOperating.class);
        map.put(Operator.SQRT, SqrtOperating.class);
        map.put(Operator.CLEAR, ClearOperating.class);
        map.put(Operator.UNDO, UndoOperating.class);

        map.forEach((key, value) -> assertTrue(value.isInstance(OperatingRegistry.of().getOperating(key))));
    }
}