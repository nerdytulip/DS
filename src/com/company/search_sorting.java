package com.company;
//has search algos
public class search_sorting {
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

    //selection sort - O(n^2)
    static void selectionsort(int arr[])
    {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
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
        selectionsort(a);
        printArray(a);
    }
}

