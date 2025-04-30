package com.company.TwoPointer;

public class TrappingRainWater_VolumeOfHistogram {
    public int trap_WithExtraSpace(int[] height) {

        // get left max
        int[] leftMaxes = new int[height.length];
        int leftMax = height[0];
        for(int i=0;i< height.length;i++){
            leftMax = Math.max(leftMax,height[i]);
            leftMaxes[i] = leftMax;
        }

        int sum = 0;

        // get right max
        int righMax = height[height.length-1];
        for(int i= height.length-1;i>0;i--){
            righMax = Math.max(righMax,height[i]);
            int secondTallest = Math.min(righMax,leftMaxes[i]);

            if(secondTallest>height[i]){
                sum+=secondTallest-height[i];
            }
        }

        return sum;
    }

    // with out extra space
    public int trap(int[] height){
        int l = 0;
        int r = height.length-1;
        int leftMax = height[l];
        int rightMax = height[r];
        int res = 0;

        while(l<r){
            if(leftMax<rightMax){
                l+=1;
                leftMax = Math.max(leftMax,height[l]);
                res += leftMax - height[l];
            }else{
                r-=1;
                rightMax = Math.max(rightMax,height[r]);
                res += rightMax - height[r];
            }
        }

        return res;
    }
}
