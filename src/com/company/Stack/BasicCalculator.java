package com.company.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {
    /**
     * https://leetcode.com/problems/basic-calculator/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=3AEKyHx3tzU
     * */
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();

        int number = 0;
        int result = 0;
        int sign = 1; // will reflect + and - sign , for + -> it will be +1 and for - -> it will be -1

        for(int i =0;i<s.length();i++){
            char ch = s.charAt(i);

            if(Character.isDigit(ch)){
                number = number * 10 + (ch - '0');
            }else if(ch == '+'){
                result+= (sign * number);
                number = 0;
                sign = 1;
            }else if(ch == '-'){
                result+= (sign * number);
                number = 0;
                sign = -1;
            }else if(ch == '('){
                // new fresh beginning for inside bracket
                stack.push(result);
                stack.push(sign);
                result = 0;
                number = 0;
                sign = 1;
            }else if(ch == ')'){
                result += (number * sign); // this is result from bracket
                number = 0;

                int stackSign = stack.pop();
                int prevResult = stack.pop();

                result *= stackSign;
                result += prevResult;
            }else if (ch == ' ') {
                continue;
            }
        }

        // Add any remaining number
        result += sign * number;

        return result;
    }
}
