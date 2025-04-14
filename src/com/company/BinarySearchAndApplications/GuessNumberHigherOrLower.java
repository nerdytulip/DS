package com.company.BinarySearchAndApplications;

public class GuessNumberHigherOrLower {
    //mock guess
    private int guess(int num){
        return -1;
    }

    public int guessNumber(int n) {
        int l = 1;
        int r = n;

        while(l<=r){
            int mid =  l + (r - l)/2;
            int res = guess(mid);

            if(res == 0){
                return mid;
            }else if(res < 0){
                r = mid -1;
            }else{
                l = mid+1;
            }
        }

        return -1;
    }
}
