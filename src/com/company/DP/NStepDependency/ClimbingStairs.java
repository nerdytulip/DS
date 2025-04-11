package com.company.DP.NStepDependency;

public class ClimbingStairs {

    //recursion
    public int climbStairs(int nStairs) {
        if(nStairs<0){
            return 0;
        }

        if(nStairs==0){
            return 1;
        }

        return climbStairs(nStairs-1) + climbStairs(nStairs-2);


    }

    public int climbStairs_Bottom_up(int nStairs){
        int[] memo = new int[nStairs+1];
        memo[0] = 1;
        memo[1] = 1;

        for(int i =2;i<=nStairs;i++){
            memo[i] = memo[i-1]+memo[i-2];
        }

        return memo[nStairs];
    }

    public int climbStairs_opt(int nStairs){
        int[] memo = new int[nStairs+1];
        int prev1 = 1;
        int prev2 = 1;
        int numStairs = 0;


        for(int i =2;i<=nStairs;i++){
            numStairs = prev1+prev2;
            prev1 = prev2;
            prev2=numStairs;
        }

        return numStairs;
    }

    public static void main(String[] args){

        ClimbingStairs climbingStairs = new ClimbingStairs();

        System.out.println(climbingStairs.climbStairs(5));
        System.out.println("---------------------");
        System.out.println(climbingStairs.climbStairs_Bottom_up(5));
        System.out.println("---------------------");
        System.out.println(climbingStairs.climbStairs_opt(5));
    }

}
