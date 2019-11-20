package com.company;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class matrix {
    /*we have to follow the sequence as below
    * 1.move the elements of top row
    * 2.move the elements of last column
    * 3.move the elements of bottom row
    * 4.move th elements of the first column*/
    static void rotate_matrx(int m,int n,int mat[][]){
        int R=m,C=n;
        int row=0,col=0;
        int prev,curr;
        while(row<m && col<n){
            if (row+1==m || col+1==n)
                break;
            prev=mat[row+1][col];
            //move the element of first row
            for (int i=col;i<n;i++){
                curr=mat[row][i];
                mat[row][i]=prev;
                prev=curr;
            }
            row++;
            //move the last column
            for(int i=row;i<m;i++){
                curr=mat[i][n-1];
                mat[i][n-1]=prev;
                prev=curr;
            }
            n--;
            //move the remaining element of last row
            if(row<m){
                for(int i=n-1;i>=col;i--){
                    curr=mat[m-1][i];
                    mat[m-1][i]=prev;
                    prev=curr;
                }
            }
            m--;
            //move the elements of first column from the remaining rows
            if (col<n){
                for(int i=m-1;i>=row;i--){
                    curr=mat[i][col];
                    mat[i][col]=prev;
                    prev=curr;
                }
            }
            col++;
        }

        for(int i=0;i<R;i++){
            for (int j=0;j<C;j++){
                System.out.print(mat[i][j]+" ");
               // System.out.print("\n");
            }
            System.out.print("\n");
        }
    }
    /*
    *   top           right
    *
    *   left          bottom
    * */
    /*https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/*/
    static void rotate90AntiClockwise_squarematrix_inplace(int mat[][]){
        int N=4;
        for (int x=0;x<N/2;x++){
            for (int y=x;y<N-x-1;y++){
                int temp=mat[x][y];//store the current variable
                mat[x][y]=mat[y][N-1-x];//shift from right to top
                mat[y][N-1-x]=mat[N-1-x][N-1-y];//shift from bottom to right
                mat[N-1-x][N-1-y]=mat[N-1-y][x];//shift from left to bottom
                mat[N-1-y][x]=temp;//top/temp to left
            }
        }
    }
    /*https://www.geeksforgeeks.org/rotate-a-matrix-by-90-degree-in-clockwise-direction-without-using-any-extra-space/*/
    static void rotate90Clockwise_squarematrix_inplace(int mat[][]){
        int N=4;
        for (int x=0;x<N/2;x++){
            for (int y=x;y<N-x-1;y++){
                int temp=mat[x][y];//store the current variable
                mat[x][y]=mat[N-1-y][x];//shift from left to top/current
                mat[N-1-y][x]=mat[N-1-x][N-1-y];//shift from bottom to left
                mat[N-1-x][N-1-y]=mat[y][N-1-x];//shift from right to bottom
                mat[y][N-1-x]=temp;//temp/current to right
            }
        }
    }
    /*Another method to rotate a matrix by 90 degree clockwise
    and anticlockwise is below
    for anticlockwise:
    1.find the transpose of matrix
    2.reverse columns of the transpose

    for clockwise:
    1.find the transpose of a matrix
    2.reverse the rows of the transpose
    */
    static void rotate90AntiClockwise_using_tranpose(int mat[][]){
        transpose(mat);
        //int k;
        //reverse columns for anticlockwise
        for (int i=0;i<mat[0].length;i++){
            for (int j=0,k=mat[0].length-1;j<k ; j++,k--){
                int temp=mat[j][i];
                mat[j][i]=mat[k][i];
                mat[k][i]=temp;
            }
        }
    }

    static void rotate90Clockwise_using_transpose(int mat[][]){
        transpose(mat);
        //outer loop for columns and inner loop for rows as here we have to
        //reverese the row
        for(int i=0;i<mat[0].length;i++){
            for (int j=0,k=mat[0].length-1;j<k;j++,k--){
                int temp=mat[i][j];
                mat[i][j]=mat[i][k];
                mat[i][k]=temp;
            }
        }
    }
    /*if we have to rotate the matrix by 180 degrees
    * then we will just print the matrix in reverse order
    * or
    * as for 90 we did transpose and then reverse ,so
    * for 180, we'll do transpose,reverse,transpose,reverse and that's it
    * */
    static void printmatrix(int mat[][]){
        for (int i=0;i<mat.length;i++){
            for (int j=0;j<mat[0].length;j++)
                System.out.print(mat[i][j]+" ");
            System.out.println("");
        }
    }
    static void spiralprint(int m,int n,int a[][]){
        int row=0,col=0;
        while (row<m && col<n){
            //print first row from remaining rows
            for (int i=col;i<n;++i){
                System.out.print(a[row][i]+" ");
            }
            row++;
            //print last column from remaining columns
            for (int i=row;i<m;++i){
                System.out.print(a[i][n-1]+" ");
            }
            n--;
            //print last row from the remaining rows
            if (row<m){
                for (int i=n-1;i>=col;--i){
                    System.out.print(a[m-1][i]+" ");
                }
                m--;
            }

            if (col<n){
                for (int i=m-1;i>=row;--i){
                    System.out.print(a[i][col]+" ");
                }
                col++;
            }
        }
    }
    //tranpose of the rectangular matrix cannot be done in place as in
    // java once the size is declared it cannot be changed,
    // the only possible solution so that rectangular matrix can be
    // transposed inplace is that we declare a larger 2d array before hand
    static int M=3,N=4;
    static void transpose_rectangular_matrix(int A[][],int B[][]){
       // int M=3,N=4;
        for (int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                B[i][j]=A[j][i];
            }
        }
    }
    //inplace transpose for a square matrix
    static void transpose(int A[][]){
        int N=4;
        for (int i=0;i<N;i++){
            for (int j=i+1;j<N;j++){
                int temp=A[i][j];
                A[i][j]=A[j][i];
                A[j][i]=temp;
            }
        }
    }
    /*check if all rows of a matrix are circular rotations
    of each other.
    the idea used here is as follows:
    1.we create a string of first row elements and concatenate the string
    with itself,str_cat, so that search can be performed
    2.we traverse all the remaining rows.For every row being traversed we
    create a string string_curr.If str_curr is not a substring of str_cat,return false
    else return true.
    @https://www.geeksforgeeks.org/check-rows-matrix-circular-rotations/
    */
    static boolean arerows_rotations(int mat[][],int n){
        String str_cat="";
        for (int i=0;i<n;i++){
            str_cat=str_cat+"-"+String.valueOf(mat[0][i]);
        }
        str_cat=str_cat+str_cat;
        //traverse the remaining rowa
        for (int i=1;i<n;i++){
            String curr_str="";
            for (int j=0;j<n;j++){
                curr_str=curr_str+"-"+String.valueOf(mat[i][j]);
            }
            if (str_cat.contentEquals(curr_str)){
                return false;
            }
        }
        return true;
    }
    /*@https://www.geeksforgeeks.org/find-number-of-islands/*/
    static int numIslands(char[][] grid){
        if (grid==null || grid.length==0){
            return 0;
        }

        int numIslands=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                if (grid[i][j]=='1'){
                    numIslands+=dfs(grid,i,j);
                }
            }
        }
        return numIslands;
    }
    static int dfs(char[][] grid,int i,int j){
        if(i<0||i>=grid.length || j<0||j>=grid[i].length || grid[i][j]=='0'){
            return 0;
        }
        grid[i][j]='0';
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
        return 1;
    }
    /*
    * given a matrix with 0s and 1s.Find biggest sub-square
    * matrix entirely of 1s int this matrix
    * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/MaximumSizeSubMatrix.java
    * https://www.youtube.com/watch?v=_Lf1looyJMU*/
    static int maxSize_squareMatrix(int a[][]){
        int result[][]=new int[a.length][a[0].length];
        int max=0;
        for (int i=0;i<a.length;i++){
            result[i][0]=a[i][0];
            if (result[i][0]==1)
                max=1;
        }
        for (int i=0;i<a[0].length;i++){
            result[0][i]=a[0][i];
            if (result[0][i]==1)
                max=1;
        }

        for (int i=1;i<a.length;i++){
            for (int j=1;j<a[i].length;j++){
                if (a[i][j]==0)
                    continue;
                int t= Math.min(Math.min(result[i-1][j],result[i-1][j-1]),result[i][j-1]);
                result[i][j]=t+1;
                if (result[i][j]>max)
                    max=result[i][j];
            }
        }
        return max;
    }

    /*given a matrix with 0s and 1s,find the maximumrectangular matrix
    * we use the concept of max rectangular area in histogram by considering the
    * fact that each row can be represented as histogram*/
    /*TC-O(rows*cols)
    * SC-O(cols)
    * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/MaximumRectangularSubmatrixOf1s.java#L40
    * https://www.youtube.com/watch?v=ZmnqCZp9bBs
    * https://www.youtube.com/watch?v=g8bSdXCG-lA*/
    static int maximumRectangularSubmatrixof1s(int in[][]){
        int temp[]=new int[in[0].length];
        int maxArea=0;
        int area=0;
        for (int i=0;i<in.length;i++){
            for (int j=0;j<in[0].length;j++){
                if (in[i][j]==0)
                    temp[j]=0;
                else
                    //if not zero then we add in[i][j] to the jth value of temp
                    temp[j]+=in[i][j];
            }
            area=maxHistogram(temp);
            if (area>maxArea)
                maxArea=area;
        }
        return maxArea;
    }
    static int maxHistogram(int input[]){
        Deque<Integer> stack = new LinkedList<Integer>();
        int maxArea = 0;
        int area = 0;
        int i;
        for(i=0; i < input.length;){
            if(stack.isEmpty() || input[stack.peekFirst()] <= input[i]){
                stack.offerFirst(i++);
            }else{
                int top = stack.pollFirst();
                //if stack is empty means everything till i has to be
                //greater or equal to input[top] so get area by
                //input[top] * i;
                if(stack.isEmpty()){
                    area = input[top] * i;
                }
                //if stack is not empty then everything from i-1 to input.peek() + 1
                //has to be greater or equal to input[top]
                //so area = input[top]*(i - stack.peek() - 1);
                else{
                    area = input[top] * (i - stack.peekFirst() - 1);
                }
                if(area > maxArea){
                    maxArea = area;
                }
            }
        }
        while(!stack.isEmpty()){
            int top = stack.pollFirst();
            //if stack is empty means everything till i has to be
            //greater or equal to input[top] so get area by
            //input[top] * i;
            if(stack.isEmpty()){
                area = input[top] * i;
            }
            //if stack is not empty then everything from i-1 to input.peek() + 1
            //has to be greater or equal to input[top]
            //so area = input[top]*(i - stack.peek() - 1);
            else{
                area = input[top] * (i - stack.peekFirst() - 1);
            }
            if(area > maxArea){
                maxArea = area;
            }
        }
        return maxArea;
    }

    /*Counting all submatrix with sum divisible by k
    * brute force approach takes O(n^4)
    * so ,in this approach we take use of https://www.geeksforgeeks.org/count-sub-arrays-sum-divisible-k/*/
    static int countSubmatrix(int mat[][],int n,int k){
        int total=0;
        int left,right;
        int temp[]=new int[n];
        for (left=0;left<n;left++){
            Arrays.fill(temp,0);
            for (right=left;right<n;right++){
                for (int i=0;i<n;++i)
                    temp[i]+=mat[i][right];
                total+=greedy_dc.subarray_with_sum_div_by_k(temp,n,k);
            }
        }
        return total;
    }
    public static void main(String args[]){
     int a[][]={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
     //System.out.println("row"+a.length+"column"+a[0].length);
     rotate90Clockwise_using_transpose(a);
     printmatrix(a);
    rotate_matrx(a.length,a[0].length,a);
     int A[][]={{1,1,1,1},{2,2,2,2},{3,3,3,3}};
     int B[][]= new int[N][M];
     int A1[][]={{1,1,1,1},{2,2,2,2},{3,3,3,3},{4,4,4,4}};
     transpose_rectangular_matrix(A,B);
     //char[][] grid={{1,1,0,0,0},{1,1,0,0,0},{0,0,1,0,0},{0,0,0,1,1}};
     //System.out.println("num of islands"+numIslands(grid));
    }
}
