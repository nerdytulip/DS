package com.company.Greedy;

public class JumpGame {
    public boolean canJump(int[] nums) {
        int finalPosition = nums.length-1;

        for(int i = nums.length-2;i>=0;i--){
            //if you can reach the final position from this index
            // update the final position flag
            if(i + nums[i] >= finalPosition){
                finalPosition = i;
            }
        }

        return finalPosition == 0;
    }
}
