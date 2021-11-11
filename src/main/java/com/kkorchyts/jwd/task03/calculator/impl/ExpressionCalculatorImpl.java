package com.kkorchyts.jwd.task03.calculator.impl;

import com.kkorchyts.jwd.task03.calculator.ExpressionCalculator;
import com.kkorchyts.jwd.task03.evaluating.Evaluator;
import com.kkorchyts.jwd.task03.evaluating.impl.PostfixNotationEvaluator;
import com.kkorchyts.jwd.task03.parsing.NotationConverter;
import com.kkorchyts.jwd.task03.parsing.impl.InfixToPostfixNotationConverter;

import java.util.List;

public class ExpressionCalculatorImpl implements ExpressionCalculator {
    @Override
    public Float evaluateExpression(String expression) {
        NotationConverter notationConverter = InfixToPostfixNotationConverter.getInstance();
        Evaluator evaluator = PostfixNotationEvaluator.getInstance();
        List<String> tokens = notationConverter.convert(expression);
        return evaluator.evaluate(tokens);
    }
}
