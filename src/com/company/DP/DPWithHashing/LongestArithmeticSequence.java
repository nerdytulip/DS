package com.company.DP.DPWithHashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestArithmeticSequence {
    private int solveMem(int index, int diff, int[] nums, List<Map<Integer, Integer>> dp) {
        if(index < 0){
            return 0;
        }

        if (dp.get(index).containsKey(diff)){
            return dp.get(index).get(diff);
        }

        int ans = 0;
        for(int j=index-1;j>=0;j--){
            if(nums[index] - nums[j] == diff){
                ans = Math.max(ans, 1 + solveMem(j, diff,nums,dp));
            }
        }

        dp.get(index).put(diff, ans);
        return dp.get(index).get(diff);
    }

    public int longestArithSeqLengthMem(int[] nums) {

        if(nums.length <= 2){
            return nums.length;
        }

        int ans = 0;

        List<Map<Integer, Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            dp.add(new HashMap<>()); // Initialize each map
        }

        for(int i=0;i< nums.length;i++){
            for(int j=i+1;j< nums.length;j++){
                // adding two - because we are including the two elements chosen as part of this loop
                ans = Math.max(ans, 2 + solveMem(i,nums[j] - nums[i],nums,dp));
            }
        }

        return ans;
    }

    //tab
    public int longestArithSeqLength(int[] nums) {

        if(nums.length <= 2){
            return nums.length;
        }

        int ans = 0;

        List<Map<Integer, Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            dp.add(new HashMap<>()); // Initialize each map
        }

        //i from 1 , because if it is we start from 0 , that means both of them would be pointing to 0 at the same time , which is not right
        for(int i=1;i< nums.length;i++){
            for(int j=0;j<i;j++){
                int diff = nums[i] - nums[j];
                //because we are atleast including ith element for now - elements in ap right now
                int count = 1;

                // check if ans already present
                if(dp.get(j).containsKey(diff)){
                    count = dp.get(j).get(diff);
                }

                dp.get(i).put(diff, 1 + count);
                ans = Math.max(ans,dp.get(i).get(diff));
            }
        }

        return ans;
    }
}
