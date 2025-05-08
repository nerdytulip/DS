package com.company.BinarySearchAndApplications;

public class FindFirstAndLastPositionOfElementInSortedArray {
    /**
     * First occurrence - have a left value smaller than itself
     * Second occurrence - have a right value greater than itself
     *
     * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=bvaYNDKp830
     * */
    public int[] searchRange(int[] nums, int target) {
        int left = findLeftBound(nums,target);
        int right = findRightBound(nums,target);

        return new int[]{left,right};
    }

    private int findLeftBound(int[] nums, int target) {
        int index = -1;
        int low = 0;
        int high = nums.length - 1;

        while(low<=high){
            int mid = low + (high - low)/2;

            if(nums[mid] == target){
                index = mid;
                high = mid -1; // look further in left subarray
            } else if(nums[mid] < target){
                low = mid + 1;
            } else{
                high = mid -1;
            }
        }

        return index;
    }

    private int findRightBound(int[] nums, int target) {
        int index = -1;
        int low = 0;
        int high = nums.length - 1;

        while(low<=high){
            int mid = low + (high - low)/2;

            if(nums[mid] == target){
                index = mid;
                low = mid + 1; // look further in left subarray
            } else if(nums[mid] < target){
                low = mid + 1;
            } else{
                high = mid -1;
            }
        }

        return index;
    }

}
