package com.company.Sorting;

import java.util.Arrays;

public class CountingSort {

    /**
     * TC - O(n) and SC - O(k)
     *
     * Idea - does not use comparisons, create a temp array ,
     * where we would store count of each values , like index 1 would store number of 1s
     * After this array is populated , you add the values to get a cumulative sum array
     *
     * and then we start iterating original array
     * and store element at the index that is in cummulative array for that number - 1
     * */

    static void countingSort(int[] arr)
    {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;

        // Auxiliary array to store counts of each element
        int[] count = new int[range];

        // Auxiliary array to store the resultant sorted array
        int[] output = new int[arr.length];

        // Store count of each element
        for (int el : arr) {
            count[el - min]++;
        }

        // Change count[i] so that count[i] now contains actual
        // position of this character in output array
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        // To make it stable we are operating in reverse order.
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }
}
