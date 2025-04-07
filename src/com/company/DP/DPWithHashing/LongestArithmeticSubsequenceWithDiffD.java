package com.company.DP.DPWithHashing;

import java.util.HashMap;

public class LongestArithmeticSubsequenceWithDiffD {
    public int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer,Integer> dp = new HashMap<>();
        int ans =0;

        for(int i=0;i<arr.length;i++){
            // the previous number such that by adding d to it , we would have got arr[i]
            int temp = arr[i] - difference;
            int tempAns = 0;
            // check answer exists for temp already or not
            if(dp.containsKey(temp)){
                tempAns = dp.get(temp);
            }

            //update ans for current
            dp.put(arr[i],1+tempAns);

            //ans update
            ans = Math.max(ans,dp.get(arr[i]));
        }

        return ans;
    }
}
