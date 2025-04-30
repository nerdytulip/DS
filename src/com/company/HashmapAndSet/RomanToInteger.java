package com.company.HashmapAndSet;

import java.util.HashMap;

public class RomanToInteger {

    /**
     * https://leetcode.com/problems/roman-to-integer/description/?envType=study-plan-v2&envId=top-interview-150
     * */

    public int romanToInt(String s) {
        HashMap<Character,Integer> romanToIntMapping = new HashMap<>();
        romanToIntMapping.put('I',1);
        romanToIntMapping.put('V',5);
        romanToIntMapping.put('X',10);
        romanToIntMapping.put('L',50);
        romanToIntMapping.put('C',100);
        romanToIntMapping.put('D',500);
        romanToIntMapping.put('M',1000);

        //if at any point we see a lower roman numeral is before next roman numeral , we will subtract that.
        // for example : if we have IV , then we see I < V , so we will so -1 + 5

        int integerNumber = 0;
        for(int i=0;i<s.length();i++){
            if(i + 1 < s.length() && romanToIntMapping.get(s.charAt(i))<romanToIntMapping.get(s.charAt(i+1))){
                integerNumber-=romanToIntMapping.get(s.charAt(i));
            }else{
                integerNumber+=romanToIntMapping.get(s.charAt(i));
            }
        }

        return integerNumber;
    }
}
