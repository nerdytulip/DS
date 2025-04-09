package com.company;
//has search algos
public class BinarySearchAndApplications {
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
    static int binarySearch_rec(int arr[],int s,int e,int key){
        if(s>e){
            return -1;
        }

        int mid = s + (e-s)/2;
        if(arr[mid] == key){
            return mid;
        }

        if(arr[mid] < key){
            return binarySearch_rec(arr,mid+1,e,key);
        }else{
            return binarySearch_rec(arr,0,mid-1,key);
        }
    }

    public static int lowerBound(int []arr, int n, int x) {
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

    public static int upperBound(int[] arr, int x, int n) {
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer - this if condition is upper bound condition
            if (arr[mid] > x) {
                ans = mid;
                //look for smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }

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

