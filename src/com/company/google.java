package com.company;

import java.util.*;

public class google {
    //#1 frequency of a number in array
    //there can be following cases
    //array is sorted or unsorted
    /*if the array is not sorted we will have following options to solve the question
    * 1.linearly traverse the array-TC-O(n) and SC-O(1)
    * 2.binary search and find the first and last occurence of that number
    * int the array and the count we require will be j-i+1
    *
    * for unsorted array ,we can have following approaches
    * 1.two nested for loops(one for picking and other for iterating)-TC-O(n*n) ,SC-O(n)
    * 2.first sorting and the linear search-O(nlogn+n)
    * 3.using hashmap-O(1)
    * to get the entries in ascending order we can use hashmap with sortedmap
    *
    * the efficient methods are implemented below*/

    //find the first and last occurence of a number
    // using binary search TC-O(logn)
    static int Count_Sorted_Using_Binarysearch(int arr[],int x,int n){
        int i,j;
        i=first(arr,0,n-1,x,n);
        if(i==-1)
            return i;
        j=last(arr,i,n-1,x,n);
        return j-i+1;
    }
    //returns first index of occurence of number
    static int first(int arr[],int low,int high,int x,int n){
        if(high>=low){
            int mid=(low+high)/2;
            if((mid==0 || x>arr[mid-1]) && arr[mid]==x)
                return mid;
            else if(x>arr[mid])
                return first(arr,(mid+1),high,x,n);
            else
                return first(arr,low,(mid-1),x,n);
        }
        return -1;
    }
    static int last(int arr[],int low,int high,int x,int n){
        if (high>=low){
            int mid=(low+high)/2;
            if ((mid==n-1 || x<arr[mid+1]) && arr[mid]==x)
                return mid;
            else if (x<arr[mid])
                return last(arr,low,(mid-1),x,n);
            else
                return last(arr,(mid+1),high,x,n);
        }
        return -1;
    }

    //In this we are given an unsorted array ,the most efficient solution
    //will be using hashmap-insertion in hashmap-(O(n)) and we answer query in O(1)
    static int count_Freq_in_Unsorted_array(int a[],int n,int x){
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int i=0;i<n;i++){
            if(!hm.containsKey(a[i]))
                hm.put(a[i],1);
            else
                hm.put(a[i],hm.get(a[i])+1);
        }

        if(hm.containsKey(x))
            return hm.get(x);
        return 0;
    }
    //if we want to get the unosorted array sorted as well as we the to return
    //the frequency efficiently,we use sortedmap with hashmap,insertion in
    //sortedmap-O(nlogn)
    //TC-O(nlogn)
    static void countFreq_of_unosorted_by_using_sortedmap(int a[],int n){
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int i=0;i<n;i++)
            hm.put(a[i],hm.get(a[i])==null?1:hm.get(a[i])+1);
        SortedMap<Integer,Integer> st = new TreeMap<>();
        for(HashMap.Entry<Integer,Integer> s:hm.entrySet()){
            st.put(s.getKey(),s.getValue());
        }
        int cumul=0;
        for(SortedMap.Entry<Integer,Integer> x:st.entrySet()){
            cumul+=x.getValue();
            System.out.println(x.getKey()+" "+cumul);
        }
    }

    //If we are given an unsorted array and we are asked to return the keys and number
    //in the order of occurence of the number in the array,then our approach
    //will be to first insert the element in the map and then traverse the array
    //and if that digit is not previously visited only then visit it.
    //to check if an element is visited or not we initialize the value of that
    //element(key) as 0
    //static countFreq_and_return_in_the_sequence_in_which_elements_originally_appear
    static void countFreq_by_the_sequence(int a[],int n){
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int i=0;i<n;i++)
            hm.put(a[i],hm.get(a[i])==null?1:hm.get(a[i])+1);
        for (int i=0;i<n;i++){
            if (hm.get(a[i])!=0){
                System.out.println(a[i]+" "+hm.get(a[i]));
                hm.put(a[i],0);
            }
        }
    }
    /*So,our question is to basically calculate the frequency of each
    * elements in an array using TC-O(n) and SC-O(1)
    * To achieve this approach we basicaly follow the below steps
    * 1.we use element as index
    * 2.Store the count od the elements at the above index
    * we store counts as negative and to deal with loss of current number at the index
    * where we want to store the number we store it at the ith current index
    * where we are*/
    static void countFreq_using_constant_extra_space(int a[],int n){
        int i=0;
        while(i<n){
            //if the element is already processed ,then do nothing
            if (a[i]<=0){
                i++;
                continue;
            }
            int elementIndex=a[i]-1;
            //if the element has not been already processed,so then
            //store that element at current index i and at index=elementIndex
            //store the count as -1 as we are first encountering it
            if (a[elementIndex]>0){
                a[i]=a[elementIndex];
                a[elementIndex]=-1;
            }
            else {
                //if this is not the first occurence of element in the array
                //then increase the count,i.e here the decrease it because we
                //are dealing with negative number here
                a[elementIndex]--;
                //initialize a[i] as we have already counted that number and
                //index for the element is already allocated
                a[i]=0;
                i++;
            }
        }

    }
    //unsorted array searching method TC-O(n)
    //the idea is to copy the element to be searched ,x,to the last location so that
    //that one last comparison when x is not present in arr[] is saved.
    /*@https://www.geeksforgeeks.org/search-element-unsorted-array-using-minimum-number-comparisons/*/
    //only works when there are no duplicate elements in the array to be searched
    static String search(int a[],int n,int x){
        if(a[n-1]==x)
            return "Found";
        int backup=a[n-1];
        a[n-1]=x;
        for (int i=0;;i++){
            if (a[i]==x){
                a[n-1]=backup;

                if (i<n-1)
                    return "Found";
                return "Not Found";
            }
        }
    }
    /*@https://www.geeksforgeeks.org/frequent-element-array/*/
    //TC-O(nlogn) & SC-O(1)
    static int mostFrequent(int a[],int n){
        Arrays.sort(a);
        int max_count=1,res=a[0];
        int curr_count=1;
        for (int i=1;i<n;i++){
            if (a[i]==a[i-1])
                curr_count++;
            else {
                if (curr_count>max_count){
                    max_count=curr_count;
                    res=a[i-1];
                }
                curr_count=1;
            }
        }
        if (curr_count>max_count){
            max_count=curr_count;
            res=a[n-1];
        }
        return res;
    }

    /*most frequent element in array using hashmap*/
    static int mostFrequent_hashmap(int a[],int n){
        HashMap<Integer,Integer> hm=new HashMap<>();
        for (int i=0;i<n;i++){
            //int key =a[i];
            if (hm.containsKey(a[i])){
                hm.put(a[i],hm.get(a[i])+1);
            }
            else {
                hm.put(a[i],1);
            }
        }
        int max_count=0,res=-1;
        for (HashMap.Entry<Integer,Integer> s:hm.entrySet()){
            if (max_count<s.getValue()){
                res=s.getKey();
                max_count=s.getValue();

            }
        }
        return res;
    }
    /*@https://practice.geeksforgeeks.org/problems/frequency-game/1*/
    static int LargestButMinFreq(int a[],int n){
        SortedMap<Integer,Integer> sm = new TreeMap<>(Collections.reverseOrder());
        for (int i=0;i<n;i++){
            if (!sm.containsKey(a[i])){
                sm.put(a[i],1);
            }
            else{
                sm.put(a[i],sm.get(a[i])+1);
            }
        }
        int minfreq=0;
        int max=-1;
        for (Map.Entry<Integer,Integer> e:sm.entrySet()){
             if(minfreq==0||e.getValue()<minfreq){
                 minfreq=e.getValue();
                 max=e.getKey();
             }
             else if(e.getValue()==minfreq && e.getKey()>max){
                 max=e.getKey();
             }
        }
        return max;
    }
    static int LargestButMinFreq_using_hashmap(int a[],int n){
        HashMap<Integer,Integer> hm= new HashMap<>();
        for(int i=0;i<n;i++)
            hm.put(a[i],hm.get(a[i])==null?1:hm.get(a[i])+1);
        int minFreq=Integer.MAX_VALUE,max=1;
        for (Map.Entry<Integer,Integer> e:hm.entrySet()){
            if (minFreq==Integer.MAX_VALUE || e.getValue()<minFreq){
                minFreq=e.getValue();
                max=e.getKey();
            }
            else if(e.getValue() == minFreq && e.getKey()>max){
                max=e.getKey();
            }
        }
        return max;
    }
    static class Country{
        String name;
        int no;
        public Country(String n,int num){
            this.name=n;
            this.no=num;
        }
    }
    /*There are N countries, each country has Ai players. You need to form teams of size K such that each player in the team is from a different country.
Given N and number of players from each country and size K. Find the maximum number of teams you can form.*/
    static int max_teams(Country[] countries,int teamlength){
     int result=0;
     if (countries==null || countries.length==0 || teamlength<1)
         return result;
     ArrayList<Country> list= new ArrayList<>();
     PriorityQueue<Country> queue = new PriorityQueue<>(new Comparator<Country>() {
         @Override
         public int compare(Country c1, Country c2) {
             return c2.no-c1.no;
         }
     });
     for (Country c:countries){
         queue.offer(c);
     }
     while (teamlength<=queue.size()){
         for (int i=0;i<teamlength;++i){
             Country temp=queue.poll();
             temp.no=temp.no-1;
             if (temp.no>0){
                 list.add(temp);
             }
         }
         ++result;
         for (Country item:list){
             queue.offer(item);
         }
         list.clear();
     }
     return result;
    }

    //maximum no of 3 persons teams that can be formed from two groups
    static int maxTeams(int n1,int n2){
        int count=0;
        while (n1>0 && n2>0 && n1+n2 >=3){
            if (n1>n2){
                n1-=2;
                n2-=1;
            }
            else {
                n1-=1;
                n2-=2;
            }
            count++;
        }
        return count;
    }
    /*This solution doesnt handle negatives
    * TC-O(n)*/
    static int subarraysum(int arr[],int n,int sum){
        int curr_sum=arr[0];
        int start=0;
        for (int i=1;i<=n;i++){
            while (curr_sum > sum && start < i-1)
            {
                curr_sum = curr_sum - arr[start];
                start++;
            }

            // If curr_sum becomes equal to sum, then return true
            if (curr_sum == sum)
            {
                int p = i-1;
                System.out.println("Sum found between indexes " + start
                        + " and " + p);
                return 1;
            }

            // Add this element to curr_sum
            if (i < n)
                curr_sum = curr_sum + arr[i];
        }
        System.out.println("No subarray found");
        return 0;
    }
    //https://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/
    public static void subArraySum(int[] arr, int n, int sum) {
        //cur_sum to keep track of cummulative sum till that point
        int cur_sum = 0;
        int start = 0;
        int end = -1;
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            cur_sum = cur_sum + arr[i];
            //check whether cur_sum - sum = 0, if 0 it means
            //the sub array is starting from index 0- so stop
            if (cur_sum - sum == 0) {
                start = 0;
                end = i;
                break;
            }
            //if hashMap already has the value, means we already
            // have subarray with the sum - so stop
            if (hashMap.containsKey(cur_sum - sum)) {
                start = hashMap.get(cur_sum - sum) + 1;
                end = i;
                break;
            }
            //if value is not present then add to hashmap
            hashMap.put(cur_sum, i);

        }
        // if end is -1 : means we have reached end without the sum
        if (end == -1) {
            System.out.println("No subarray with given sum exists");
        } else {
            System.out.println("Sum found between indexes "
                    + start + " to " + end);
        }

    }
    public static void main(String args[]){
    /*  int arr[]={1,2,2,3,3,3,3};
      int x=3;
      int n =arr.length;
      System.out.println("occurence "+x+" is "+Count_Sorted_Using_Binarysearch(arr,x,n));
      int arr1[]={1,3,2,4,2,1,2};
      int n1=arr1.length;
      System.out.println("unsorted array"+count_Freq_in_Unsorted_array(arr1,n1,2));
      countFreq_of_unosorted_by_using_sortedmap(arr1,n1);
      System.out.println("count frequency according to sequence \n");
      countFreq_by_the_sequence(arr1,n1);
      int a[]={4,6,1,5,8};
      int n3=a.length;
      int t=1;
      System.out.println(x+"is"+search(a,n3,t));
      */
      int a[]={2,2,5,50,1};
      int n=a.length;
      System.out.println("largest but min frequency "+LargestButMinFreq_using_hashmap(a,n));;
    }
}
