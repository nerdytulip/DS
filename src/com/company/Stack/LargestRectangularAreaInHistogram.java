package com.company.Stack;

import java.util.Stack;

public class LargestRectangularAreaInHistogram {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        int[] next = new int[n];
        next = nextSmallerElements(heights,n);

        int[] prev = new int[n];
        prev = prevSmallerElements(heights,n);

        int area = Integer.MIN_VALUE;

        for(int i =0;i<n;i++){
            int l = heights[i];

            if(next[i] == -1){
                next[i] = n;
            }
            int b = next[i] - prev[i] - 1;

            int newArea = l*b;
            area = Math.max(area,newArea);
        }

        return area;

    }

    private int[] nextSmallerElements(int[] heights, int n) {

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        int ans[] = new int[n];

        for(int i = n-1;i>=0;i--){
            int curr = heights[i];
            while (stack.peek()!=-1 && heights[stack.peek()]>= curr){
                stack.pop();
            }

            //ans is stack ka top
            ans[i] = stack.peek();
            stack.push(i);
        }

        return ans;
    }

    private int[] prevSmallerElements(int[] heights, int n) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        int ans[] = new int[n];

        for(int i = 0;i<n;i++){
            int curr = heights[i];
            while (stack.peek()!=-1 && heights[stack.peek()]>= curr){
                stack.pop();
            }

            //ans is stack ka top
            ans[i] = stack.peek();
            stack.push(i);
        }

        return ans;
    }
}
