package com.company;

public class StringsReecursion {

    public void reverseString1(char[] s) {

        int i = 0;

        reverse(s,i);
    }

    private void reverse(char[] s, int i) {
        if(i >= s.length - 1 -i){
            return;
        }

        swap(s, i);
        i++;

        reverse(s,i);
    }

    private static void swap(char[] s, int i) {
        char temp = s[i];
        s[i]= s[s.length -1 -i];
        s[s.length -1 -i] = temp;
    }

    public void reverseString(char[] s) {
        int start = 0 ;
        int end = s.length-1;
        while(start<=end){
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    public void helper(int startIndex,int endIndex,char[] s){
        char startEl = s[startIndex];
        s[startIndex] = s[endIndex];
        s[endIndex] = startEl;
    }

}
