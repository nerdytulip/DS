package com.company.DP.UnboundedKnapsack;

import java.util.Arrays;

public class Pizza3nSlices {

    private int solveRec(int index,int endIndex, int[] slices, int n) {
        if(n==0 || index>endIndex){
            return 0;
        }

        int take = slices[index] + solveRec(index+2,endIndex,slices,n-1);
        int notTake = 0+ solveRec(index+1,endIndex,slices,n);

        return Math.max(take,notTake);
    }

    public int maxSizeSlicesRec(int[] slices) {
        int k = slices.length;
        //eat first slice
        int case1 = solveRec(0,k-2,slices,k/3);
        //eat last slice
        int case2 = solveRec(1,k-1,slices,k/3);
        return Math.max(case1,case2);
    }

    private int solveMem(int index,int endIndex, int[] slices, int n,int[][] memo) {
        if(n==0 || index>endIndex){
            return 0;
        }

        if(memo[index][n]!=-1){
            return memo[index][n];
        }

        int take = slices[index] + solveMem(index+2,endIndex,slices,n-1,memo);
        int notTake = 0+ solveMem(index+1,endIndex,slices,n,memo);

        memo[index][n] =  Math.max(take,notTake);
        return memo[index][n];
    }

    public int maxSizeSlicesMem(int[] slices) {

        int k = slices.length;
        int[][] memo1 = new int[k][k];
        for(int[] row : memo1){
            Arrays.fill(row,-1);
        }

        int[][] memo2 = new int[k][k];
        for(int[] row : memo2){
            Arrays.fill(row,-1);
        }

        //eat first slice
        int case1 = solveMem(0,k-2,slices,k/3,memo1);
        //eat last slice
        int case2 = solveMem(1,k-1,slices,k/3,memo2);

        return Math.max(case1,case2);
    }


    public int maxSizeSlicesTab(int[] slices) {

        int k = slices.length;
        int[][] memo1 = new int[k+2][k+2];
        for(int[] row : memo1){
            Arrays.fill(row,0);
        }

        int[][] memo2 = new int[k+2][k+2];
        for(int[] row : memo2){
            Arrays.fill(row,0);
        }

        //eat first slice
        for(int index= k-2;index>=0;index--){
            for(int n=1;n<=k/3;n++){
                int take = slices[index] + memo1[index+2][n-1];
                int notTake = 0+ memo1[index+1][n];

                memo1[index][n] =  Math.max(take,notTake);
            }
        }

        int case1 = memo1[0][k/3];

        //eat last slice
        //eat first slice
        for(int index= k-1;index>=1;index--){
            for(int n=1;n<=k/3;n++){
                int take = slices[index] + memo2[index+2][n-1];
                int notTake = 0+ memo2[index+1][n];

                memo2[index][n] =  Math.max(take,notTake);
            }
        }

        int case2 = memo2[1][k/3];

        return Math.max(case1,case2);
    }

    public int maxSizeSlicesSO(int[] slices) {

        int k = slices.length;
        int[] prev1 = new int[k+2];
        Arrays.fill(prev1,0);

        int[] current1 = new int[k+2];
        Arrays.fill(current1,0);

        int[] next1 = new int[k+2];
        Arrays.fill(next1,0);

        //eat first slice
        for(int index= k-2;index>=0;index--){
            for(int n=1;n<=k/3;n++){
                int take = slices[index] + next1[n-1];
                int notTake = 0+ current1[n];

                prev1[n] =  Math.max(take,notTake);
            }

            int[] temp = next1;
            next1 = current1;
            current1 = prev1;
            prev1 = temp;
        }

        int case1 = current1[k/3];

        int[] prev2 = new int[k+2];
        Arrays.fill(prev2,0);

        int[] current2 = new int[k+2];
        Arrays.fill(current2,0);

        int[] next2 = new int[k+2];
        Arrays.fill(next2,0);

        for(int index= k-1;index>=1;index--){
            for(int n=1;n<=k/3;n++){
                int take = slices[index] + next2[n-1];
                int notTake = 0+ current2[n];

                prev2[n] =  Math.max(take,notTake);
            }

            int[] temp = next2;
            next2 = current2;
            current2 = prev2;
            prev2 = temp;
        }

        int case2 = current2[k/3];

        return Math.max(case1,case2);
    }
}
