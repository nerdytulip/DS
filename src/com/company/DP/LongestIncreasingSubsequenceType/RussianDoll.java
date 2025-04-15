package com.company.DP.LongestIncreasingSubsequenceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RussianDoll {

    /**
     * https://leetcode.com/problems/russian-doll-envelopes/description/
     * */
    // DP with binary search - TC (nlogn)
    private int solveOptimal(int[][] envelopes){
        if(envelopes.length == 0){
            return 0;
        }

        //Sorting the envelopes by width (ascending) and height (descending for ties) helps structure the problem to focus only on heights for the next step.
        Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);

        List<Integer> ans = new ArrayList<>();
        ans.add(envelopes[0][1]); // Start with first height

        for(int i=1;i<envelopes.length;i++){
            int height = envelopes[i][1];
            if (height > ans.get(ans.size() - 1)) {
                // If current element is greater than the last element in ans
                ans.add(height);
            }else {
                // Find the index of the smallest element that is greater than or equal to a[i]
                // Find the index where a[i] can be inserted
                int index = lowerBound(ans, height);
                ans.set(index, height); // Replace the element
            }
        }

        return ans.size();
    }

    private static int lowerBound(List<Integer> list, int x) {
        int low = 0, high = list.size() - 1;
        int ans = list.size(); // Default answer points to the end (insertion at the end)

        while (low <= high) {
            int mid = low + (high - low) / 2; // Avoid integer overflow

            // Maybe an answer
            if (list.get(mid) >= x) {
                ans = mid;      // Possible answer found
                high = mid - 1; // Search on the left side for a smaller index
            } else {
                low = mid + 1;  // Search on the right side
            }
        }
        return ans; // Final position to insert or find the element
    }

    public int maxEnvelopes(int[][] envelopes) {
        return solveOptimal(envelopes);
    }
}
