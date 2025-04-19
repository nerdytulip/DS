package com.company.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    /**
     * https://leetcode.com/problems/merge-intervals/description/
     *
     * https://www.youtube.com/watch?v=dzNIPX7HY6A&t=562s
     * */
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1){
            return intervals;
        }

        Arrays.sort(intervals,(a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);

        for(int i=1;i<intervals.length;i++){
            //this means it is overlapping
            if(intervals[i][0] <= newInterval[1]){
                newInterval[1]= Math.max(newInterval[1],intervals[i][1]);
            }else{
                newInterval = intervals[i];
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

}
