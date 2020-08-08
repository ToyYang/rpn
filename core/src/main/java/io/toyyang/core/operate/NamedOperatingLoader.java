package io.toyyang.core.operate;

import io.toyyang.core.node.Operator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NamedOperatingLoader implements OperatingLoader {
    private static Logger logger = Logger.getLogger(NamedOperatingLoader.class.getName());
    @Override
    public Operating load(Operator operator) {
        String name = operator.name();
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        try {
            Class<?> clazz = Class.forName("io.toyyang.core.operate.real." + name + "Operating");
            Object o = clazz.newInstance();
            if (o instanceof Operating) {
                return (Operating)o;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            logger.log(Level.ALL, e.getMessage());
        }

        return null;
    }
}
