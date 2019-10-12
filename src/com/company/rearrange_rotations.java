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

    static void Juggling_leftrotate(int arr[],int d,int n){
        int i,j,k,temp;
        int gcd = gcd(d,n);
        for(i=0;i<gcd;i++){
            temp = arr[i];
            j=i;
            while (true){
                k=j+d;
                if(k>=n)
                    k=k-n;
                if(k==i)
                    break;
                arr[j]=arr[k];
                j=k;
            }
            arr[j]=temp;
        }
    }
    static int gcd(int a,int b){
        int gcd=1;
        for(int i =1;i<=a||i<=b;i++){
            if(a%i==0 && b%i==0)
                gcd=i;
        }
        return gcd;
    }

    static void printarray(int arr[]){
        for(int i=0;i<arr.length;i++)
            System.out.print(arr[i]+",");
    }

    static void reversal(int arr[],int start,int end){
        int temp;
        while (start<end){
            temp=arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
            start++;
            end--;
        }
    }

    static void leftRotate(int arr[],int d){
        if (d==0)
            return;
        int n = arr.length;
        reversal(arr,0,d-1);//Ar
        reversal(arr,d,n-1);//Br
        reversal(arr,0,n-1);//(ArBr)r=BA
    }

    static void rightRotate(int arr[],int d){
        if (d==0)
            return;
        int n =arr.length;
        reversal(arr,0,n-1);//AB->BA
        reversal(arr,0,d-1);//BrA
        reversal(arr,d,n-1);//BrAr
    }

    /*@https://www.geeksforgeeks.org/c-program-cyclically-rotate-array-one/*/
    static void cyclically_rotate(int arr[]){
        int x= arr[arr.length-1];
        for (int i =arr.length-1;i>0;i--)
            arr[i]=arr[i-1];
        arr[0]=x;
    }

    static int search_in_sorted_and_rotated_arr(int arr[],int l,int h,int key){
        if(l>h)
            return -1;
        int mid =(l+h)/2;
        if (arr[mid]==key)
            return mid;
        if (arr[l]<=arr[mid]){
            if(key>=arr[l] && key<=arr[mid])
                return search_in_sorted_and_rotated_arr(arr,l,mid-1,key);
            search_in_sorted_and_rotated_arr(arr,mid+1,h,key);
        }
        if (key>=arr[mid] && key<=arr[h])
            return search_in_sorted_and_rotated_arr(arr,mid+1,h,key);
        return search_in_sorted_and_rotated_arr(arr,l,mid-1,key);
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
    public static void main(String[] args){
        int a[]={1,2,3,4,5,6,7};
        int n=a.length;
        //System.out.println(rearrange(a,n));
        Juggling_leftrotate(a,2,n);
        printarray(a);
    }
}
