package com.company.Matrix;

public class RotateImage {
    /**
     * https://leetcode.com/problems/rotate-image/submissions/1623951926/?envType=study-plan-v2&envId=top-interview-150
     *
     * 🧠 Summary of Positions:
     * Logical Position	Formula
     * Top Left	(i, j)
     * Top Right	(j, n - 1 - i)
     * Bottom Right	(n - 1 - i, n - 1 - j)
     * Bottom Left	(n - 1 - j, i)
     *
     * These come from applying (i, j) → (j, n - 1 - i) repeatedly (4 times = full rotation cycle).
     * */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // keeps count of rings
        for(int i=0;i<(n+1)/2;i++){
            //keeps track of each element in ring
            for(int j=0;j<n/2;j++){
                //start 4 ways swap
                // temp = bottom left
                int temp = matrix[n-1-j][i];

                // bottom left = bottom right
                matrix[n-1-j][i] = matrix[n-1-i][n-j-1];

                // bottom right = top right
                matrix[n-1-i][n-j-1] = matrix[j][n-1-i];

                //top right = top left
                matrix[j][n-1-i] = matrix[i][j];

                // top left = temp
                matrix[i][j] = temp;
            }
        }
    }
}
