package com.company.Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        Set<String> operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");

        for(String token : tokens){
            if(operators.contains(token)){
                if(!stack.isEmpty() && stack.size()>=2){
                    int first = stack.pop();
                    int second = stack.pop();

                    int result = performCalculation(second,first,token);

                    stack.push(result);
                }
            }else{
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    private int performCalculation(int a, int b,String operator){
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
