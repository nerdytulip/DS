package com.company.Interval;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomsIII {
    /**
     * https://leetcode.com/problems/meeting-rooms-iii/description/
     * */

    public int mostBooked(int n, int[][] meetings) {
        if(meetings.length == 0){
            return 0;
        }

        int[] meetingCount = new int[n];

        // unused_rooms representing available rooms sorted by room number,
        PriorityQueue<Integer> unusedRooms = new PriorityQueue<>();

        // used_rooms storing rooms in use along with the time they become available again
        // This priority queue is ordered in ascending order based on both room_availability_time and room_number
        PriorityQueue<long[]> usedRooms = new PriorityQueue<long[]>((a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1]));

        for (int i = 0; i < n; i++) {
            unusedRooms.offer(i);
        }

        Arrays.sort(meetings, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1];

            while (!usedRooms.isEmpty() && usedRooms.peek()[0] <= start) {
                int room = (int) usedRooms.poll()[1];
                unusedRooms.offer(room);
            }

            if (!unusedRooms.isEmpty()) {
                int room = unusedRooms.poll();
                usedRooms.offer(new long[]{end, room});
                meetingCount[room]++;
            } else {
                long roomAvailabilityTime = usedRooms.peek()[0];
                int room = (int) usedRooms.poll()[1];
                usedRooms.offer(new long[]{roomAvailabilityTime + end - start, room});
                meetingCount[room]++;
            }
        }

        int maxMeetingCount = 0, maxMeetingCountRoom = 0;

        for (int i = 0; i < n; i++) {
            if (meetingCount[i] > maxMeetingCount) {
                maxMeetingCount = meetingCount[i];
                maxMeetingCountRoom = i;
            }
        }

        return maxMeetingCountRoom;

    }
}
