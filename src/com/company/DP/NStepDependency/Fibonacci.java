package com.company.DP.NStepDependency;

import java.util.Arrays;

public class Fibonacci {

    public int fibonacci(int n){
        int[] memo = new int[n+1];
        Arrays.fill(memo,-1);
        return fibonacci_top_down(n,memo);
    }

    //top-down : rec + memoization TC,SC = O(n) + O(n) (recursive stack + dp array)
    public int fibonacci_top_down(int i,int[] memo){
        if(i==0||i==1){
            return i;
        }

        if(memo[i]==-1){
            memo[i] = memo[i-1]+memo[i-2];
        }

        return memo[i];
    }

    //bottom-up : tabulation , TC - O(n) , SC - O(n) - only dp array space
    public int fibonacci_bottom_up(int n){
        int[] memo = new int[n+1];
        memo[0] = 0;
        memo[1] = 1;

        for(int i=2;i<=n;i++){
            memo[i] = memo[i-1] + memo[i-2];
        }

        return memo[n];
    }

    //Space optimized - TC - O(n), SC - O(1)
    public int fibonacci_space_opt(int n){
        int prev1=0;
        int prev2=1;

        if(n==0){
            return prev1;
        }

        for(int i=2;i<=n;i++){
            int curr = prev1+prev2;
            prev1 = prev2;
            prev2 = curr;
        }

        return prev2;
    }


}
