package io.toyyang.core.operate;

import io.toyyang.core.node.Operator;
import io.toyyang.core.operate.real.MinusOperating;
import io.toyyang.core.operate.real.PlusOperating;
import org.junit.Assert;
import org.junit.Test;

public class NamedOperatingLoaderTest {

    @Test
    public void load_plusAndMinusOperators_shouldBeLoaded() {
        NamedOperatingLoader loader = new NamedOperatingLoader();
        Assert.assertTrue(loader.load(Operator.PLUS) instanceof PlusOperating);
        Assert.assertTrue(loader.load(Operator.MINUS) instanceof MinusOperating);
    }

}