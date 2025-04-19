package com.company.Interval;

import java.util.Arrays;

public class NonOverlappingIntervals {

    /**
     * https://leetcode.com/problems/non-overlapping-intervals/editorial/?envType=study-plan-v2&envId=leetcode-75
     * https://www.youtube.com/watch?v=XsrJgwGlRoc
     * */
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0){
            return 0;
        }

        // because we want to pick the one which ends the first and removing the ones overlapping with it
        Arrays.sort(intervals,(a,b) -> a[1] - b[1]);

        int count = 1;
        int previous_interval = 0;

        for(int i = 1;i<intervals.length;i++){

            // If start time of the next interval is greater than or equal to the end time of current interval, then we keep it
            if(intervals[i][0] >= intervals[previous_interval][1]){
                previous_interval = i;
                count++;
            }
        }

        return intervals.length - count;
    }
}
