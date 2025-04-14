package com.company.BinarySearchAndApplications;

public class pairSumInSortedAndRotatedArray {
    static boolean pairsum_in_sorted_and_rotated_arr(int arr[],int n,int x){
        int i;
        for (i=0;i<n-1;i++)
            if (arr[i]>arr[i+1])
                break;

        int l=(i+1);
        int r=i;
        while (l!=r){
            if (arr[l]+arr[r]==x)
                return true;
            if (arr[l]+arr[r]<x)
                l=(l+1)%n;
            else
                r=(n+r-1)%n;
        }
        return false;
    }
}
