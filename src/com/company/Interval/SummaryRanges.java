package com.company.Interval;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    /**
     * https://leetcode.com/problems/summary-ranges/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            // Keep iterating until the next element is one more than the current element.
            while (i + 1 < nums.length && nums[i] + 1 == nums[i + 1]) {
                i++;
            }

            if (start != nums[i]) {
                ranges.add(start + "->" + nums[i]);
            } else {
                ranges.add(String.valueOf(start));
            }
        }

        return ranges;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,4,5,7};
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.summaryRanges(nums);
    }
}
