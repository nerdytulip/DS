package com.company;

public class IntegerToEnglish {
    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] thousands = {"", "Thousand", "Million", "Billion"};

        StringBuilder result = new StringBuilder();
        int groupIndex = 0;
        while(num>0){
            if(num%1000 !=0){
                StringBuilder groupResult = new StringBuilder();
                int part = num%1000;

                if(part>=100){
                    groupResult.append(ones[part/100]).append(" Hundred ");
                    part%=100;
                }

                if(part>=20){
                    groupResult.append(tens[part/10]).append(" ");
                    part%=10;
                }

                if(part>0){
                    groupResult.append(ones[part]).append(" ");
                }

                groupResult.append(thousands[groupIndex]).append(" ");

                result.insert(0,groupResult);
            }

            num/=1000;
            groupIndex++;
        }

        return result.toString().trim();
    }

    public static void main(String[] args){
        IntegerToEnglish integerToEnglish = new IntegerToEnglish();
        int num =1234567;
        System.out.println(integerToEnglish.numberToWords(num));
    }
}
