package com.company.Backtracking;

import java.util.ArrayList;
import java.util.List;

public class PermutationOfString {

    public void swap(StringBuilder string,int i,int j){
        if(i<0|i>string.length()|j<0|j>string.length()){
            throw new ArrayIndexOutOfBoundsException();
        }

        char temp = string.charAt(i);
        string.setCharAt(i,string.charAt(j)); // set i with j
        string.setCharAt(j,temp); // set j with temp
    }

    private void solvePermutation(String input,StringBuilder mutableString, int index, List<String> ans) {
        if(index>=input.length()){
            ans.add(mutableString.toString());
            return;
        }

        for(int j=index;j<input.length();j++){
            swap(mutableString,index,j);
            solvePermutation(input,mutableString,index+1,ans);
            swap(mutableString,j,index);
        }
    }

    public List<String> permutations(String input){
        List<String> ans = new ArrayList<>();
        StringBuilder mutableString = new StringBuilder(input);
        int index =0;
        solvePermutation(input,mutableString,index,ans);

        return ans;
    }

    public static void main(String[] args){
        PermutationOfString  permutationOfString =  new PermutationOfString();
        List<String> permutations = permutationOfString.permutations("abc");
        System.out.println(permutations);
    }
}
