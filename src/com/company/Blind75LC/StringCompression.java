package com.company.Blind75LC;

public class StringCompression {
    public int compress(char[] chars) {
        int i = 0;
        int index = 0;
        while(i<chars.length){
            int groupLength = 1;
            while(i+groupLength < chars.length && chars[i+groupLength] == chars[i]){
                groupLength++;
            }

            chars[index++] = chars[i];
            if(groupLength>1){
                for(char c : Integer.toString(groupLength).toCharArray()){
                    chars[index++] = c;
                }
            }

            i=i+groupLength;
        }

        return index;
    }
}
