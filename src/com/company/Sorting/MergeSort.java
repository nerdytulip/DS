package com.company.Sorting;

public class MergeSort {

    /**
     * why needed? This runs in O(nlogn) worst case
     *
     * Idea - Divide and Conquer
     *
     * */

    public void mergeSort(int[] arr, int numberOfElements){
        if(numberOfElements<2){
            return;
        }

        int mid = numberOfElements/2;

        int[] leftArr = new int[mid];
        int[] rightArr = new int[numberOfElements-mid];

        for(int i=0;i<mid;i++){
            leftArr[i] = arr[i];
        }

        for(int i=mid;i<numberOfElements;i++){
            rightArr[i-mid] = arr[i];
        }

        //apply mergesort to left array
        mergeSort(leftArr,mid);

        //apply merge on right array
        mergeSort(rightArr,numberOfElements-mid);

        //merge
        merge(arr,leftArr,rightArr,mid,numberOfElements-mid);
    }

    private void merge(int[] arr, int[] leftArr, int[] rightArr, int left, int right){
        int i =0,j=0,k=0;

        while(i<left && j<right){
            if(leftArr[i]<rightArr[j]){
                    arr[k++] = leftArr[i++];
            }else{
                arr[k++] = rightArr[j++];
            }
        }

        while(i<left){
            arr[k++] = leftArr[i++];
        }

        while(j<left){
            arr[k++] = leftArr[j++];
        }

    }
}
