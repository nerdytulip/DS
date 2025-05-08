package com.company.Matrix;

public class SetMatrixZeroes {
    /**
     * https://leetcode.com/problems/set-matrix-zeroes/submissions/1623986599/?envType=study-plan-v2&envId=top-interview-150
     *https://www.youtube.com/watch?v=dSxt3ZCbIqA
     * */
    public void setZeroes(int[][] matrix) {
        boolean firstRow = false, firstCol = false;

        //set markers in first row and first column
        for(int i=0;i< matrix.length;i++){
            for (int j=0;j< matrix[0].length;j++){
                if(matrix[i][j] == 0){
                    if(i == 0) firstRow = true;
                    if(j == 0) firstCol = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        //replace inner matrix
        for(int i=1;i< matrix.length;i++){
            for(int j=1;j< matrix[0].length;j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        // last remaining check
        if(firstRow){
            for(int j=0;j< matrix[0].length;j++){
                matrix[0][j] = 0;
            }
        }

        if(firstCol){
            for(int i=0;i< matrix.length;i++){
                matrix[i][0] = 0;
            }
        }
    }
}
