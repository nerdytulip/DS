package com.company.DP.ZeroOneKnapsack;

import java.util.Arrays;

public class QuestionsWithBrainPower {

    private long solveRec(int[][] questions, int i) {
        if(i >= questions.length){
            return 0;
        }

        //include
        long include_points = questions[i][0] + solveRec(questions,i+questions[i][1]+1);

        //exclude
        long exclude_points = 0 + solveRec(questions,i+1);

        return Math.max(include_points,exclude_points);
    }

    public long mostPointsRec(int[][] questions) {
        return solveRec(questions,0);
    }

    private long solveMem(int[][] questions, int i,long[] memo) {
        if(i >= questions.length){
            return 0;
        }

        if(memo[i]!=-1){
            return memo[i];
        }

        //include
        long include_points = questions[i][0] + solveMem(questions,i+questions[i][1]+1,memo);

        //exclude
        long exclude_points = 0 + solveMem(questions,i+1,memo);

        return memo[i] = Math.max(include_points,exclude_points);
    }

    public long mostPointsMem(int[][] questions) {
        long[] memo = new long[questions.length];
        Arrays.fill(memo,-1);
        return solveMem(questions,0,memo);
    }

    private long solveTab(int[][] questions) {
        long[] dp = new long[questions.length+1];

        for(int i = questions.length-1;i>=0;i--){
            int nextQuestion = i+questions[i][1]+1;
            long include = questions[i][0];
            if(nextQuestion<questions.length){
                include+=dp[nextQuestion];
            }

            long exclude = dp[i+1];

            dp[i] = Math.max(include,exclude);
        }
        return dp[0];
    }

    public long mostPoints(int[][] questions) {
        return solveTab(questions);
    }
}
