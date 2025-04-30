package com.company.Stack.MonotonicStack;

import java.util.ArrayDeque;

public class DailyTemperatures {

    /**
     * https://leetcode.com/problems/daily-temperatures/?envType=study-plan-v2&envId=leetcode-75
     * */
    public int[] dailyTemperatures(int[] temperatures) {

        ArrayDeque<Integer> helperStack = new ArrayDeque<>();

        int n = temperatures.length;
        int[] result = new int[n];

        for(int i = n -1;i>=0;i--){
            //prepping all indices with a lower or equal temp than current
            while(!helperStack.isEmpty() && temperatures[i] >=temperatures[helperStack.peek()]){
                helperStack.pop();
            }

            // if stack still has element, then the next warmer element exists
            if(!helperStack.isEmpty()){
                result[i] = helperStack.peek() - i;
            }

            helperStack.push(i);
        }

        return result;
    }
}
