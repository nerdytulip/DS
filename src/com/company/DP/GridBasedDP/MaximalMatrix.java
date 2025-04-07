package com.company.DP.GridBasedDP;

import java.util.Arrays;

public class MaximalMatrix {

    //https://leetcode.com/problems/maximal-square/description/

    int solveRec(char[][] matrix, int i, int j,int[] maxi){
        if(i>=matrix.length || j>=matrix[0].length){
            return 0;
        }

        int right = solveRec(matrix,i+1,j,maxi);
        int diagonal = solveRec(matrix,i+1,j+1,maxi);
        int down = solveRec(matrix,i,j+1,maxi);

        if(matrix[i][j] == '1'){
            //taking minimum here because it will ensure if we take min out of it the square formed from it will have all ones
            int ans = 1 + Math.min(right,Math.min(diagonal,down));
            maxi[0] = Math.max(maxi[0],ans);
            return ans;
        }else{
            return 0;
        }
    }

    public int maximalSquareRec(char[][] matrix) {
        int[] maxiHolder = new int[]{0};
        solveRec(matrix,0,0,maxiHolder);
        return maxiHolder[0]*maxiHolder[0];
    }

    int solveMem(char[][] matrix, int i, int j,int[] maxi,int[][] memo){
        if(i>=matrix.length || j>=matrix[0].length){
            return 0;
        }

        if(memo[i][j]!=-1){
            return memo[i][j];
        }

        int right = solveMem(matrix,i+1,j,maxi,memo);
        int diagonal = solveMem(matrix,i+1,j+1,maxi,memo);
        int down = solveMem(matrix,i,j+1,maxi,memo);

        if(matrix[i][j] == '1'){
            //taking minimum here because it will ensure if we take min out of it the square formed from it will have all ones
            memo[i][j] = 1 + Math.min(right,Math.min(diagonal,down));
            maxi[0] = Math.max(maxi[0],memo[i][j]);
            return memo[i][j];
        }else{
            return memo[i][j] = 0;
        }
    }

    public int maximalSquareMem(char[][] matrix) {
        int[][] memo = new int[matrix.length][matrix[0].length];
        for(int[] row : memo){
            Arrays.fill(row,-1);
        }
        int[] maxiHolder = new int[]{0};
        solveMem(matrix,0,0,maxiHolder,memo);
        return maxiHolder[0]*maxiHolder[0];
    }

    int solveTab(char[][] matrix,int[] maxi){
        //because we are doing +1 in for loop, so it will go out of bound for first index
        int[][] memo = new int[matrix.length+1][matrix[0].length+1];
        for(int[] row : memo){
            Arrays.fill(row,0);
        }

        for(int i = matrix.length-1;i>=0;i--){
            for(int j = matrix[0].length-1;j>=0;j--){
                int right = memo[i+1][j];
                int diagonal = memo[i+1][j+1];
                int down = memo[i][j+1];

                if(matrix[i][j] == '1'){
                    //taking minimum here because it will ensure if we take min out of it the square formed from it will have all ones
                    memo[i][j] = 1 + Math.min(right,Math.min(diagonal,down));
                    maxi[0] = Math.max(maxi[0],memo[i][j]);
                }else{
                    memo[i][j] = 0;
                }
            }
        }

        return memo[0][0];
    }

    public int maximalSquareTab(char[][] matrix) {

        int[] maxiHolder = new int[]{0};
        solveTab(matrix,maxiHolder);
        return maxiHolder[0]*maxiHolder[0];
    }

    int solveSO(char[][] matrix,int[] maxi){
        //because we are doing +1 in for loop, so it will go out of bound for first index
        int[] curr = new int[matrix[0].length+1];
        Arrays.fill(curr,0);

        int[] next = new int[matrix[0].length+1];
        Arrays.fill(next,0);


        for(int i = matrix.length-1;i>=0;i--){
            for(int j = matrix[0].length-1;j>=0;j--){
                int right = next[j];
                int diagonal = next[j+1];
                int down = curr[j+1];

                if(matrix[i][j] == '1'){
                    //taking minimum here because it will ensure if we take min out of it the square formed from it will have all ones
                    curr[j] = 1 + Math.min(right,Math.min(diagonal,down));
                    maxi[0] = Math.max(maxi[0],curr[j]);
                }else{
                    curr[j] = 0;
                }
            }

            int[] temp = next;
            next = curr;
            curr = temp;
        }

        return next[0];
    }

    public int maximalSquare(char[][] matrix) {

        int[] maxiHolder = new int[]{0};
        solveSO(matrix,maxiHolder);
        return maxiHolder[0]*maxiHolder[0];
    }
}
