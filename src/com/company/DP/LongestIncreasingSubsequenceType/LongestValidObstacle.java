package com.company.DP.LongestIncreasingSubsequenceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestValidObstacle {

    /**
     * https://leetcode.com/problems/find-the-longest-valid-obstacle-course-at-each-position/description/?envType=study-plan-v2&envId=dynamic-programming
     */

    public int[] longestObstacleCourseAtEachPosition_DP(int[] obstacles) {
        int n = obstacles.length;
        int[] result = new int[n];
        Arrays.fill(result, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (obstacles[j] <= obstacles[i]) {
                    result[i] = Math.max(result[i], result[j] + 1);
                }
            }
        }

        return result;
    }

    // greedy
    // LIS[i] = x , subsequence length : i+1(0 based indexing), x : longest increasing subsequence ending at x
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        List<Integer> LIS = new ArrayList<>();
        int[] result = new int[n];

        for(int i=0;i<n;i++){
            int obstacle = obstacles[i];

            int idx = upperBound(LIS,obstacle);

            if(idx == LIS.size()){
                LIS.add(obstacle);
            }else{
                LIS.set(idx,obstacle);
            }
            result[i] = idx+1;
        }
        return result;
    }

    public static int upperBound(List<Integer> LIS, int x) {
        int n = LIS.size();
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer - this if condition is upper bound condition
            if (LIS.get(mid) > x) {
                ans = mid;
                //look for smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }
}