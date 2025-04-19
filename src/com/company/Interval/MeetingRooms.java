package com.company.Interval;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRooms {

    /**
     * https://leetcode.com/problems/meeting-rooms/
     * */

    public boolean canAttendMeetingsUsingCount(int[][] intervals) {
        if(intervals.length == 0){
            return true;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int count = 1;
        int ansEnd = intervals[0][1];

        for(int i =1;i<intervals.length;i++){
            if(intervals[i][0] >= ansEnd){
                count++;
                ansEnd = intervals[i][1];
            }
        }

        if(count != intervals.length){
            return false;
        }
        return true;
    }

    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals.length == 0){
            return true;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int count = 1;
        int ansEnd = intervals[0][1];

        for(int i =1;i<intervals.length;i++){
            if(intervals[i][0] < ansEnd){
                return false;
            }
            ansEnd = intervals[i][1];
        }

        return true;
    }
}
