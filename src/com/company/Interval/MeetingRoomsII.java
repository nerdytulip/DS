package com.company.Interval;

import java.util.*;

public class MeetingRoomsII {

    /**
     * https://leetcode.com/problems/meeting-rooms-ii/
     * */
    public int minMeetingRooms_UsingMinHeap(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        //Add the end time of the first meeting
        minHeap.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0], end = intervals[i][1];

            // If the earliest‑ending room is free, reuse it
            if (start >= minHeap.peek()) {
                minHeap.poll();
            }

            // Allocate (or re‑allocate) a room until 'end'
            minHeap.add(end);
        }

        return minHeap.size();
    }

    public int minMeetingRooms(int[][] intervals) {
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();

        for(int i=0;i<intervals.length;i++){
            start.add(intervals[i][0]);
            end.add(intervals[i][1]);
        }

        start.sort((a,b) -> a - b);
        end.sort((a,b) -> a - b);

        int usedRooms = 0;
        int startIndex = 0;
        int endIndex = 0;

        while(startIndex < intervals.length){
            if(start.get(startIndex)<end.get(endIndex)){
                usedRooms++;
            }else{
                endIndex++;
            }
            // Always move to the next meeting's start
            startIndex++;
        }
        return usedRooms;
    }
}
