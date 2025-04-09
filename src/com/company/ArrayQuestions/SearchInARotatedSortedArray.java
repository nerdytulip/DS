package com.company.ArrayQuestions;

public class SearchInARotatedSortedArray {

    // Main search method - 2 * O(logn)
    public int searchUsingTwoBinarySearch(int[] nums, int target) {
        int pivot = findPivot(nums);

        // If no pivot is found, the array is not rotated.
        if (pivot == -1) {
            return binarySearch(nums, target, 0, nums.length - 1);
        }

        // If the target is at the pivot index
        if (nums[pivot] == target) {
            return pivot;
        }

        // Decide which subarray to search:
        // If target is greater than or equal to the first element,
        // then the target lies in the first (left) sorted subarray.
        if (target >= nums[0]) {
            return binarySearch(nums, target, 0, pivot - 1);
        }

        // Otherwise, target lies in the second (right) sorted subarray.
        return binarySearch(nums, target, pivot + 1, nums.length - 1);
    }

    // Helper method to perform a standard binary search
    private int binarySearch(int[] nums, int target, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Helper method to find the pivot (largest element)
    private int findPivot(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        // If the list is not rotated at all
        if (nums[low] <= nums[high]) {
            return -1;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if the mid element is the pivot
            if (mid < high && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            // Check if the element before mid is the pivot
            if (mid > low && nums[mid] < nums[mid - 1]) {
                return mid - 1;
            }

            // Decide which half to choose for finding the pivot
            if (nums[mid] >= nums[low]) {
                // Pivot must be in the right half
                low = mid + 1;
            } else {
                // Pivot is in the left half
                high = mid - 1;
            }
        }

        return -1;  // Should not reach here if the array is rotated
    }

    //TC - O(logn)
    private int modifiedBinarySearch(int arr[],int target,int left, int right){
        if(left > right){
            return -1;
        }

        int mid = left + (right - left)/2;

        if(arr[mid] == target){
            return mid;
        }

        //if left subarray is sorted
        if(arr[mid] >= arr[left]){
            if(arr[left]<=target && target <= arr[mid])
                return modifiedBinarySearch(arr,target,left,mid-1);
            else
                return modifiedBinarySearch(arr,target,mid+1,right);

        }else{
            //rigth subarray is sorted
            if(arr[mid] <= target && target <= arr[right])
                return modifiedBinarySearch(arr,target,mid+1,right);
            else
                return modifiedBinarySearch(arr,target,left,mid-1);
        }
    }

    public int search(int[] nums, int target) {
        return modifiedBinarySearch(nums,target,0,nums.length-1);
    }

    // Example usage:
    public static void main(String[] args) {
        SearchInARotatedSortedArray solution = new SearchInARotatedSortedArray();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int index = solution.search(nums, target);
        System.out.println("Target " + target + " found at index: " + index);
    }
}
