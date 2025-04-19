package com.company.TwoPointer;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int index = 0; // Pointer to track the position for the next non-zero element

        // First pass: Move all non-zero elements to the front
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // Swap nums[i] with nums[index]
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;

                index++; // Move index forward for the next non-zero
            }
        }
    }
}
