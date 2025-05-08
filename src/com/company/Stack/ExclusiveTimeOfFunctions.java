package com.company.Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ExclusiveTimeOfFunctions {
    /**
     * https://youtu.be/3zqVinluGSM?si=pLFTmVwCLMlxDCi-
     * https://leetcode.com/problems/exclusive-time-of-functions/description/
     * */

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        int lastLogTimestamp = -1;

        for(String log : logs){
            String[] str = log.split(":");
            int funcId = Integer.parseInt(str[0]);
            boolean isStart = "start".equals(str[1]);
            int timestamp = Integer.parseInt(str[2]);

            if(!isStart){
                timestamp+=1;
            }

            if(!stack.isEmpty()){
                int currFunc = stack.peek();
                result[currFunc] = result[currFunc] + (timestamp - lastLogTimestamp);
            }

            if(isStart){
                stack.push(funcId);
            }else{
                stack.pop();
            }

            lastLogTimestamp = timestamp;
        }

        return result;
    }
}
