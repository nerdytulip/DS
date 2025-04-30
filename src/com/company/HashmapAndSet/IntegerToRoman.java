package com.company.HashmapAndSet;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class IntegerToRoman {
    public String intToRoman(int num) {
        Map<Integer, String> intToRomanMap = getIntegerStringHashMap();

        int[] category = new int[]{1,10,100,1000};
        int currCategory = 0;

        StringBuilder romanString = new StringBuilder();

        for (int value : intToRomanMap.keySet()) {
            int count = num / value;
            if(count > 0){
                romanString.append(intToRomanMap.get(value).repeat(count));
                num%=value;
            }
        }

        return romanString.toString();
    }

    private static Map<Integer, String> getIntegerStringHashMap() {
        Map<Integer,String> intToRomanMap = new LinkedHashMap<>();

        intToRomanMap.put(1000,"M");
        intToRomanMap.put(900,"CM");
        intToRomanMap.put(500,"D");
        intToRomanMap.put(400,"CD");
        intToRomanMap.put(100,"C");
        intToRomanMap.put(90,"XC");
        intToRomanMap.put(50,"L");
        intToRomanMap.put(40,"XL");
        intToRomanMap.put(10,"X");
        intToRomanMap.put(9,"IX");
        intToRomanMap.put(5,"V");
        intToRomanMap.put(4,"IV");
        intToRomanMap.put(1,"I");
        return intToRomanMap;
    }
}
