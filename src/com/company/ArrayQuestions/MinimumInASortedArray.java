package com.company.ArrayQuestions;

public class MinimumInASortedArray {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length;

        while(left<right){
            int mid = left + (right - left)/2;

            if(nums[mid] > nums[right]){
                left = mid+1;
            }else{
                right = mid;
            }
        }

        return nums[left];
    }
}
