package com.kkorchyts.jwd.task03;

import com.kkorchyts.jwd.task03.calculator.ExpressionCalculator;
import com.kkorchyts.jwd.task03.calculator.impl.ExpressionCalculatorImpl;
import org.junit.Assert;
import org.junit.Test;

public class ExpressionCalculatorTest {
    @Test
    public void regionCreationPointInRightOrderTest() {
        String expr1 = "(2+2)*2";
        Float expr1ExpectedResult = 8f;

        String expr2 = "(12+(12*12)+31)-7*8/(2-1)";
        Float expr2ExpectedResult = 131f;

        ExpressionCalculator calculator = new ExpressionCalculatorImpl();

        Float expr1Result = calculator.evaluateExpression(expr1);
        Float expr2Result = calculator.evaluateExpression(expr2);

        Assert.assertEquals(expr1ExpectedResult, expr1Result);
        Assert.assertEquals(expr2ExpectedResult, expr2Result);

    }
}
