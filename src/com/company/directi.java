package com.company;

import java.util.Arrays;
import java.util.HashMap;

public class directi {

    //nlogn
    //https://www.geeksforgeeks.org/find-the-point-where-maximum-intervals-overlap/
    static String max_interval_overlap(int arrl[],int exit[],int n){
        Arrays.sort(arrl);
        Arrays.sort(exit);
        //guests_in guests at a time
        int guests_in=1,max_guests=1,time=arrl[0];
        int i=1,j=0;
        while(i<n && j<n){
            if(arrl[i]<=exit[j]){
                guests_in++;
                if(guests_in>max_guests){
                    max_guests=guests_in;
                    time=arrl[i];
                }
                i++;
            }
            else{
                guests_in--;
                j++;
            }
        }

        String S ="max num of guests ="+max_guests+"at time"+time;
        return S;
    }

    /*@https://www.geeksforgeeks.org/count-number-of-strings-made-of-r-g-and-b-using-given-combination/*/
    static int possibleString(int n,int r,int g,int b){
        int fact[]=new int[n+1];
        fact[0]=1;
        for(int i =1;i<=n;i++){
            fact[i]=fact[i-1]*i;
        }
        int remainder=n-(r+g+b);
        int sum =0;
        for(int i=0;i<=remainder;i++){
            for(int j=0;j<=remainder-i;j++){
                int k = remainder-(i+j);
                sum = sum + fact[n]/(fact[i+r]*fact[j+b]*fact[k+g]);
            }
        }
        return sum;
    }

    /*@https://www.geeksforgeeks.org/check-if-a-sorted-array-can-be-divided-in-pairs-whose-sum-is-k/*/
    static boolean canPairs_sortedarr_ofsumequalK(int arr[],int n,int k){
        if(n==1)
            return false;
        int l=0,r=n-1;
        while (l<r){
            if (arr[l]+arr[r]!=k){
                return false;
            }
            l++;
            r--;
        }

        return true;
    }

    /*@https://www.geeksforgeeks.org/check-if-an-array-can-be-divided-into-pairs-whose-sum-is-divisible-by-k/*/
    static boolean canPairs_ofsumdivbyk(int arr[],int k){
        if(arr.length%2==1)
            return false;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i =0;i<arr.length;i++){
            int rem=arr[i]%k;
            if(!map.containsKey(rem)){
                map.put(rem,0);
            }
            map.put(rem,map.get(rem)+1);
        }
        /*
        * the logic is as follows:
        * if 2*rem=k that means that this element is a multiple of k
        * else if rem ==0,that means that is completely divisible by k
        * else the number of occurences of remainder must be equal to number of
        * of occurences of k-rem*/

        for(int i=0;i<arr.length;i++){
            int rem = arr[i]%k;
            if(2*rem==k){
                if (map.get(rem)%2==1)
                    return false;
            }
            else if(rem==0){
                if(map.get(rem)%2==1)
                    return false;
            }
            else{
                if(map.get(k-rem)!=map.get(rem))
                    return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        int arrl[] ={1,2,10,5,5};
        int exit[]={4,5,12,9,12};
        int n1 =arrl.length;
        max_interval_overlap(arrl,exit,n1);

        int arr[]= {1,2,3,3,3,3,4,5};
        int k =6;
        int n = arr.length;
        if(canPairs_sortedarr_ofsumequalK(arr,n,k)){
            System.out.println("True");
        }else{
            System.out.println("False");
        }
    }
}
