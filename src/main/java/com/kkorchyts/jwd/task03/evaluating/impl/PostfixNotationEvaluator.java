package com.kkorchyts.jwd.task03.evaluating.impl;

import com.kkorchyts.jwd.task03.evaluating.Evaluator;

import java.util.List;
import java.util.Stack;

public class PostfixNotationEvaluator implements Evaluator {
    private static final PostfixNotationEvaluator INSTANCE = new PostfixNotationEvaluator();

    private PostfixNotationEvaluator() {
    }

    @Override
    public Float evaluate(List<String> tokens) {
        Stack<String> resultStack = new Stack<>();

        for (String strToken : tokens) {
            if (strToken.matches("^(-?[1-9]+\\d*([.]\\d+)?)$|^(-?0[.]\\d*[1-9]+)$|^0$|^0.0$")) {
                resultStack.push(strToken);
            } else {
                String operand_2 = resultStack.pop();
                String operand_1 = resultStack.pop();
                resultStack.push(calculateMathOperation(strToken, operand_1, operand_2));
            }
        }

        return Float.parseFloat(resultStack.pop());
    }

    private String calculateMathOperation(String mathOperation, String operand_1, String operand_2) {
        Float foperand_1 = Float.valueOf(operand_1);
        Float foperand_2 = Float.valueOf(operand_2);
        switch (mathOperation) {
            case "+":
                return String.valueOf(foperand_1 + foperand_2);
            case "-":
                return String.valueOf(foperand_1 - foperand_2);
            case "*":
                return String.valueOf(foperand_1 * foperand_2);
            case "/":
                return String.valueOf(foperand_1 / foperand_2);
            default:
                throw new RuntimeException();
        }
    }

    public static PostfixNotationEvaluator getInstance() {
        return INSTANCE;
    }
}
