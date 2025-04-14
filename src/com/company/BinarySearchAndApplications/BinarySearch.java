package com.company.BinarySearchAndApplications;

public class BinarySearch {

    //regular binary search
    static int binarysearch(int ar[],int l,int r,int key){
        int m;
        while(l<=r){
            m=l+(r-l)/2;
            if(ar[m]==key)
                return m;
            if(ar[m]<key)
                l=m+1;
            if (ar[m]>key)
                r=m-1;
        }
        return -1;
    }

    //recursive solution
    static int binarySearch_rec(int arr[],int l,int r,int key){
        if(l>r){
            return -1;
        }

        int mid = l + (r-l)/2;
        if(arr[mid] == key){
            return mid;
        }

        if(arr[mid] < key){
            return binarySearch_rec(arr,mid+1,r,key);
        }else{
            return binarySearch_rec(arr,0,mid-1,key);
        }
    }

    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args){
        int arr[] = {1,2,3,4,5,6,7};
        System.out.println(binarysearch(arr,0,arr.length-1,5));
        int a[] = {64,25,12,22,11};
        printArray(a);
    }
}
