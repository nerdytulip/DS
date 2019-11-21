package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class dp {
    //longest common substring
    static String LCSsubstr(String X, String Y, int m, int n){
        int[][] LCSuff = new int[m + 1][n + 1];

        // To store length of the longest common substring
        int len = 0;

        // To store the index of the cell which contains the
        // maximum value. This cell's index helps in building
        // up the longest common substring from right to left.
        int row = 0, col = 0;

        /* Following steps build LCSuff[m+1][n+1] in bottom
           up fashion. */
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    LCSuff[i][j] = 0;

                else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    LCSuff[i][j] = LCSuff[i - 1][j - 1] + 1;
                    if (len < LCSuff[i][j]) {
                        len = LCSuff[i][j];
                        row = i;
                        col = j;
                    }
                }
                else
                    LCSuff[i][j] = 0;
            }
        }

        // if true, then no common substring exists
        if (len == 0) {
            System.out.println("No Common Substring");
            String def="";
            return def;
        }

        // allocate space for the longest common substring
        String resultStr = "";

        // traverse up diagonally form the (row, col) cell
        // until LCSuff[row][col] != 0
        while (LCSuff[row][col] != 0) {
            resultStr = X.charAt(row - 1) + resultStr; // or Y[col-1]
            --len;

            // move diagonally up to previous cell
            row--;
            col--;
        }
        return resultStr;
    }
    //longest common subsequence
    static char[] LCS(String X,String Y,int m,int n){
        int[][] L = new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0 || j==0)
                    L[i][j]=0;
                else if(X.charAt(i-1)==Y.charAt(j-1))
                    L[i][j]=L[i-1][j-1]+1;
                else
                    L[i][j]=Math.max(L[i-1][j],L[i][j-1]);
            }
        }
        int index = L[m][n];
        int temp= index;

        char[] lcs = new char[index+1];
        lcs[index]= ' ';
        int i=m,j=n;
        while(i>0 && j>0){
            if(X.charAt(i-1)==Y.charAt(j-1)){
                lcs[index-1]=X.charAt(i-1);
                i--;
                j--;
                index--;
            }
            else if(L[i-1][j]>L[i][j-1])
                i--;
            else
                j--;
        }

        return lcs;
    }
    /*Given an array of non negative numbers and a total, is there subset of numbers in this array which adds up
     * to given total.*/
    //TC-O(m*n)
    //https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
    static boolean SubsetSum(int input[],int total){
       boolean T[][]=new boolean[input.length+1][total +1];
       for(int i=0;i<=input.length;i++){
           T[i][0] =true;
       }
       for(int i=1;i<=input.length;i++){
           for(int j=1;j<=total;j++){
               if(j-input[i-1]>=0){
                   T[i][j]=T[i-1][j]||T[i-1][j-input[i-1]];
                   /*This means the value of T[i][j] till it is less than or equal to the value mentioned in if
                   will be either value in the above row or will be the in above row moved back input times*/
               }
               else {
                   T[i][j]=T[i-1][j];
               }
           }
       }
       return T[input.length][total];
        //return T;
    }
    /*In the variation to the above problem the problem could also be
    * is it possible to split it up into 2 equal
     * sum partitions. Partition need not be equal sized. Just equal sum.
     * @https://www.geeksforgeeks.org/partition-problem-dp-18/
     * @https://www.geeksforgeeks.org/print-equal-sum-sets-array-partition-problem-set-2/
     * @https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/SubsetSum.java
     */
    static HashMap<String,List<Integer>> partition(int arr[]){
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        if(sum%2!=0){
            System.out.println("sum cannot be divided by 2");
        }
        /*another method to check if a particular partition can be done or not*/
        /* if((sum & 1)==1)//This checks whether a sum is odd or even
        *     System.out.println("sum is odd and hence cannot be partioned");
        *     return false;
        *
        * //This divides the sum by two,>>1 divides by two,>>2 divides by 4
        * int k = sum >>1 */
        sum=sum/2;
        boolean T[][]=new boolean[arr.length+1][sum+1];
        for(int i=0;i<arr.length;i++){
            T[i][0]=true;
        }
        for(int i=1;i<=arr.length;i++){
            for(int j=1;j<=sum;j++){
                if(j-arr[i-1]>=0){
                    T[i][j]=T[i-1][j-arr[i-1]] || T[i][j];
                }
                else{
                    T[i][j]=T[i-1][j];
                }
            }
        }

        ArrayList<Integer> set1 = new ArrayList<Integer>();
        ArrayList<Integer> set2 = new ArrayList<Integer>();
        //if partition not possible
        if(!T[arr.length][sum]){
            System.out.println("-1/n");
            //return false;
        }
        int i = arr.length;
        int currSum = sum;
        while (i>0 && currSum >=0){
            /*if the current sum does not contribute to k ,then it belongs to set 2
            * if the current element contribute to k then it belongs to set 1*/
            if(T[i-1][currSum]){
                i--;
                set2.add(arr[i]);
            }
            else if(T[i-1][currSum-arr[i-1]]){
                i--;
                currSum-=arr[i];
                set1.add(arr[i]);
            }
        }

        HashMap<String, List<Integer>> map = new HashMap<>();
        map.put("set 1",set1);
        map.put("set 2",set2);
        //return T[arr.length][sum];
        return map;
    }

    static HashMap<Integer,Integer> knapsack_01(int W,int wt[],int val[],int n){
        int K[][]=new int[n+1][W+1];
        //build the table
        for(int i=0;i<=n;i++){
            for(int w=0;w<=W;w++){
                if(i==0||w==0)
                    K[i][w]=0;
                else if(wt[i-1]<=w)
                    K[i][w]=Math.max(val[i-1]+K[i-1][w-wt[i-1]],K[i-1][w]);
                else
                    K[i][w]=K[i-1][w];
            }
        }

        int res =K[n][W];
        System.out.println("max value of val[] "+res);

        int w= W;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=n;i>0 && res>0;i--){
            // either the result comes from the top
            // (K[i-1][w]) or from (val[i-1] + K[i-1]
            // [w-wt[i-1]]) as in Knapsack table. If
            // it comes from the latter one/ it means
            // the item is included.
            if(res == K[i-1][w])
                continue;
            else{
                map.put(wt[i-1],val[i-1]);
                //System.out.print(wt[i-1]+" ");
                res=res - val[i-1];
                w=w-wt[i-1];
            }
        }
        return map;
    }

    /*Longest increasing subsequence
    * i for traversing the array elements and j for traversing till i
    * if(arr[i]>arr[j]{T[i]=max(T[i],T[j]+1)}
    * O(n^2)
    * */
    static int LIS(int arr[],int n){
        int T[] = new int[arr.length];
        int actualSolution[] = new int[arr.length];
        for(int i=0; i < arr.length; i++){
            T[i] = 1;
            actualSolution[i] = i;
        }

        for(int i=1; i < arr.length; i++){
            for(int j=0; j < i; j++){
                if(arr[i] > arr[j]){
                    if(T[j] + 1 > T[i]){
                        T[i] = T[j] + 1;
                        //set the actualSolution to point to guy before me
                        actualSolution[i] = j;
                    }
                }
            }
        }

        //find the index of max number in T
        int maxIndex = 0;
        for(int i=0; i < T.length; i++){
            if(T[i] > T[maxIndex]){
                maxIndex = i;
            }
        }

        //lets print the actual solution
        int t = maxIndex;
        int newT = maxIndex;
        do{
            t = newT;
            System.out.print(arr[t] + " ");
            newT = actualSolution[t];
        }while(t != newT);
        System.out.println();

        return T[maxIndex];
    }

    //TC-O(n)
    static void maximumsumSubarr_kadane(int arr[]){
        int maxsoFar=0,maxEndingHere=0,start=0,end=0;
        int beg=0;//stores starting index of a positive sum sequence
        for (int i=0;i<arr.length;i++){
            maxEndingHere=maxEndingHere+arr[i];
            if (maxEndingHere<0){
                maxEndingHere=0;
                beg=i+1;
            }
            if (maxsoFar<maxEndingHere){
                maxsoFar=maxEndingHere;
                start=beg;
                end=i;
            }
        }
        System.out.println("largest sum"+maxsoFar);
        System.out.print("the max sub array is");
        for (int i=start;i<end;i++){
            System.out.print(arr[i]+" ");
        }
    }
    /* Write a program to find maximum sum rectangle in give 2D matrix.
     * Assume there is at least one positive number in the 2D matrix.
     *
     * Solution:
     * Keep temp array with size as number of rows. Start left and right from 0
     * and keep adding values for each row and maintain them in this temp array.
     * Run Kadane's algorithm to find max sum subarray in temp. Now increment right by
     * 1. When right reaches last column reset right to 1 and left to 1.
     *
     * Space complexity of this algorithm is O(row)
     * Time complexity of this algorithm is O(row*col*col)
     *
     * References
     * https://www.youtube.com/watch?v=yCQN096CwWM
     * http://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/
     */
    static class Result{
     int maxSum;
     int leftbound;
     int rightbound;
     int upbound;
     int lowbound;
    }
    static class kadaneResult{
        int maxSum;
        int end;
        int start;
    }
    static void kadane(int in[],kadaneResult kadaneresult){
        int max=0, maxstart=-1,maxend=-1,currstart=0,maxsofar=0;
        for (int i=0;i<in.length;i++){
            maxsofar+=in[i];
            if (maxsofar<0){
                maxsofar=0;
                currstart=i+1;
            }
            if (max<maxsofar){
                maxstart=currstart;
                maxend=i;
                max=maxsofar;
            }
        }
        kadaneresult.maxSum=max;
        kadaneresult.start=maxstart;
        kadaneresult.end=maxend;
    }
    static void maxSumRectangle_util(int in[][],Result result,kadaneResult kadaneresult){
       int r=in.length;
       int c=in[0].length;
       int temp[]=new int[r];
       for (int left=0;left<c;left++){
           for (int i=0;i<r;i++){
               temp[i]=0;
           }
           for (int right=left;right<c;right++){
               for (int i=0;i<r;i++){
                   temp[i]+=in[i][right];
               }
               kadane(temp,kadaneresult);
               if (kadaneresult.maxSum>result.maxSum){
                   result.maxSum=kadaneresult.maxSum;
                   result.leftbound=left;
                   result.rightbound=right;
                   result.upbound=kadaneresult.start;
                   result.lowbound=kadaneresult.end;
               }
           }
       }
    }
    static void maxSum(int in[][]){
        Result result=new Result();
        kadaneResult kadaneresult=new kadaneResult();
        maxSumRectangle_util(in,result,kadaneresult);
        System.out.println("maxsum"+result.maxSum+" ,leftbound "+result.leftbound+" ,rightbound "+result.rightbound
                +"  upbound"+result.upbound+"  lowbound"+result.lowbound);
    }

    static int dynamicEditDistance(char[] str1, char[] str2){
        int temp[][] = new int[str1.length+1][str2.length+1];

        for(int i=0; i < temp[0].length; i++){
            temp[0][i] = i;
        }

        for(int i=0; i < temp.length; i++){
            temp[i][0] = i;
        }

        for(int i=1;i <=str1.length; i++){
            for(int j=1; j <= str2.length; j++){
                if(str1[i-1] == str2[j-1]){
                    temp[i][j] = temp[i-1][j-1];
                }else{
                    temp[i][j] = 1 + min(temp[i-1][j-1], temp[i-1][j], temp[i][j-1]);
                }
            }
        }
        printActualEdits(temp, str1, str2);
        return temp[str1.length][str2.length];

    }

    /**
     * Prints the actual edits which needs to be done.
     */
    static void printActualEdits(int T[][], char[] str1, char[] str2) {
        int i = T.length - 1;
        int j = T[0].length - 1;
        while(true) {
            if (i == 0 || j == 0) {
                break;
            }
            if (str1[i-1] == str2[j-1]) {
                i = i-1;
                j = j-1;
            } else if (T[i][j] == T[i-1][j-1] + 1){
                System.out.println("Edit " + str2[j-1] + " in string2 to " + str1[i-1] + " in string1");
                i = i-1;
                j = j-1;
            } else if (T[i][j] == T[i-1][j] + 1) {
                System.out.println("Delete in string1 " + str1[i-1]);
                i = i-1;
            } else if (T[i][j] == T[i][j-1] + 1){
                System.out.println("Delete in string2 " + str2[j-1]);
                j = j -1;
            } else {
                throw new IllegalArgumentException("Some wrong with given data");
            }

        }
    }

    static int min(int a,int b, int c){
        int l = Math.min(a, b);
        return Math.min(l, c);
    }
    public static void main(String[] args){
       /* String A ="site:GeeksforGeeks.org";
        String B ="site:GeeksQuiz.com";
        int m =A.length();
        int n =B.length();
        System.out.println(LCSsubstr(A,B,m,n));*/

        /*String X = "AGGTAB";
        String Y = "GXTXAYB";
        int m = X.length();
        int n = Y.length();
        System.out.println(LCS(X, Y, m, n));*/

      //  int arr1[]={2,3,7,8,10};
       // System.out.println(SubsetSum(arr1,2));
       /* boolean c[][];
        c=SubsetSum(arr1,11);
        for(int i=0;i<c.length;i++){
         System.out.println(Arrays.toString(c[i]));
        }*/
       //System.out.println(partition(arr1));

      /* int val[]={60,100,120};
       int wt[]={10,20,30};
       int W=50;
       int n = val.length;
       System.out.println(knapsack_01(W,wt,val,n));*/

      /* int a[]={-2,-3,4,-1,-2,1,5,-3};
       maximumsumSubarr_kadane(a);*/

       int in[][]={{ 2,  1, -3, -4,  5},
               { 0,  6,  3,  4,  1},
               { 2, -2, -1,  4, -5},
               {-3,  3,  1,  0,  3}};
       maxSum(in);

       String str1="azced";
       String str2="abcdef";
       int result=dynamicEditDistance(str1.toCharArray(),str2.toCharArray());
       System.out.println(result);
        int arr[] = {23,10,22,5,33,8,9,21,50,41,60,80,99, 22,23,24,25,26,27};
        System.out.println(LIS(arr,arr.length));
    }
}

