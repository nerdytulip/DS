package com.company.DP;

import java.util.Arrays;

public class KnapsackProblem {

    int solveRec(int W, int wt[], int val[], int index ){
        //base case
        //if only 1 item to steal , the just compare it's weight with the knapsack capacity
        if(index == 0) {
            if (wt[0] <= W) {
                return val[0];
            }else{
                return 0;
            }
        }

        int include = 0;
        if(wt[index]<=W){
            include = val[index] + solveRec(W - wt[index],wt,val,index-1);
        }

        int exclude = 0 + solveRec(W,wt,val,index-1);

        int ans = Math.max(exclude,include);
        return ans;
    }
    int knapSack(int W, int wt[], int val[], int n)
    {
        return solveRec(W,wt,val,n-1);
    }

    int solveMem(int W, int wt[], int val[], int index ,int[][] memo){
        //base case
        //if only 1 item to steal , the just compare it's weight with the knapsack capacity
        if(index == 0) {
            if (wt[0] <= W) {
                return val[0];
            }else{
                return 0;
            }
        }

        if(memo[index][W]!=-1){
            return memo[index][W];
        }

        int include = 0;
        if(wt[index]<=W){
            include = val[index] + solveRec(W - wt[index],wt,val,index-1);
        }

        int exclude = 0 + solveRec(W,wt,val,index-1);

        memo[index][W] = Math.max(exclude,include);
        return memo[index][W];
    }

    int knapSackMem(int W, int wt[], int val[], int n)
    {
        int[][] memo = new int[n][W+1];
        Arrays.stream(memo).forEach(row -> Arrays.fill(row,-1));
        return solveMem(W,wt,val,n-1,memo);
    }

    int knapSackTabulation(int W, int wt[], int val[], int n)
    {
        int[][] memo = new int[n][W+1];
        Arrays.stream(memo).forEach(row -> Arrays.fill(row,0));

        //index = row
        for(int w=wt[0];w<=W;w++){
            if (wt[0] <= W) {
                memo[0][w] = val[0];
            }else{
                memo[0][w] = 0;
            }
        }

        for(int index = 1;index<n;index++){
            for(int w = 0;w<=W;w++){
                int include = 0;
                if(wt[index]<=w){
                    include = val[index] + memo[index-1][w - wt[index]];
                }

                int exclude = 0 + memo[index-1][w];

                memo[index][w] = Math.max(exclude,include);
            }
        }

        return memo[n-1][W];
    }

    int knapSackTabulationSpaceOptimized(int W, int wt[], int val[], int n)
    {
        int[] prev = new int[W+1];
        Arrays.fill(prev,0);

        int[] current = new int[W+1];
        Arrays.fill(current,0);

        //index = row
        for(int w=wt[0];w<=W;w++){
            if (wt[0] <= W) {
                prev[w] = val[0];
            }else{
                prev[w] = 0;
            }
        }

        for(int index = 1;index<n;index++){
            for(int w = 0;w<=W;w++){
                int include = 0;
                if(wt[index]<=w){
                    include = val[index] + prev[w - wt[index]];
                }

                int exclude = 0 + prev[w];

                current[w] = Math.max(exclude,include);
            }
            prev = current;
        }

        return prev[W];
    }

    // Just uses one array current and traverse from right to left in current array , as curr[x] relies on either prev[w] or prev[w-wt[i]]
    int knapSackTabulationSpaceOptimized_One_Array(int W, int wt[], int val[], int n)
    {
        int[] current = new int[W+1];
        Arrays.fill(current,0);

        //index = row
        for(int w=wt[0];w<=W;w++){
            if (wt[0] <= W) {
                current[w] = val[0];
            }else{
                current[w] = 0;
            }
        }

        for(int index = 1;index<n;index++){
            for(int w = W;w>=0;w--){
                int include = 0;
                if(wt[index]<=w){
                    include = val[index] + current[w - wt[index]];
                }

                int exclude = 0 + current[w];

                current[w] = Math.max(exclude,include);
            }
        }

        return current[W];
    }


    public static void main(String[] args) {
        int profit[] = new int[] { 60, 100, 120 };
        int weight[] = new int[] { 10, 20, 30 };
        int W = 50;
        int n = profit.length;

        KnapsackProblem knapsackProblem = new KnapsackProblem();
        System.out.println(knapsackProblem.knapSackTabulationSpaceOptimized_One_Array(W,weight,profit,n));
    }
}
