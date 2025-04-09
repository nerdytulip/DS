package com.company.ArrayQuestions;
import java.util.*;

public class ArrayRearrangeAndRotations {
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

    static void num_of_flipsof0_to_getmaxconsecutive1(int a[],int m){
        int start=0;int end=0;
        int countzeros=0;
        int bestwindow=0,bestwindowstart=0;
        while (end<a.length){
            if (countzeros<=m){
                if (a[end]==0)
                    countzeros++;
                end++;//increasing window size
            }
            if (countzeros>m){
                if (a[start]==0)
                    countzeros--;
                start++;//decreasing window size
            }

            if (end-start>bestwindow){
                bestwindow=end-start;
                bestwindowstart=start;
            }
        }

        for (int i=0;i<bestwindow;i++){
            if (a[bestwindowstart+i]==0)
                System.out.println(bestwindowstart+i+" ");
        }
    }

    public static void main(String[] args){
        int a[]={1,2,3,4,5,6,7};
        int n=a.length;
        //System.out.println(rearrange(a,n));
        Juggling_leftrotate(a,2,n);
        printarray(a);

        int m=2;
        int ar[]={1, 0, 0, 1, 1, 0, 1, 0, 1, 1};
        num_of_flipsof0_to_getmaxconsecutive1(ar,m);
    }
}
