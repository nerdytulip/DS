package com.company.Backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {

    private void solve(int start,int k,int n,List<List<Integer>> totalList,List<Integer> currentCombination){
        if(currentCombination.size() == k && n == 0){
            totalList.add(new ArrayList<>(currentCombination));
            return;
        }

        if(currentCombination.size() > k || n<0){
            return;
        }

        for(int i = start;i<=9;i++){
            currentCombination.add(i);
            solve(i+1,k,n-i,totalList,currentCombination);
            currentCombination.remove(currentCombination.size() - 1);;
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> totalList = new ArrayList<>();
        solve(1,k,n,totalList,new ArrayList<>());

        return totalList;
    }
}
