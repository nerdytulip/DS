package com.company.DP;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MinCostForTickets {

    private int solveRec(int[] days, int[] costs,int index) {
        if(index>=days.length){
            return 0;
        }

        //1 day pass
        int option1 = costs[0] + solveRec(days,costs,index+1);

        //7 day pass
        int i;
        for(i=index;i<days.length && days[i]<days[index]+7;i++);
        //send i because it will be the first day after 7th day
        int option2 = costs[1] + solveRec(days,costs,i);

        //30 days pass
        for(i=index;i<days.length && days[i]<days[index]+30;i++);
        //send i because it will be the first day after 7th day
        int option3 = costs[2] + solveRec(days,costs,i);

        return Math.min(option1, Math.min(option2,option3));
    }

    public int mincostTickets(int[] days, int[] costs) {
        return solveRec(days,costs,0);
    }

    //top-down array
    // TC = O[1]
    private int solveMem(int[] days, int[] costs,int index , int[] memo) {
        if(index>=days.length){
            return 0;
        }

        if(memo[index]!=-1){
            return memo[index];
        }

        //1 day pass
        int option1 = costs[0] + solveMem(days,costs,index+1,memo);

        //7 day pass
        int i;
        for(i=index;i<days.length && days[i]<days[index]+7;i++);
        //send i because it will be the first day after 7th day
        int option2 = costs[1] + solveMem(days,costs,i,memo);

        //30 days pass
        for(i=index;i<days.length && days[i]<days[index]+30;i++);
        //send i because it will be the first day after 30th day
        int option3 = costs[2] + solveMem(days,costs,i,memo);

        memo[index] = Math.min(option1, Math.min(option2,option3));

        return memo[index];
    }

    public int mincostTicketsMem(int[] days, int[] costs) {
        int[] memo = new int[days.length+1];
        Arrays.fill(memo,-1);
        return solveMem(days,costs,0,memo);
    }

    //bottom-up array
    // we are initializing the k as n-1 and populating dp[n] first because we started at 0 in bottom up and incremented it
    // so if we go bottom up we would start with n
    private int solveTabulation(int[] days, int[] costs) {

        int[] dp = new int[days.length+1];
        Arrays.fill(dp,Integer.MAX_VALUE);

        dp[days.length] = 0;

        for(int k = days.length-1;k>=0;k--){
            //1 day pass
            int option1 = costs[0] + dp[k+1];

            //7 day pass
            int i;
            for(i=k;i<days.length && days[i]<days[k]+7;i++);
            //send i because it will be the first day after 7th day
            int option2 = costs[1] + dp[i];

            //30 days pass
            for(i=k;i<days.length && days[i]<days[k]+30;i++);
            //send i because it will be the first day after 30th day
            int option3 = costs[2] +  dp[i];

            dp[k] = Math.min(option1, Math.min(option2,option3));
        }

        return dp[0];
    }

    private int solveSpaceOptimization(int[] days, int[] cost) {
        int ans = 0;

        // Define queues for week and month
        Queue<int[]> week = new LinkedList<>(); // Each element is {day, cost}
        Queue<int[]> month = new LinkedList<>();

        for (int day : days) {
            // Step 1: Remove expired days
            while (!week.isEmpty() && week.peek()[0] + 7 <= day) {
                week.poll();
            }

            while (!month.isEmpty() && month.peek()[0] + 30 <= day) {
                month.poll();
            }

            // Step 2: Add cost for the current day
            week.offer(new int[]{day, ans + cost[1]});
            month.offer(new int[]{day, ans + cost[2]});

            // Step 3: Update ans
            ans = Math.min(ans + cost[0], Math.min(week.peek()[1], month.peek()[1]));
        }

        return ans;
    }


}
