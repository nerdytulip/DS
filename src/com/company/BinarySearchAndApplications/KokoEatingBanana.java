package com.company.BinarySearchAndApplications;

import java.util.Arrays;

public class KokoEatingBanana {
    public int minEatingSpeed(int[] piles, int h) {
        int minSpeed = 1;
        int maxSpeed = Arrays.stream(piles).max().orElse(0);

        while(minSpeed < maxSpeed){
            int mid = minSpeed + (maxSpeed-minSpeed)/2;

            if(canEatInTime(piles,h,mid)){
                maxSpeed = mid;
            }else{
                minSpeed = mid+1;
            }
        }

        return minSpeed;
    }

    private boolean canEatInTime(int[] piles, int h, int speed) {
        int hours = 0;
        for(int pile:piles){
            hours+= (int) Math.ceil((double) pile /speed);
        }

        return hours<=h;
    }
}
