package com.company.DP.GridBasedDP;

public class MinimumFallingPath {

    private int solveRec(int[][] matrix, int row, int col) {
        if(col < 0 || col == matrix.length){
            return Integer.MAX_VALUE;
        }

        if(row == matrix.length-1){
            return matrix[row][col];
        }

        // calculate the minimum falling path sum starting from each possible next step
        int left = solveRec(matrix, row + 1, col);
        int middle = solveRec(matrix, row + 1, col + 1);
        int right = solveRec(matrix, row + 1, col - 1);

        int minFallingSum = matrix[row][col] + Math.min(left, Math.min(middle, right));

        return minFallingSum;
    }

    public int minFallingPathSumRec(int[][] matrix) {
        int minFallingSum = Integer.MAX_VALUE;
        for(int startCol = 0;startCol< matrix.length;startCol++){
            minFallingSum = Math.min(minFallingSum,solveRec(matrix,0,startCol));
        }

        return minFallingSum;
    }

    private int solveMem(int[][] matrix, int row, int col,Integer memo[][]) {
        if(col < 0 || col == matrix.length){
            return Integer.MAX_VALUE;
        }

        if(row == matrix.length-1){
            return matrix[row][col];
        }

        // same as initializing memo with -1 and then using that value
        if(memo[row][col]!=null){
            return memo[row][col];
        }

        // calculate the minimum falling path sum starting from each possible next step
        int left = solveMem(matrix, row + 1, col,memo);
        int middle = solveMem(matrix, row + 1, col + 1,memo);
        int right = solveMem(matrix, row + 1, col - 1,memo);

        int minFallingSum = matrix[row][col] + Math.min(left, Math.min(middle, right));

        return memo[row][col] = minFallingSum;
    }

    public int minFallingPathSumMem(int[][] matrix) {
        int minFallingSum = Integer.MAX_VALUE;
        Integer memo[][] = new Integer[matrix.length][matrix[0].length];

        for(int startCol = 0;startCol< matrix.length;startCol++){
            minFallingSum = Math.min(minFallingSum,solveMem(matrix,0,startCol,memo));
        }

        return minFallingSum;
    }

    private int solveTab(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int dp[][] = new int[m +1][n +1];

        // 1) Fill the “fake” bottom row with INF
        for(int col = 0; col<= n; col++){
            dp[m][col] = Integer.MAX_VALUE;
        }

        // 2) Fill the two “fake” side‐columns with INF
        for(int row = 0; row< m; row++){
            dp[row][n] = Integer.MAX_VALUE;
        }

        // 3) Base case: real bottom row lives at dp[matrix.length-1][1..n]
        for (int col = 0; col < n; col++) {
            dp[m - 1][col] = matrix[m - 1][col];
        }

        for(int row = m -2; row>=0; row--){
            for(int col = n -1; col >=0; col--){
                int left = dp[row + 1][col];
                int middle = dp[row + 1][col + 1];
                int right = (col > 0) ? dp[row + 1][col - 1] : Integer.MAX_VALUE;

                int minCurrentFallingSum = matrix[row][col] + Math.min(left, Math.min(middle, right));
                dp[row][col] = minCurrentFallingSum;

            }
        }

        int minFallingSum = Integer.MAX_VALUE;
        for (int col = 0; col < n; col++) {
            minFallingSum = Math.min(minFallingSum, dp[0][col]);
        }

        return minFallingSum;
    }

    public int minFallingPathSumTab(int[][] matrix) {
        return solveTab(matrix);
    }

    private int solveSO(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] curr = new int[n +1];
        int[] next = new int[n +1];

        // 3) Base case: real bottom row lives at dp[matrix.length-1][1..n]
        for (int col = 0; col < n; col++) {
            next[col] = matrix[m - 1][col];
        }

        for(int row = m -2; row>=0; row--){
            for(int col = n -1; col >=0; col--){
                int left = next[col];
                int middle = (col < n - 1) ? next[col + 1] : Integer.MAX_VALUE;
                int right = (col > 0) ? next[col - 1] : Integer.MAX_VALUE;

                int minCurrentFallingSum = matrix[row][col] + Math.min(left, Math.min(middle, right));
                curr[col] = minCurrentFallingSum;
            }
            int[] tmp = next;
            next = curr;
            curr = tmp;
        }

        int minFallingSum = Integer.MAX_VALUE;
        for (int col = 0; col < n; col++) {
            minFallingSum = Math.min(minFallingSum, next[col]);
        }

        return minFallingSum;
    }

    public int minFallingPathSum(int[][] matrix) {
        return solveSO(matrix);
    }


}
