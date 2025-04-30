package com.company.StringQuestions;

public class ZigZagConversion {

    /**
     *  P   A   H   N
     *  A P L S I I G
     *  Y   I   R
     *
     * OR
     *
     * P   A   H   N
     *  A P L S I I G
     *   Y   I   R
     *
     *
     * at each step we first go down and we go up, based on number of rows
     *
     * refer zigzagConversion.png
     *
     * https://www.youtube.com/watch?v=pEku6cp_J80
     *
     * */
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        // define string builder for each row
        StringBuilder[] builders = new StringBuilder[numRows];
        for(int i=0;i<numRows;i++){
            builders[i] = new StringBuilder();
        }

        char[] arr = s.toCharArray();
        int n = arr.length;
        int index = 0;

        while(index < n){
            //go down
            for(int j=0;j<numRows && index<n;j++){
                builders[j].append(arr[index++]);
            }

            //go up before start
            for(int j=numRows-2;j>0 && index<n;j--){
                builders[j].append(arr[index++]);
            }
        }

        StringBuilder res = builders[0];
        for(int i=1;i<numRows;i++){
            res.append(builders[i].toString());
        }

        return res.toString();
    }
}
