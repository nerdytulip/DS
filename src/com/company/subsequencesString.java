package com.company;

import java.util.ArrayList;
import java.util.List;

public class subsequencesString {

     public static void subsequences(String s, String output, int i, List<String> ans){

        if(i>=s.length()){
            ans.add(output);
            return;
        }

        //exclude
        subsequences(s,output,i+1,ans);

        //include
        char element = s.charAt(i);
        subsequences(s,output+ element,i+1,ans);
    }

    public static void main(String[] args){
        String s = "abc";
        List<String> ans = new ArrayList<>();
        String output = "";
        int index = 0;
        subsequences(s,output,index,ans);
        System.out.println(ans);
    }
}
