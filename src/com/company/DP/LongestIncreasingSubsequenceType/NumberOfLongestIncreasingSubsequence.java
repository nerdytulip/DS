package com.company.DP.LongestIncreasingSubsequenceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS_DP(int[] nums) {
        if(nums.length == 0){
            return 0;
        }

        int n = nums.length;

        int[] length = new int[n];
        int[] count = new int[n];

        Arrays.fill(length,1);
        Arrays.fill(count,1);

        int maxLen = 1;

        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                // this means sequence is increasing
                if(nums[i]>nums[j]){
                    if(length[j]+1 > length[i]){
                        length[i] = length[j]+1;
                        count[i] = count[j];
                    }else if(length[j]+1 == length[i]){
                        count[i]+=count[j];
                    }
                }
            }
            maxLen = Math.max(maxLen,length[i]);
        }

        int result = 0;
        for(int i=0;i<n;i++){
            if(length[i] == maxLen){
                result+=count[i];
            }
        }

        return result;
    }
}
