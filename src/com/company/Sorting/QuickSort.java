package com.company.Sorting;

public class QuickSort {

    /**
     * why needed? This runs has average TC : O(nlogn)
     *
     * Idea - Divide and Conquer,
     *
     * steps :
     * 1) select a pivot element
     * 2) all the elements smaller than pivot go left
     * 3) all the elements larger than pivot go right
     *
     * */

    public void quickSort(int arr[], int begin,int end){
        if(begin<end){

            //find the partition
            int partition = findPartition(arr,begin,end);

            //do quick sort on left part
            quickSort(arr,begin,partition-1);

            //do quick sort on right part
            quickSort(arr,partition+1,end);
        }
    }

    private int findPartition(int arr[], int begin,int end){
        int pivot = arr[end];

        int i = begin-1;

        for(int j = begin;i<end;j++){
            if(arr[j]<pivot){
                i++;
            }

            swap(arr,i,j);
        }

        swap(arr,i+1,end);

        return i+1;
    }

    private void swap(int[] arr, int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = arr[i];
    }
}
