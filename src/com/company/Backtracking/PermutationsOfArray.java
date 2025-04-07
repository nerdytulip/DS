package com.company.Backtracking;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

public class PermutationsOfArray {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int index = 0;
        List<Integer> tempList = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            tempList.add(nums[i]);
        }
        permutations(nums,tempList,index,ans);
        return ans;
    }

    private void permutations(int[] nums, List<Integer> tempList,int index, List<List<Integer>> ans) {
        if(index>=nums.length){
            ans.add(new ArrayList<>(tempList));
            return;
        }

        for(int j = index;j<nums.length;j++){
            swap(tempList,j,index);
            permutations(nums,tempList,index+1,ans);
            //backtrack
            swap(tempList,index,j);
        }
    }

    public static void main(String[] args){
        PermutationsOfArray permutationsOfArray = new PermutationsOfArray();
        int[] nums = {1,2,3};
        List<List<Integer>> permute = permutationsOfArray.permute(nums);
        System.out.println("permutations" + permute);
    }
}
