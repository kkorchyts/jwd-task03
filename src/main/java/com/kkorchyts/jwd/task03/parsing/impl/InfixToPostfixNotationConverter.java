package com.kkorchyts.jwd.task03.parsing.impl;

import com.kkorchyts.jwd.task03.parsing.NotationConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class InfixToPostfixNotationConverter implements NotationConverter {
    private static Map<String, Integer> priority = new TreeMap<>();
    private static final InfixToPostfixNotationConverter INSTANCE = new InfixToPostfixNotationConverter();

    private InfixToPostfixNotationConverter() {
    }

    static {
        priority.put("*", 3);
        priority.put("/", 3);
        priority.put("-", 2);
        priority.put("+", 2);
        priority.put("(", 1);
        priority.put(")", -1);
        priority.put("0", 0);
        priority.put("1", 0);
        priority.put("2", 0);
        priority.put("3", 0);
        priority.put("4", 0);
        priority.put("5", 0);
        priority.put("6", 0);
        priority.put("7", 0);
        priority.put("8", 0);
        priority.put("9", 0);
    }

    public int getP(String token) {
        if (priority.containsKey(token)) {
            return priority.get(token);
        }

        throw new RuntimeException();
    }

    private boolean isNumber(String character) {
        return priority.containsKey(character) && priority.get(character) == 0;
    }

    private boolean isRightBlock(String character) {
        return priority.containsKey(character) && priority.get(character) == -1;
    }

    private boolean isLeftBlock(String character) {
        return priority.containsKey(character) && priority.get(character) == 1;
    }

    private boolean isMathOperation(String character) {
        return priority.containsKey(character) && priority.get(character) > 1;
    }

    @Override
    public List<String> convert(String expression) {
        String currentNumber = "";
        ArrayList<String> tokens = new ArrayList<>();
        Stack<String> texas = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            String currentToken = String.valueOf(expression.charAt(i));

            if (isNumber(currentToken)) {
                currentNumber += currentToken;
            }

            if ((!isNumber(currentToken) || i == expression.length() - 1) && currentNumber.length() > 0) {
                tokens.add(currentNumber);
                currentNumber = "";
            }

            if (isLeftBlock(currentToken)) {
                texas.push(currentToken);
                continue;
            }

            if (isRightBlock(currentToken)) {
                while (!isLeftBlock(texas.peek())) {
                    tokens.add(String.valueOf(texas.pop()));
                }
                texas.pop();
            }

            if (isMathOperation(currentToken)) {
                while (!texas.isEmpty() && getP(texas.peek()) >= getP(currentToken)) {
                    tokens.add(String.valueOf(texas.pop()));
                }
                texas.push(currentToken);
            }
        }

        while (!texas.isEmpty()) {
            tokens.add(String.valueOf(texas.pop()));
        }

        return tokens;
    }

    public static InfixToPostfixNotationConverter getInstance() {
        return INSTANCE;
    }
}
