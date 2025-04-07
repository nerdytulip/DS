package com.company.ArrayQuestions;

public class MergeStringsAlternatively {

    public String mergeAlternately(String word1, String word2) {
        int word1Length = word1.length();
        int word2Length = word2.length();
        int commonLength = Math.min(word1Length,word2Length);

        StringBuilder ans = new StringBuilder();
        for(int i=0;i<commonLength;i++){
            ans.append(word1.charAt(i));
            ans.append(word2.charAt(i));
        }

        if(word1Length < word2Length){
            for(int i = commonLength ; i < word2Length;i++){
                ans.append(word2.charAt(i));
            }
        }

        if(word1Length > word2Length){
            for(int i = commonLength ; i < word1Length;i++){
                ans.append(word1.charAt(i));
            }
        }

        return ans.toString();
    }
}
