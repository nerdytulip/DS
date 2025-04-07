package com.company.ArrayQuestions;

public class GCDOfStrings {
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        int gcdLength = gcd(str1.length(), str2.length());

        return str1.substring(0, gcdLength);
    }

    private int gcd(int num1, int num2) {
        return num2 == 0 ? num1 : gcd(num2, num1 % num2);
    }

    public static void main(String[] args){
        String str1 = "ABABAB";
        String str2 = "ABAB";
        GCDOfStrings gcd = new GCDOfStrings();
        String ans = gcd.gcdOfStrings(str1,str2);
        System.out.println(ans);
    }
}
