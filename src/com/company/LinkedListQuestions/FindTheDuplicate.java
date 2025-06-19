package com.company.LinkedListQuestions;

public class FindTheDuplicate {
    /**
     * https://www.youtube.com/watch?v=_n5MR8IxR6c&t=206s
     * https://leetcode.com/problems/find-the-duplicate-number/description/
     * */
    public int findDuplicate(int[] nums) {
        // Initialize slow and fast pointers
        int slow = nums[0];
        int fast = nums[nums[0]];

        // Phase 1: Detect the cycle (tortoise and hare)
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // Phase 2: Find the entrance to the cycle (duplicate number)
        slow = 0;

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

}
