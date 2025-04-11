package com.company.DP.NStepDependency;

import java.util.Arrays;

public class PaintingFence {

    /* https://leetcode.com/problems/paint-fence/description/*/

    int solveRec(int n,int k){
        if(n==1){
            return k;
        }

        if(n==2){
            return k + k*(k-1);
        }

        int ans = solveRec(n-2,k) *(k-1) + solveRec(n-1,k) * (k-1);
        return ans;
    }

    int numberOfWaysRec(int n,int k){
       return solveRec(n,k);
    }

    //top-down
    int solveMem(int n,int k, int[] memo){
        if(n==1){
            return k;
        }

        if(n==2){
            return k + k*(k-1);
        }

        if(memo[n]!=-1){
            return memo[n];
        }

        memo[n] = solveMem(n-2,k,memo) *(k-1) + solveMem(n-1,k,memo) * (k-1);
        return memo[n];
    }

    int numberOfWaysMemo(int n,int k){
        int[] memo = new int[n+1];
        Arrays.fill(memo,-1);
        return solveMem(n,k,memo);
    }

    //bottom -up
    int numberOfWaysTabulation(int n,int k){
        int[] memo = new int[n+1];
        Arrays.fill(memo,-1);

        memo[0] = 0;
        memo[1] = k;
        memo[2] = k + (k *(k-1));

        for(int i=3;i<=n;i++){
            memo[i] = memo[i-2] * (k-1) + memo[i-1] * (k-1);
        }

        return memo[n];
    }

    //bottom -up
    int numberOfWaysSpaceOptimized(int n,int k){
        int prev1 = k;
        int prev2 = k + (k *(k-1));
        int ans = 0;

        for(int i=3;i<=n;i++){
            ans = prev2 * (k-1) + prev1 * (k-1);
            prev2 = prev1;
            prev1 = ans;
        }

        return prev1;
    }

    public static void main(String[] args) {
        PaintingFence paintingFence = new PaintingFence();
        System.out.println(paintingFence.numberOfWaysSpaceOptimized(3,3));
    }
}
