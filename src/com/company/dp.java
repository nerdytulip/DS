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

    /*Longest increasing subset
    * i for traversing the array elements and j for traversing till i
    * if(arr[i]>arr[j]{T[i]=max(T[i],T[j]+1)}
    * O(n^2)
    * */
    static int LIS(int arr[],int n){
        int lis[] = new int[n];
        for(int i =0;i<n;i++){
            lis[i]=1;
        }
        for(int i=1;i<n;i++){
            for(int j =0;j<i;i++){
                if(arr[i]>arr[j] && lis[i]<lis[j]+1){
                    lis[i]=lis[j]+1;
                }
            }
        }
        int max =0;
        for(int i=0;i<n;i++){
            if(max<lis[i])
                max=lis[i];
        }

     return max;
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

        int arr1[]={2,3,7,8,10};
       // System.out.println(SubsetSum(arr1,2));
       /* boolean c[][];
        c=SubsetSum(arr1,11);
        for(int i=0;i<c.length;i++){
         System.out.println(Arrays.toString(c[i]));
        }*/
       //System.out.println(partition(arr1));

       int val[]={60,100,120};
       int wt[]={10,20,30};
       int W=50;
       int n = val.length;
       System.out.println(knapsack_01(W,wt,val,n));
    }
}

