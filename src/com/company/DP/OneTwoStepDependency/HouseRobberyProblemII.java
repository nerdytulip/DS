package com.company.DP.OneTwoStepDependency;

import java.util.ArrayList;

public class HouseRobberyProblemII {

    /**
     * https://leetcode.com/problems/house-robber-ii/description/
     * */
    long maximumNonAdjacentSum_space_optimized(ArrayList<Integer> nums){
        int n = nums.size();
        long prev2 =0 ; //for negative indec it will be 0 , i.e for i-2
        long prev1 = nums.get(0); //i-1

        for(int i=1;i<n;i++){
            long incl = prev2 + nums.get(i);
            long excl = prev1;
            long ans = Math.max(incl,excl);
            prev2 = prev1;
            prev1 = ans;
        }

        return prev1;
    }

    public long houseRobber(int[] valueInHouse) {
        int n = valueInHouse.length;
        if(n == 1){
            return valueInHouse[0];
        }

        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> second = new ArrayList<>();


        for(int i=0;i<n;i++){
            if(i!=n-1){
               first.add(valueInHouse[i]);
            }
            if( i!=0){
                second.add(valueInHouse[i]);
            }
        }

        return Math.max(maximumNonAdjacentSum_space_optimized(first),maximumNonAdjacentSum_space_optimized(second));
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,1};
        HouseRobberyProblemII robberyProblem = new HouseRobberyProblemII();
        System.out.println(robberyProblem.houseRobber(arr));

    }
}
