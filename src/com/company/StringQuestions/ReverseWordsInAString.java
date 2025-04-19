package com.company.StringQuestions;

public class ReverseWordsInAString {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        int l = 0;
        int r = words.length -1;

        while(l<r){
            String temp = words[l];
            words[l] = words[r];
            words[r] = temp;
            l++;
            r--;
        }

        return String.join(" ", words);
    }

    public String reverseWords_Constant_Space(String s) {
        int end = s.length() - 1;
        StringBuilder result = new StringBuilder();

        while (end >= 0) {
            // Skip trailing spaces
            while (end >= 0 && s.charAt(end) == ' ') end--;
            if (end < 0) break;

            // Find the start of the word
            int start = end;
            while (start >= 0 && s.charAt(start) != ' ') start--;

            // Append the word to result
            result.append(s.substring(start + 1, end + 1)).append(" ");

            // Move end pointer to the previous word
            end = start;
        }

        // Remove last extra space and return result
        return result.toString().trim();
    }
}
