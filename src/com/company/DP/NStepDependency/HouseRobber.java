package com.company.DP.NStepDependency;

import java.util.ArrayList;

public class HouseRobber {

    /**
     * SAME as sum non adjacent
     * https://leetcode.com/problems/house-robber/
     * */
    int maximumNonAdjacentSum_space_optimized(ArrayList<Integer> nums){
        int n = nums.size();
        int prev2 =0 ; //for negative index it will be 0 , i.e for i-2
        int prev1 = nums.get(0); //i-1

        for(int i=1;i<n;i++){
            int incl = prev2 + nums.get(i);
            int excl = prev1;
            int ans = Math.max(incl,excl);
            prev2 = prev1;
            prev1 = ans;
        }

        return prev1;
    }

    public int rob(int[] nums) {
        ArrayList<Integer> arr = new ArrayList<>();
        // Convert int[] to ArrayList<Integer>
        for (int num : nums) {
            arr.add(num); // Add each int to ArrayList
        }
        int ans = maximumNonAdjacentSum_space_optimized(arr);
        return ans;
    }
}
