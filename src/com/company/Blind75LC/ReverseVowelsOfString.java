package com.company.Blind75LC;

import java.util.ArrayList;
import java.util.List;

public class ReverseVowelsOfString {

    public String reverseVowels(String s) {
        List<Character> vowels = new ArrayList<>();
        StringBuilder result = new StringBuilder(s);

        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == 'a' || s.charAt(i) == 'A' ||
                s.charAt(i) == 'e' || s.charAt(i) == 'E' ||
                    s.charAt(i) == 'i' || s.charAt(i) == 'I' ||
                    s.charAt(i) == 'o' || s.charAt(i) == 'O' ||
                    s.charAt(i) == 'u' || s.charAt(i) == 'U'){
                vowels.add(s.charAt(i));
            }
        }

        int reverseIndex = vowels.size()-1;
        for(int i =0;i<s.length();i++){
            if(s.charAt(i) == 'a' || s.charAt(i) == 'A' ||
                    s.charAt(i) == 'e' || s.charAt(i) == 'E' ||
                    s.charAt(i) == 'i' || s.charAt(i) == 'I' ||
                    s.charAt(i) == 'o' || s.charAt(i) == 'O' ||
                    s.charAt(i) == 'u' || s.charAt(i) == 'U'){
                result.setCharAt(i,vowels.get(reverseIndex));
                reverseIndex--;
            }
        }

        return result.toString();
    }
}
