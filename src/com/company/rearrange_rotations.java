package com.company;
import java.util.*;

public class rearrange_rotations {
    /*@https://www.geeksforgeeks.org/rearrange-array-arri-arrj-even-arri/*/
    static int[] rearrange(int a[],int n){
        int evenpos=n/2;
        int oddpos=n-evenpos;
        int temp[] = new int[n];
        for (int i=0;i<n;i++){
            temp[i]=a[i];
        }
        Arrays.sort(temp);
        int j=oddpos-1;
        for(int i=0;i<n;i+=2){
            a[i]=temp[j];
            j--;
        }
        j=oddpos;
        for(int i=1;i<n;i+=2){
            a[i]=temp[j];
            j++;
        }
        return a;
    }
    static int reverseDigits(int num){
        int rev_num=0;
        while(num>0){
            rev_num=rev_num*10+num%10;
            num=num/10;
        }
        return rev_num;
    }
    public static void main(String[] args){
        int a[]={1,2,3,4,5,6,7};
        int n=a.length;
        System.out.println(rearrange(a,n));
    }
}
