package com.company.DP.OneTwoStepDependency;

import java.util.ArrayList;
import java.util.Arrays;

public class MaximumSumNonAdjacentElements {

    /**
     * https://gkgaurav31.github.io/posts/maximum-sum-such-that-no-two-elements-are-adjacent/
     * */
    int maximumNonAdjacentSum(ArrayList<Integer> nums){
        int n = nums.size();
        int ans = solve(nums,n-1);
        return ans;
    }

    private int solve(ArrayList<Integer> nums, int n) {
        if(n<0){
            return 0;
        }

        if(n == 0){
            return nums.get(0);
        }

        int incl = solve(nums,n-2) + nums.get(n);
        int excl = solve(nums,n-1);

        return Math.max(incl,excl);
    }

    int maximumNonAdjacentSum_top_down(ArrayList<Integer> nums){
        int n = nums.size();
        int memo[] = new int[n];
        Arrays.fill(memo,-1);
        int ans = solveMem(nums,n-1,memo);
        return ans;
    }

    private int solveMem(ArrayList<Integer> nums, int n,int memo[]) {
        if(n<0){
            return 0;
        }

        if(n == 0){
            return nums.get(0);
        }

        if(memo[n]!=-1){
            return memo[n];
        }

        int incl = solve(nums,n-2) + nums.get(n);
        int excl = solve(nums,n-1);

        memo[n] = Math.max(incl,excl);
        return memo[n];
    }

    int maximumNonAdjacentSum_bottom_up(ArrayList<Integer> nums){
        int n = nums.size();
        int memo[] = new int[n];
        Arrays.fill(memo,-1);
        memo[0] = nums.get(0);
        memo[1] = nums.get(1);

        for(int i=2;i<n;i++){
            int incl = memo[i-2] + nums.get(i);
            int excl = memo[i-1];
            memo[i] = Math.max(incl,excl);
        }

        return memo[n-1];
    }

    int maximumNonAdjacentSum_space_optimized(ArrayList<Integer> nums){
        int n = nums.size();
        int prev2 =0 ; //for negative indec it will be 0 , i.e for i-1
        int prev1 = nums.get(0);

        for(int i=1;i<n;i++){
            int incl = prev2 + nums.get(i);
            int excl = prev1;
            int ans = Math.max(incl,excl);
            prev2 = prev1;
            prev1 = ans;
        }

        return prev1;
    }

    public static void main(String[] args){
        MaximumSumNonAdjacentElements maximumSumNonAdjacentElements = new MaximumSumNonAdjacentElements();
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(2,1,4,9));
//        int ans = maximumSumNonAdjacentElements.maximumNonAdjacentSum(arr);
        int ans = maximumSumNonAdjacentElements.maximumNonAdjacentSum_bottom_up(arr);

        System.out.println(ans);
    }
}
