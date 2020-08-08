package io.toyyang.core.operate;

import io.toyyang.core.node.Operator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OperatingRegistry {
    private static OperatingRegistry instance = new OperatingRegistry();
    private Map<Operator, Operating> map = new HashMap<>();

    private OperatingRegistry() {
        OperatingLoader operatingLoader = new NamedOperatingLoader();
        for (Operator operator : Operator.values()) {
            Optional.ofNullable(operatingLoader.load(operator))
                    .ifPresent(op -> map.put(operator, op));
        }
    }

    public Operating getOperating(Operator operator) {
        return map.get(operator);
    }

    public static OperatingRegistry of() {
        return instance;
    }
}
