package com.company.DP.NStepDependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DeleteAndEarn {
    /**
     * https://leetcode.com/problems/delete-and-earn/editorial/?envType=study-plan-v2&envId=dynamic-programming
     */

    //top-down
    private int maxPoints_Mem(int num, HashMap<Integer, Integer> points, HashMap<Integer, Integer> mem) {
        if (num == 0) {
            return 0;
        }

        if (num == 1) {
            return points.getOrDefault(1, 0);
        }

        if (mem.containsKey(num)) {
            return mem.get(num);
        }

        int gain = points.getOrDefault(num, 0);
        int include = gain + maxPoints_Mem(num - 2, points, mem);
        int exclude = maxPoints_Mem(num - 1, points, mem);
        mem.put(num, Math.max(include, exclude));

        return mem.get(num);
    }

    public int deleteAndEarn_topDown(int[] nums) {
        int maxNumber = 0;
        HashMap<Integer, Integer> points = new HashMap<>();
        HashMap<Integer, Integer> mem = new HashMap<>();
        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
            maxNumber = Math.max(maxNumber, num);
        }

        return maxPoints_Mem(maxNumber, points, mem);
    }

    //bottom up
    private int maxPoints_BU(int maxNumber, HashMap<Integer, Integer> points, int[] maxPoints) {
        maxPoints[1] = points.getOrDefault(1, 0);

        for (int num = 2; num <= maxNumber; num++) {
            int gain = points.getOrDefault(num, 0);
            int include = gain + maxPoints[num - 2];
            int exclude = maxPoints[num - 1];

            maxPoints[num] = Math.max(include, exclude);
        }

        return maxPoints[maxNumber];
    }

    public int deleteAndEarn_BU(int[] nums) {
        int maxNumber = 0;
        HashMap<Integer, Integer> points = new HashMap<>();
        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
            maxNumber = Math.max(maxNumber, num);
        }

        int[] maxPoints = new int[maxNumber + 1];

        return maxPoints_BU(maxNumber, points, maxPoints);
    }

    //bottom up
    private int maxPoints_Optimized(int maxNumber, HashMap<Integer, Integer> points) {
        int prev2 = 0;
        int prev1 = points.getOrDefault(1, 0);

        for (int num = 2; num <= maxNumber; num++) {
            int gain = points.getOrDefault(num, 0);
            int include = gain + prev2;
            int exclude = prev1;

            int current = Math.max(include, exclude);

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public int deleteAndEarn_Optimized(int[] nums) {
        int maxNumber = 0;
        HashMap<Integer, Integer> points = new HashMap<>();
        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
            maxNumber = Math.max(maxNumber, num);
        }


        return maxPoints_Optimized(maxNumber, points);
    }

    // the above algorithm works when we have adjacent elements , i.e when elements mostly have difference of 1
    //but if they do not have difference of 1 , we do need to always the next element from points map

    public int deleteAndEarn(int[] nums) {
        HashMap<Integer, Integer> points = new HashMap<>();

        // Precompute how many points we gain from taking an element
        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
        }

        List<Integer> elements = new ArrayList<Integer>(points.keySet());
        Collections.sort(elements);

        // Base cases
        int prev2 = 0;
        int prev1 = points.get(elements.get(0));
        int current = prev1;

        for (int i = 1; i < elements.size(); i++) {
            int currentElement = elements.get(i);
            if (currentElement == elements.get(i - 1) + 1) {
                // The 2 elements are adjacent, cannot take both - apply normal recurrence
                current = Math.max(prev1, prev2 + points.get(currentElement));
            } else {
                // Otherwise, we don't need to worry about adjacent deletions
                current += points.get(currentElement);
            }

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}
