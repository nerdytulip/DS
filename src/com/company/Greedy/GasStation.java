package com.company.Greedy;

public class GasStation {

    /**
     * https://leetcode.com/problems/gas-station/submissions/1620764787/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=fOaUh1_fJPw
     * */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0;

        for(int i=0;i<gas.length;i++){
            totalGas+=gas[i];
            totalCost+=cost[i];
        }

        if(totalGas<totalCost){
            return -1;
        }

        int currentGas = 0;
        int startIndex = 0;

        for(int i=0;i<gas.length;i++){
            currentGas += gas[i] - cost[i];

            if(currentGas<0){
                startIndex = i+1;
                currentGas = 0;
            }
        }

        return startIndex;
    }
}
