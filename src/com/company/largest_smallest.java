package com.company;

import java.util.*;

public class largest_smallest {
    //O(n)
    static int Largest(int arr[],int n){
     Arrays.sort(arr);
     return arr[n-1];
    }
    //O(n)
    static int getMin(int arr[], int n)
    {
        int res = arr[0];

        for (int i = 1; i < n; i++)
            res = Math.min(res, arr[i]);
        return res;
    }

    static int getMax(int arr[], int n)
    {
        int res = arr[0];

        for (int i = 1; i < n; i++)
            res = Math.max(res, arr[i]);
        return res;
    }
    /*
    * @https://www.geeksforgeeks.org/largest-element-in-the-array-that-is-repeated-exactly-k-times/
    */
    static void solveFork(int arr[],int n,int k){
        HashMap<Integer,Integer> m = new HashMap<>();
        int max=Integer.MIN_VALUE;
        for(int i =0;i<n;i++){
            if(!m.containsKey(arr[i]))
                m.put(arr[i],0);
            m.put(arr[i],m.get(arr[i])+1);}
            for(int  i=0;i<n;i++){
                if(m.get(arr[i])==k && max==Integer.MIN_VALUE)
                {max=arr[i];}
                else if(m.get(arr[i])==k && max<arr[i]){
                    max = arr[i];
                }
            }
        if (max==Integer.MIN_VALUE)
            System.out.println("No such element");
        else
            System.out.println(max);

    }
    static HashMap<String,Integer> largest3(int arr[],int n){
        int first,second,third;
         if(n<3){
             System.out.println("invalid input"); }
         third=second=first=Integer.MIN_VALUE;
         for(int i=0;i<n;i++){
             if(arr[i]>first){
                 third=second;
                 second=first;
                 first=arr[i];
             }
             else if(arr[i]>second){
                 third=second;
                 second=arr[i];
             }
             else if(arr[i]>third){
                 third=arr[i];
             }
         }
         HashMap<String,Integer> h =new HashMap<>();
         h.put("1",first);
         h.put("2",second);
         h.put("3",third);
     return h;
    }
    /*
    * @https://www.geeksforgeeks.org/find-second-largest-element-array/*/
    static int SecondLargest(int arr[],int n){
        int i,first,second;
        if(n<2){
            System.out.println("invalid input");
        }
        first=second=Integer.MIN_VALUE;
        for(i=0;i<n;i++){
            if(arr[i]>first){
                second=first;
                first=arr[i];
            }
            else if(arr[i]>second && arr[i]!=first)
                second=arr[i];
        }
        if(second==Integer.MIN_VALUE)
            System.out.println("there is no second largest element");
        else
            System.out.println("second largest element"+second);
        return second;
    }

    /*Largest element in array when array elements are repeated*/
    static int largestEleInRepeat(int arr[],int n){
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> l = new ArrayList<>();

        for(int i=0;i<n;i++){
            set.add(arr[i]);
        }
        int temp[]= new int[set.size()];
        int i=0;
        for(Integer el : set)
            temp[i++]=el;
        //return getMax(temp,set.size());
        return Largest(temp,set.size());
    }

    /*https://www.geeksforgeeks.org/find-the-largest-pair-sum-in-an-unsorted-array/*/
    static int largestpairsum_without_sorting(int arr[],int n){
        int first,second;
        first=Math.max(arr[0],arr[1]);
        second=Math.min(arr[0],arr[1]);
        for(int i=2;i<n;i++){
            if(arr[i]>first){
                second=first;
                first=arr[i];
            }
            else if(arr[i]>second && arr[i]!=first){
                second=arr[i];
            }
        }
        return (first+second);
    }
/*https://www.programcreek.com/2014/05/leetcode-kth-largest-element-in-an-array-java/
other than the below method the initial method of sorting an array using
any sorting algo is also valid and has a time complexity of nlogn
The below method has a time complexity of nlogk with extra space complexity O(k)*/
//priority queue is by default min heap
/**/
    static int findKthLargest(int arr[],int k){
        PriorityQueue<Integer> q = new PriorityQueue<>(k);
        for(int i: arr){
            q.offer(i);//same as add method
            //q.add(i);
            if(q.size()>k){
                q.poll();//This method retreives the data and then removes it ,Peek only retrieves the data
            }
        }
        return q.peek();
    }
    //above solution is implemented using minheap as priority queue is by default min heap;i.e min element at top
    //below solution is of TC-O(n+klogn)
    /*1) Build a Max Heap tree in O(n)
      2) Use Extract Max k times to get k maximum elements
      from the Max Heap O(klogn)*/
    /*https://www.techiedelight.com/find-kth-largest-element-array/*/
    static int find_Kth_largestelement(int arr[],int k){
     PriorityQueue<Integer> maxheap = new PriorityQueue<>(10, new Comparator<Integer>() {
         @Override
         public int compare(Integer a, Integer b) {
             return b-a;
         }
     });
     for (int i=0;i<arr.length;i++){
         maxheap.add(arr[i]);
     }
     //pop exactly k-1 times
     while (--k>0){
         maxheap.poll();
     }
     return maxheap.peek();
    }

    static void findKthSmallest(int arr[],int k){
     PriorityQueue<Integer> minheap = new PriorityQueue<>(new Comparator<Integer>() {
         @Override
         public int compare(Integer o1, Integer o2) {
             return o1-o2;
         }
     });
     for(int i: arr){
         minheap.offer(i);
         if(minheap.size()<k){
             int temp=minheap.poll();
             System.out.println(temp);
         }
     }
     System.out.println(minheap.peek());
     //return minheap.peek();
    }

    //Below solution is of complexity TC-O(n+klogn)
    /*@https://www.techiedelight.com/find-kth-smallest-element-array/*/
    static int find_Kth_smallestelement(int arr[],int k){
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        for (int i=0;i<arr.length;i++){
            minheap.add(arr[i]);
        }
        while (--k>0){
            minheap.poll();
        }
        return minheap.peek();
    }

    /*https://www.geeksforgeeks.org/given-an-array-of-numbers-arrange-the-numbers-to-form-the-biggest-number/*/
    static void printlargest(ArrayList<String> a){
     Collections.sort(a, new Comparator<String>() {
         @Override
         public int compare(String X, String Y) {
             String XY =X+Y;
             String YX=Y+X;
             return XY.compareTo(YX)>0?-1:1;
         }
     });
     Iterator i = a.iterator();
     while(i.hasNext())
         System.out.print(i.next());
    }
    static public void main (String[] args)
    {
        int []arr = {10, 324, 45,
                90, 9808};
        int n = arr.length;
        System.out.println("kth largest element"+" "+find_Kth_largestelement(arr,1));
        System.out.println("kth smallest element"+find_Kth_smallestelement(arr,2));
        /*System.out.println(Largest(arr, n));
        System.out.println( "Minimum element"
                + " of array: " + getMin(arr, n));
        System.out.println( "Maximum element"
                + " of array: " + getMax(arr, n));
        solveFork(arr,n,4);*/
        /*int arr1[]={10, 5, 15, 5, 15, 10, 1, 1};
        int n1 = arr1.length;
        System.out.println("largest\t"+largestEleInRepeat(arr1,n1));
        System.out.println(largest3(arr,n));
        System.out.println(findKthLargest(arr,2));*/
        //System.out.println(findKthSmallest(arr,2));
       // findKthSmallest(arr,2);
        ArrayList<String> a = new ArrayList<>();
        a.add("54");
        a.add("546");
        a.add("548");
        a.add("60");
        printlargest(a);
    }
}
