package com.company.Greedy;

public class SurviveOnIsland {
    //https://www.geeksforgeeks.org/survival/
    static int minimumDays(int S, int N, int M){
        //N max food per day
        //S no. of days you are required to survive
        //M unit of food required each day to survive

        // If the daily food requirement is greater than what can be bought per day, survival is impossible
        if (M > N) return -1;

        int sunday = S / 7;  // Count Sundays when food cannot be bought
        int buyingDays = S - sunday;  // Total available days to buy food
        int totalFood = S * M;  // Total food required

        // Key Fix: Ensure enough food can be bought within available days
        if (buyingDays * N < totalFood) return -1;

        int ans = 0;
        if(totalFood%N == 0){
            ans = totalFood/N;
        }else{
            ans = totalFood/N + 1;
        }

        if(ans<=buyingDays){
            return ans;
        }else{
            return -1;
        }
    }

}
