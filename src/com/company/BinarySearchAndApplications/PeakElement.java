package com.company.BinarySearchAndApplications;

public class PeakElement {
    /**
     * https://leetcode.com/problems/find-peak-element/?envType=study-plan-v2&envId=leetcode-75
     * */
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        int mid = -1;

        while(left<=right){
             mid = left + (right - left)/2;
            if ((mid == 0 || nums[mid] > nums[mid-1]) && (mid == nums.length-1 || nums[mid] > nums[mid+1])) {
                return mid;
            } else if(mid > 0 && nums[mid-1] > nums[mid]){
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }
        return mid;
    }
}
