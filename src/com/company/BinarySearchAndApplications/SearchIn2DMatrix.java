package com.company.BinarySearchAndApplications;

public class SearchIn2DMatrix {

    /**
     * https://leetcode.com/problems/search-a-2d-matrix/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=RhPqIIOUiS8
     * */

    public boolean searchMatrix(int[][] matrix, int target) {
        int rowIdx = searchPotentialRow(matrix,target);
        if(rowIdx!=-1){
            return binarySeachOverRow(rowIdx,matrix,target);
        }else{
            return false;
        } 
    }

    private boolean binarySeachOverRow(int rowIdx, int[][] matrix, int target) {
        int low = 0;
        int high = matrix[rowIdx].length -1 ;

        while(low<= high){
            int mid = low +(high-low)/2;
            if(matrix[rowIdx][mid] == target) return true;
            else if(matrix[rowIdx][mid] > target) high = mid - 1;
            else low = mid + 1;
        }

        return false;
    }

    private int searchPotentialRow(int[][] matrix, int target) {
        int low = 0;
        int high = matrix.length -1;
        while(low<=high){
            int mid = low + (high - low)/2;

            if(matrix[mid][0]<=target && target<=matrix[mid][matrix[0].length-1]){
                return mid;
            }else if(matrix[mid][0] < target){
                low = mid + 1;
            }else if(matrix[mid][0] > target){
                high = mid -1;
            }
        }

        return -1;
    }


}
