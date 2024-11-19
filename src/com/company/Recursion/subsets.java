package com.company.Recursion;

import java.util.ArrayList;
import java.util.List;

public class subsets {
    static public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        int index =0;

        solve(nums,output,index,ans);

        return ans;
    }

    static private void solve(int[] nums, List<Integer> output, int index, List<List<Integer>> ans) {

        System.out.println("inside solve when index : "+index);

        //base case
        if(index>=nums.length){
            System.out.println("ans : " + ans + " when index is " + index);
            ans.add(output);
            return;
        }

        //exclude
        System.out.println("exclude step output "+output + " for index " + index);
        solve(nums,new ArrayList<>(output),index+1,ans);

        //include
        int element = nums[index];
        output.add(element);
        System.out.println("include step output "+output + " for index " + index);
        solve(nums,new ArrayList<>(output),index+1,ans);
    }

    public static void main(String[] args){
        int nums[] = {1,2,3};
        subsets(nums);
    }
}
