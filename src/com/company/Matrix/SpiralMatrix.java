package com.company.Matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    /**
     * https://leetcode.com/problems/spiral-matrix/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=aqVW8IuXUF0
     * */
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> res = new ArrayList<>();

        if (matrix.length == 0) {
            return res;
        }

        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while(rowBegin <= rowEnd && colBegin <= colEnd){

            //traverse right
            for(int j = colBegin;j<=colEnd;j++){
                res.add(matrix[rowBegin][j]);
            }
            rowBegin++;

            //traverse down
            for(int j = rowBegin;j<=rowEnd;j++){
                res.add(matrix[j][colEnd]);
            }
            colEnd--;

            //traverse left
            if(rowBegin <= rowEnd){
                for(int j = colEnd;j>=colBegin;j--){
                    res.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;

            //traverse up
            if(colBegin <= colEnd){
                for(int j = rowEnd;j>=rowBegin;j--){
                    res.add(matrix[j][colBegin]);
                }
            }
            colBegin++;
        }

        return res;
    }
}
