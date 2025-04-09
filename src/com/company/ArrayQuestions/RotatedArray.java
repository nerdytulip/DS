package com.company.ArrayQuestions;

public class RotatedArray {
    public void rotateWithExtraSpace(int[] nums, int k) {
        int n = nums.length;
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            temp[(i + k) % n] = nums[i];
        }

        // Copy the rotated array back into the original array
        for (int i = 0; i < n; i++) {
            nums[i] = temp[i];
        }
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        // Normalize k to avoid unnecessary rotations
        k = k % n;

        // Reverse the entire array
        reverse(nums, 0, n - 1);

        // Reverse the first k elements
        reverse(nums, 0, k - 1);

        // Reverse the remaining n - k elements
        reverse(nums, k, n - 1);
    }

    // Helper method to reverse the portion of the array between indices left and right
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
