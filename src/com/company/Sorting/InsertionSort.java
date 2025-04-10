package com.company.Sorting;

public class InsertionSort {

    /**
     * TC - O(n^2) - worst / O(n) - best
     * */
    public void insertionSort(int arr[]){
        int n = arr.length;

        //starting from 1 because first element is considered sorted
        for(int i = 1;i<n;i++){
            int key = arr[i];
            int j = i-1;

            while(j>=0 && arr[j]>key){
                arr[j+1] = arr[j];
                j = j-1;
            }

            arr[j+1] = key;
        }
    }


}
