package com.company.Interval;

import java.util.Arrays;

public class InsertIntervals {
    /**
     * https://leetcode.com/problems/insert-interval/description/
     *
     * https://www.youtube.com/watch?v=wCBtjZxw1xY
     * */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] result = new int[intervals.length+1][2];
        int i =0, j=0;

        // add all intervals that end before the new interval starts
        while(i< intervals.length && intervals[i][1] < newInterval[0]){
            result[j++] = intervals[i++];
        }

        //merge all overlapping intervals
        while(i<intervals.length && intervals[i][0]<= newInterval[1]){
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1],intervals[i++][1]);
        }

        result[j++] = newInterval;

        //add remaining intervals
        while(i<intervals.length){
            result[j++] = intervals[i++];
        }

        return Arrays.copyOf(result,j);
    }
}
