package com.company.Sorting;

public class BubbleSort {

    /**
     * When ? peforms well in a nearly sorted algorithm
     *
     * Idea :
     *  - the maximum element will bubble towards the end
     *  - idea - compare two adjacent element and if they are not sorted , they swap them
     * */

    // TC - O(n^2)
    public void bubbleSort(int arr[]){
        int n = arr.length;
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-1-i;j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] =temp;
                }
            }
        }
    }




}
