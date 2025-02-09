package com.company.Greedy;

import java.util.ArrayList;
import java.util.Arrays;

public class ShopInCandy {

    //https://www.geeksforgeeks.org/problems/shop-in-candy-store1145/1
    static ArrayList<Integer> candyStore(int candies[], int N, int K){
        // min cost to buy all candies - buy cheap candies and take pricey candy for free
        //max cost to buy all candies - buy pricey candies and take cheap candies for free

        Arrays.sort(candies);

        int mini = 0;
        int buy = 0;
        int free = N-1;

        while(buy<=free){
            mini = mini + candies[buy];
            buy++;
            free = free - K;
        }

        int maxi = 0;
        buy = N -1;
        free = 0;

        while(free<=buy){
            maxi = maxi + candies[buy];
            buy--;
            free = free + K;
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(mini);
        ans.add(maxi);

        return ans;
    }
}
