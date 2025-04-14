package com.company.BinarySearchAndApplications;

import java.util.Arrays;

public class SuccessfulPairsOfSpellsAndPotions {

    /**
     * potions = n and spells = m and  success integer
     *
     * potions[i] = strength of potion
     * spells[j = strength of spell
     *
     * potion and spell pair => successful => potion[i]*spell[j] >= success
     *  => ratio = potion[i] = success/spell[j]
     *
     * return array of pairs of length n ,
     * where pair[i] = number of potions that will form successful pair with ith spell
     *
     * find index in potions ,where potion[index]>= ratio : i.e lower bound.
     *
     * */

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] results = new int[spells.length];

        Arrays.sort(potions);

        for(int i =0;i< spells.length;i++){
//            long potionRatio = (long) Math.ceil((double) (success) /spells[i]);
            long potionRatio = (success + spells[i] - 1) / spells[i];

            int indexGreaterThanRatio = lowerBound(potions,potions.length,potionRatio);
            results[i] = potions.length - indexGreaterThanRatio;
        }

        return results;
    }

    public static int lowerBound(int []arr, int n, long x) {
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer - this if condition is lower bound condition
            if (arr[mid] >= x) {
                ans = mid;
                //look for smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }


}
