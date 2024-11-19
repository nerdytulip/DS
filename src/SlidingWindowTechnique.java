import java.util.HashMap;
import java.util.Map;

public class SlidingWindowTechnique {

    //Fixed sliding window
    public int maxSumSubArrayOfSizeK(int arr[], int k)
    {
        int i=0,j=0;
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        while(j<arr.length){
            sum+=arr[j];
            if(j-i+1<k){
                j++;
            }else if(j-i+1 == k){
                maxSum = Math.max(sum,maxSum);
                sum -=arr[i];
                i++;
                j++;
            }
        }
        return maxSum;
    }

    //Variable sliding window

    //https://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
    public int longestKSubstr(String s, int k){
        int longest_substring_k_unique_character =0;
        int i=0,j=0;
        Map<Character,Integer> count_map = new HashMap<>();


        while(j<s.length()){
            //calcs
            if(count_map.containsKey(s.charAt(j))){
                count_map.put(s.charAt(j),count_map.get(s.charAt(j))+1);
            }else{
                count_map.put(s.charAt(j),1);
            }

            if(count_map.size()<k){
                //j will be incremented
            }else if(count_map.size() == k){
                longest_substring_k_unique_character = Math.max(longest_substring_k_unique_character,j-i+1);
            }else if(count_map.size()>k){
                while (count_map.size()>k){
                    count_map.put(s.charAt(i),count_map.get(s.charAt(i))-1);
                    if(count_map.get(s.charAt(i)) == 0){
                        count_map.remove(s.charAt(i));
                    }
                    i++;
                }
            }

            j++;
        }

        return longest_substring_k_unique_character;
    }

    // In this question , k is also not given
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/?envType=company&envId=amazon&favoriteSlug=amazon-three-months&difficulty=EASY%2CMEDIUM
    public int lengthOfLongestSubstring(String s) {
        int longest_str_length = Integer.MIN_VALUE;
        Map<Character,Integer> count_map = new HashMap<>();
        int i=0,j=0;

        while (j<s.length()){
            if(count_map.containsKey(s.charAt(j))){
                count_map.put(s.charAt(j),count_map.get(s.charAt(j))+1);
            }else{
                count_map.put(s.charAt(j),1);
            }
            //k here is window size
            if(count_map.size() == j-i+1){
                longest_str_length = Math.max(longest_str_length,j-i+1);
            }else if(count_map.size()< j-i+1){
                while(count_map.size()<j-i+1){
                    count_map.put(s.charAt(i),count_map.get(s.charAt(i))-1);
                    if(count_map.get(s.charAt(i)) == 0){
                        count_map.remove(s.charAt(i));
                    }
                    i++;
                }
            }
            j++;
        }

        return longest_str_length;
    }

    public static void main(String[] args){

        SlidingWindowTechnique slidingWindowTechnique = new SlidingWindowTechnique();
//        int lengthOfLongestSubstring = slidingWindowTechnique.lengthOfLongestSubstring("eeydgwdykpv");
//        System.out.println(lengthOfLongestSubstring);

//        int longestKSubstr = slidingWindowTechnique.longestKSubstr("aabacbebebe", 3);
//        System.out.println(longestKSubstr);

        int arr[] = {1, 4, 2, 10, 2, 3, 1, 0, 20};
        int k = 4;
        System.out.println(slidingWindowTechnique.maxSumSubArrayOfSizeK(arr, k));
    }
}
