package com.company.Interval;

import java.util.Arrays;

public class MinimumBurstBalloonsIntervals {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;

        Arrays.sort(points, (a,b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int currentEnd = points[0][1];

        for(int i =1;i< points.length;i++){
            // Need a new arrow if start is beyond the reach of the current one
            if(points[i][0] > currentEnd){
                arrows++;
                currentEnd = points[i][1];
            }
        }
        return arrows;
    }
}
