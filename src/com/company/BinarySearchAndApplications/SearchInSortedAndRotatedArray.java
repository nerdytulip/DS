package com.company.BinarySearchAndApplications;

public class SearchInSortedAndRotatedArray {
    static int search_in_sorted_and_rotated_arr_recursion(int arr[], int l, int h, int key){
        if(l>h)
            return -1;
        int mid =(l+h)/2;
        if (arr[mid]==key)
            return mid;
        if (arr[l]<=arr[mid]){
            if(key>=arr[l] && key<=arr[mid]){
                return search_in_sorted_and_rotated_arr_recursion(arr,l,mid-1,key);
            }else{
                return search_in_sorted_and_rotated_arr_recursion(arr,mid+1,h,key);
            }
        }else{
            if (key>=arr[mid] && key<=arr[h]){
                return search_in_sorted_and_rotated_arr_recursion(arr,mid+1,h,key);
            }else{
                return search_in_sorted_and_rotated_arr_recursion(arr,l,mid-1,key);
            }
        }
    }

    static int search_in_sorted_and_rotated_arr_iterative(int a[],int key){
        if (a==null || a.length==0)
            return -1;
        int start=0;
        int end=a.length-1;
        while (start<=end){
            int mid=(start+end)/2;
            if (key==a[mid]){
                return mid;}
            if (a[start]<=a[mid]){
                if (a[start]<=key && key<=a[mid]){
                    end=mid-1;
                }else{
                    start=mid+1;
                }
            }
            else{
                if (a[mid]<=key && key<=a[end]){
                    start=mid+1;
                }
                else{
                    end=mid-1;
                }
            }
        }
        return -1;
    }
}
