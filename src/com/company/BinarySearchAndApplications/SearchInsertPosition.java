package com.company.BinarySearchAndApplications;

public class SearchInsertPosition {
    /**
     * https://leetcode.com/problems/search-insert-position/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public int searchInsert(int[] nums, int target) {
        return lowerBound(nums,nums.length,target);
    }

    public static int lowerBound(int[] arr, int n, int x) {
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer - this if condition is lower bound condition
            if (arr[mid] >= x) {
                ans = mid;
                //look for smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }
}
