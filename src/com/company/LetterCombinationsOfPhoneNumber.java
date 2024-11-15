package com.company;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfPhoneNumber {

    // Mapping of digits to letters
    private static final String[] PHONE_MAP = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();

        if(digits.isEmpty()){
            return ans;
        }

        StringBuilder output = new StringBuilder();
        int index = 0;

        solveCombinations(digits,output,index,ans);
        return ans;
    }

    private void solveCombinations(String digits, StringBuilder output, int i, List<String> ans) {
        if(i>=digits.length()){
         ans.add(output.toString());
         return;
        }

        int digit = Character.getNumericValue(digits.charAt(i));
        char[] mappedLetters = PHONE_MAP[digit].toCharArray();

        for (char mappedLetter : mappedLetters) {
            output.append(mappedLetter);
            solveCombinations(digits, output, i + 1, ans);
            //back track remove the output that was added in this for loop, for next item in loop
            output.deleteCharAt(output.length()-1);
        }
    }

    public static void main(String[] args){
        LetterCombinationsOfPhoneNumber letterCombinationsOfPhoneNumber = new LetterCombinationsOfPhoneNumber();
        List<String> combinations = letterCombinationsOfPhoneNumber.letterCombinations("23");
        System.out.println(combinations);
    }


}
