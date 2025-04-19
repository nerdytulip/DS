package com.company.StringQuestions;

import java.util.*;

public class Strings {
    /*@https://www.geeksforgeeks.org/count-substrings-that-starts-with-character-x-and-ends-with-character-y/*/
    static int substr_startwithX_endwithY(String str,int n,char x,char y){
        int total_count=0,count_x=0;
        for(int i=0;i<n;i++){
            if(str.charAt(i)==x)
                count_x++;
            if(str.charAt(i)==y)
                total_count+=count_x;
        }

        return total_count;
    }

    /*@https://www.geeksforgeeks.org/a-program-to-check-if-strings-are-rotations-of-each-other/*/
    static boolean areRotations(String str1,String str2){
        return ((str1.length()==str2.length()) &&
                ((str1+str1).indexOf(str2)!=-1));
    }

    /*TreeMap can be a bit handy when we only need to store unique elements in a sorted order
    *  This solution has more time complexity O(nLogn) compared HashMap which has O(n).*/
   /*https://github.com/mission-peace/interview/blob/master/src/com/interview/recursion/StringPermutation.java*/
    static ArrayList<String> permute(char in[]){
        TreeMap<Character,Integer> map = new TreeMap<>();
        for (char ch:in){
            if(!map.containsKey(ch)){
                map.put(ch,1);
            }
            int c = map.get(ch);
            map.put(ch,++c);
        }
        char str[]=new char[map.size()];
        int count[]=new int[map.size()];
        int index=0;
        for (Map.Entry<Character,Integer> entry:map.entrySet()){
            str[index]=entry.getKey();
            count[index]=entry.getValue();
            index++;
        }
        ArrayList<String> resultlist = new ArrayList<>();
        char result[]=new char[in.length];
        permuteUtil(str,count,result,0,resultlist);
        return resultlist;
    }

    static void permuteUtil(char str[],int count[],char result[],int level,ArrayList<String> resultlist){
        if(level==result.length){
            resultlist.add(new String(result));
            return;
        }

        for(int i=0;i<str.length;i++){
            if (count[i]==0){
                continue;
            }
            result[level]=str[i];
            count[i]--;
            permuteUtil(str,count,result,level+1,resultlist);
            count[i]++;
        }
    }

    /*static void permute(String str,int l,int r){
        //ArrayList<String> list = new ArrayList<String>();
        if(l==r){
           System.out.println(str);
           //list.add(str);
        }
        else{
            for(int i =l;i<=r;i++){
                str=swap(str,l,i);
                permute(str,l+1,r);
                str=swap(str,l,i);
            }
        }
        //return list;
    }
    static String swap(String a,int i,int j){
        char temp;
        char charArray[]=a.toCharArray();
        temp=charArray[i];
        charArray[i]=charArray[j];
        charArray[j]=temp;
        return String.valueOf(charArray);
    }*/
    static int check(String S,String D){
        int i=S.length()-1;
        int j=D.length()-1;
        int result=0;
        while (i>0 && j>0){
            char s=S.charAt(i);
            char d=D.charAt(j);
            if (s==d){
                i--;
                j--;
            }
            result++;
            i--;
        }
        return result;
    }
    //given two strings ,a character is inserted in s2 and we have to find that character
    public static Character getInsertedCharacter(String s1, String s2){
        if(s2.length()-s1.length() != 1) // Assuming s2 is always larger and not null
            return null;
        int mid = s1.length()/2;
        return getInsertedCharacter(s1, s2, mid);
    }
    public static Character getInsertedCharacter(String s1, String s2, int mid){
        if(s1.length() == 1 && s2.length() == s1.length())
            return s2.charAt(0);
        if(s2.length()==1)
            return s2.charAt(0);
        if(s1.substring(0, mid).equals(s2.substring(0, mid))){
            String temp = s1.substring(mid, s1.length());
            return getInsertedCharacter(temp, s2.substring(mid, s2.length()), temp.length()/2);
        }else
            return getInsertedCharacter(s1.substring(0, mid), s2.substring(0, mid), mid/2);
    }

    /*https://www.youtube.com/watch?v=ih2OZ9-M3OM
    * https://www.geeksforgeeks.org/find-if-a-string-is-interleaved-of-two-other-strings-dp-33/*/
    static boolean isInterleaved(char str1[],char str2[],char str3[]){
        boolean T[][] = new boolean[str1.length +1][str2.length +1];

        if(str1.length + str2.length != str3.length){
            return false;
        }

        for(int i=0; i < T.length; i++){
            for(int j=0; j < T[i].length; j++){
                int l = i + j -1;
                if(i == 0 && j == 0){
                    T[i][j] = true;
                }
                else if(i == 0){
                    if(str3[l] == str2[j-1]){
                        T[i][j] = T[i][j-1];
                    }
                }
                else if(j == 0){
                    if(str1[i-1] == str3[l]){
                        T[i][j] = T[i-1][j];
                    }
                }
                else{
                    T[i][j] = (str1[i-1] == str3[l] ? T[i-1][j] : false) || (str2[j-1] == str3[l] ? T[i][j-1] : false);
                }
            }
        }
        return T[str1.length][str2.length];
    }

    //https://www.geeksforgeeks.org/count-pairs-of-characters-in-a-string-whose-ascii-value-difference-is-k/
    static int pairsWithKdifference_of_ascii(char[] str,int k){
       int MAX=26;
       int n=str.length;
       int freq[]=new int[MAX];
       for (int i=0;i<n;i++){
           freq[str[i]-'a']++;
       }
       int count=0;
       if (k==0){
           for(int i=0;i<MAX;i++){
               if (freq[i]>1){
                   count+= ((freq[i])*(freq[i]-1)/2);
               }
           }
       }
       else{
           for (int i=0;i<MAX;i++){
               if (freq[i]>0 && i+k<MAX && freq[i+k]>0){
                   count+=(freq[i]*freq[i+k]);
               }
           }
       }
       return count;
    }

    static int patternCount_101(String str){
        int last=str.charAt(0);
        int i=1,count=0;
        while(i<str.length()){
            if (str.charAt(i)=='0' && last=='1'){
                while(str.charAt(i)=='0')
                    i++;
                if (str.charAt(i)=='1')
                    count++;
            }
            last=str.charAt(i);
            i++;
        }
        return count;
    }

    static int smallest_positive_missing(int a[]){
        int val;//stores current array element
        int nextval;//stores the next array element in the current traversal
        for (int i=0;i<a.length;i++){
            if (a[i]<=0||a[i]>a.length)
                continue;
            val=a[i];
            while(a[val-1]!=val){
                nextval=a[val-1];
                a[val-1]=val;
                val=nextval;
                if (val<=0 ||val>a.length)
                    break;
            }
        }
        for (int i=0;i<a.length;i++){
            if (a[i]!=i+1)
                return i+1;
        }
        //if all indices are marked,the return
        //smallest missing positive as arraysize+1
        return a.length+1;
    }

    static boolean isMatchingPair(char ch1,char ch2){
        if (ch1=='(' && ch2==')' || ch1=='[' && ch2==']' || ch1=='{' && ch2=='}'){
            return true;
        }
        return false;
    }
    static boolean isBalanced(String s){
        Stack<Character> leftchars=new Stack<>();
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)=='('|| s.charAt(i)=='['|| s.charAt(i)=='{'){
                leftchars.push(s.charAt(i));
            }
            if (s.charAt(i)=='}' || s.charAt(i)==']' || s.charAt(i)==')'){
                if (leftchars.isEmpty())
                    return false;
                else if(!isMatchingPair(leftchars.pop(),s.charAt(i))){
                    return false;
                }
            }
        }
        if (leftchars.isEmpty())
            return true;
        return false;
    }
    static List<String> generateParenthesis(int num){
        List<String> result=new ArrayList<>();
        directedGenerateBalancedParen(num,num,"",result);
        return result;
    }
    static  void directedGenerateBalancedParen(int numleftparen,int numrightparen,String parenString,List<String> result){
        /* The recursion has bottomed out.
    We have used all left and right parens necessary within constraints up
    to this point. Therefore, the answer we add will be a valid paren string.

    We can add this answer and then backtrack so the previous call can exhaust
    more possibilities and express more answers...and then return to its caller,
    etc. etc.
    Yeah...this is what backtracking is all about.*/
        if (numleftparen==0 || numrightparen==0){
            result.add(parenString);
            return;
        }
    /*
    At each frame of the recursion we have 2 things we can do:
    1.) Insert a left parenthesis
    2.) Insert a right parenthesis
    These represent all of the possibilities of paths we can take from this
    respective call. The path that we can take all depends on the state coming
    into this call.
     */

     /*
    Can we insert a left parenthesis? Only if we have lefts remaining to insert
    at this point in the recursion
     */
     if (numleftparen>0){
         directedGenerateBalancedParen(numleftparen-1,numrightparen,parenString+"(",result);
     }
     if (numleftparen<numrightparen){
         directedGenerateBalancedParen(numleftparen,numrightparen-1,parenString+")",result);
     }
    }

    /*https://backtobackswe.com/platform/content/minimum-window-substring/solutions
    * https://www.youtube.com/watch?v=eS6PZLjoaq8
    * https://www.geeksforgeeks.org/count-number-of-substrings-with-exactly-k-distinct-characters/
    * find-the-smallest-window-in-a-string-containing-all-characters-of-another-string*/
    static public String minWindow(String searchString, String t) {
        Map<Character, Integer> requiredCharacters = buildMappingOfCharactersToOccurrences(t);
        Map<Character, Integer> windowCharacterMapping = new HashMap<Character, Integer>();

        int left = 0;
        int right = 0;

        int totalCharFrequenciesToMatch = requiredCharacters.size();
        int charFrequenciesInWindowThatMatch = 0;

        int minWindowLengthSeenSoFar = Integer.MAX_VALUE;
        String minWindow = "";

        while (right < searchString.length()) {
            char characterAtRightPointer = searchString.charAt(right);
            addCharacterToHashtableMapping(windowCharacterMapping, characterAtRightPointer);

            boolean rightCharIsARequirement = requiredCharacters.containsKey(characterAtRightPointer);
            if (rightCharIsARequirement) {
                boolean requirementForCharacterMet = requiredCharacters.get(characterAtRightPointer)
                        .intValue() == windowCharacterMapping.get(characterAtRightPointer).intValue();

                if (requirementForCharacterMet) {
                    charFrequenciesInWindowThatMatch++;
                }
            }

            //removing excess
            while (charFrequenciesInWindowThatMatch == totalCharFrequenciesToMatch && left <= right) {
                char characterAtLeftPointer = searchString.charAt(left);
                int windowSize = right - left + 1;

                if (windowSize < minWindowLengthSeenSoFar) {
                    minWindowLengthSeenSoFar = windowSize;
                    minWindow = searchString.substring(left, right + 1);
                }
                //
                //else do this
                windowCharacterMapping.put(characterAtLeftPointer, windowCharacterMapping.get(characterAtLeftPointer) - 1);

                boolean leftCharIsARequirement = requiredCharacters.containsKey(characterAtLeftPointer);
                if (leftCharIsARequirement) {
                    boolean characterFailsRequirement = windowCharacterMapping.get(characterAtLeftPointer)
                            .intValue() < requiredCharacters.get(characterAtLeftPointer).intValue();

                    if (characterFailsRequirement) {
                        charFrequenciesInWindowThatMatch--;
                    }
                }

                left++;
            }

            right++;
        }

        return minWindow;
    }

    static Map<Character, Integer> buildMappingOfCharactersToOccurrences(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            int occurrencesOfCharacter = map.getOrDefault(s.charAt(i), 0);
            map.put(s.charAt(i), occurrencesOfCharacter + 1);
        }

        return map;
    }

    static void addCharacterToHashtableMapping(Map<Character, Integer> map, Character c) {
        int occurrences = map.getOrDefault(c, 0);
        map.put(c, occurrences + 1);
    }

    /*https://www.geeksforgeeks.org/smallest-window-contains-characters-string/*/
    static String minWindow_ofdistinctchars_of_itself(String S){
        HashSet<Character> hashset = new HashSet<Character>();
        int count =0;
        while(count < S.length()) {
            hashset.add(S.charAt(count));
            count++;
        }

        char distinct[] = new char[hashset.size()];
        int i=0;
        for(Character c :hashset){
            distinct[i++]= c;
        }
        //String pattern = new String(distinct);
        String pattern = String.valueOf(distinct);


        return minWindow(S,pattern);
    }

    /*https://www.geeksforgeeks.org/number-substrings-count-character-k/
    * Given a string and an integer k, find number of substrings in which all the different characters occurs exactly k times */

    static  boolean check(int freq[], int k)
    {
        int MAX_CHAR =26;
        for (int i = 0; i < MAX_CHAR; i++)
            if (freq[i] !=0 && freq[i] != k)
                return false;
        return true;
    }

    static int count_substrs_with_constraints(String s,int k){
        ArrayList<String> list = new ArrayList<>();
        int MAX_CHAR=26;
        int res = 0; // Initialize result

        // Pick a starting point
        for (int i = 0; i< s.length(); i++)
        {
            // Initialize all frequencies as 0
            // for this starting point
            int freq[] = new int[MAX_CHAR];

            // One by one pick ending points
            for (int j = i; j<s.length(); j++)
            {

                // Increment frequency of current char
                int index = s.charAt(j) - 'a';
                freq[index]++;

                // If frequency becomes more than
                // k, we can't have more substrings
                // starting with i
                if (freq[index] > k)
                    break;

                    // If frequency becomes k, then check
                    // other frequencies as well.
                else if (freq[index] == k &&
                        check(freq, k) == true){
                    //list.add(s.substring(i,j));
                    res++;}
            }
        }

        //System.out.println(list);
        return res;
    }

    /*https://www.geeksforgeeks.org/count-number-of-substrings-with-exactly-k-distinct-characters/*/
    static int countkDist(String str, int k)
    {
        // Initialize result
        int res = 0;
        int n = str.length();
        // To store count of characters from 'a' to 'z'
        int cnt[] = new int[26];

        // Consider all substrings beginning with
        // str[i]
        for (int i = 0; i < n; i++)
        {
            int dist_count = 0;
            // Initializing count array with 0
            Arrays.fill(cnt, 0);
            // Consider all substrings between str[i..j]
            for (int j=i; j<n; j++)
            {
                // If this is a new character for this
                // substring, increment dist_count.
                if (cnt[str.charAt(j) - 'a'] == 0)
                    dist_count++;

                // Increment count of current character
                cnt[str.charAt(j) - 'a']++;

                // If distinct character count becomes k,
                // then increment result.
                if (dist_count == k)
                    res++;
            }
        }

        return res;
    }

    /*https://www.youtube.com/watch?v=k--bSleyD4E*/
    static int longestsubstrwithKdistinctchar(String str,int k){
        if(str == null || str.length() == 0 || k <= 0 || k > str.length()) {
            return -1;
        }

        int maxLength = 0;
        int start, end;
        start = end = 0;

        Map<Character, Integer> map = new HashMap<>();

        while(end < str.length()) {
            char currentChar = str.charAt(end);
            map.put(currentChar, map.getOrDefault(currentChar, 0) + 1);

            while(map.size() > k) {
                char charFromFront = str.charAt(start);
                map.put(charFromFront, map.get(charFromFront) - 1);

                if(map.get(charFromFront) == 0) {
                    map.remove(charFromFront);
                }
                start++;
            }

            maxLength = Math.max(end - start + 1, maxLength);
            end++;
        }
        System.out.println(str.substring(start,end));
        return maxLength;
    }
    /*https://www.geeksforgeeks.org/minimum-cost-make-two-strings-identical/*/
    static int LCS(String X,String Y,int m,int n){
        int[][] L = new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0 || j==0)
                    L[i][j]=0;
                else if(X.charAt(i-1)==Y.charAt(j-1))
                    L[i][j]=L[i-1][j-1]+1;
                else
                    L[i][j]=Math.max(L[i-1][j],L[i][j-1]);
            }
        }
        return L[m][n];
    }
    static int minCosttomakestringsidentical_by_deleting(String S1,String S2,int costX,int costY){
        int len_LCS=LCS(S1,S2,S1.length(),S2.length());

        return costX*(S1.length()-len_LCS)+costY*(S2.length()-len_LCS);
    }

    /*https://www.geeksforgeeks.org/minimum-number-of-manipulations-required-to-make-two-strings-anagram-without-deletion-of-character/#:~:text=CamelCase%20Pattern%20Matching-,Minimum%20Number%20of%20Manipulations%20required%20to%20make,Anagram%20Without%20Deletion%20of%20Character&text=Given%20two%20strings%20s1%20and,anagram%20without%20deleting%20any%20character.*/
    /*https://medium.com/@nanofaroque/minimum-number-of-steps-to-make-two-strings-anagram-3488cff436db*/
    static int CountManipulationstomakestringsIdentical(String s1,String s2){
        int count=0;
        int char_count[]=new int[26];
        //iterate through first string
        for (int i=0;i<s1.length();i++){
            char_count[s1.charAt(i)-'a']++;
        }
        //iterate through second strings and if char is not found,then increase the count
        for (int i=0;i<s2.length();i++){
            if (char_count[s2.charAt(i) - 'a']--<=0)
                count++;
        }

        return count;
    }

    /*https://www.geeksforgeeks.org/anagram-substring-search-search-permutations/
    * https://www.youtube.com/watch?v=fYgU6Bi2fRg
    * https://www.geeksforgeeks.org/count-total-anagram-substrings/*/
    static boolean compare(char arr1[], char arr2[])
    {
        int MAX=26;
        for (int i = 0; i < MAX; i++)
            if (arr1[i] != arr2[i])
                return false;
        return true;
    }
    static void allAnagramsinlist(String txt,String pat){
        int MAX=26;
        int M = pat.length();
        int N = txt.length();

        // countP[]:  Store count of all
        // characters of pattern
        // countTW[]: Store count of current
        // window of text
        char[] countP = new char[MAX];
        char[] countTW = new char[MAX];
        for (int i = 0; i < M; i++)
        {
            (countP[pat.charAt(i)-'a'])++;
            (countTW[txt.charAt(i)-'a'])++;
        }

        // Traverse through remaining characters
        // of pattern
        for (int i = M; i < N; i++)
        {
            // Compare counts of current window
            // of text with counts of pattern[]
            if (compare(countP, countTW))
                System.out.println("Found at Index " +
                        (i - M));

            // Add current character to current
            // window
            (countTW[txt.charAt(i)-'a'])++;

            // Remove the first character of previous
            // window
            countTW[txt.charAt(i-M)-'a']--;
        }

        // Check for the last window in text
        if (compare(countP, countTW))
            System.out.println("Found at Index " +
                    (N - M));
    }
    /*https://www.geeksforgeeks.org/given-a-string-find-its-first-non-repeating-character/
    *TC-O(n^2) */
    static int FirstNonRepeatingchar(String str){
        char count[]=new char[256];
        for (int i = 0; i < str.length(); i++)
            count[str.charAt(i)]++;
        int index = -1;
        for (int i = 0; i < str.length(); i++) {
            if (count[str.charAt(i)] == 1) {
                index = i;
                break;
            }
        }
        return index;
    }

    /*This is method 2 for above approach
    * TC-O(n) and SC-O(n)
    * make hashmap and store both the first occurence and total count of occurence,
    * then just scan the hashmap instead and the character which has least value of first occurence and frequency value as unity.  */

    static class CountIndex{
        int count,index;
        /*constructor for first occurence*/
        public CountIndex(int index){
            this.count=1;
            this.index=index;
        }
        public void incCount(){
            this.count++;
        }
    }
    static int firstNonRepeating(String str){
        HashMap<Character, CountIndex> hm
                = new HashMap<Character, CountIndex>(256);

        for (int i = 0; i < str.length(); i++) {
            // If character already occurred,
            if (hm.containsKey(str.charAt(i))) {
                // updating count
                hm.get(str.charAt(i)).incCount();
            }

            // If it's first occurrence, then store
            // the index and count = 1
            else {
                hm.put(str.charAt(i), new CountIndex(i));
            }
        }
        int result = Integer.MAX_VALUE;
        for (Map.Entry<Character, CountIndex> entry : hm.entrySet())
        {
            int c=entry.getValue().count;
            int ind=entry.getValue().index;
            if(c==1 && ind < result)
            {
                result=ind;
            }
        }
        return result;
    }

    /*https://www.geeksforgeeks.org/kth-non-repeating-character/*/
    // Returns index of k'th non-repeating character in
    // given string str[]
    static int kthNonRepeating(String str, int k)
    {
        int MAX_CHAR=256;
        int n = str.length();

        // count[x] is going to store count of
        // character 'x' in str. If x is not present,
        // then it is going to store 0.
        int[] count = new int[MAX_CHAR];

        // index[x] is going to store index of character
        // 'x' in str.  If x is not  present or x is
        // repeating, then it is going to store  a value
        // (for example, length of string) that cannot be
        // a valid index in str[]
        int[] index = new int[MAX_CHAR];

        // Initialize counts of all characters and indexes
        // of non-repeating  characters.
        for (int i = 0; i < MAX_CHAR; i++)
        {
            count[i] = 0;
            index[i] = n;  // A value more than any index
            // in str[]
        }

        // Traverse the input string
        for (int i = 0; i < n; i++)
        {
            // Find current character and increment its
            // count
            char x = str.charAt(i);
            ++count[x];

            // If this is first occurrence, then set value
            // in index as index of it.
            if (count[x] == 1)
                index[x] = i;

            // If character repeats, then remove it from
            // index[]
            if (count[x] == 2)
                index[x] = n;
        }

        // Sort index[] in increasing order.  This step
        // takes O(1) time as size of index is 256 only
        Arrays.sort(index);

        // After sorting, if index[k-1] is value, then
        // return it, else return -1.
        return (index[k-1] != n)? index[k-1] : -1;
    }

    /*https://www.geeksforgeeks.org/reverse-words-in-a-given-string/*/
    static void reverseString(String s[]){
        String ans = "";
        for (int i = s.length - 1; i >= 0; i--) {
            ans += s[i] + " ";
        }
        System.out.println("Reversed String:");
        System.out.println(ans.substring(0, ans.length() - 1));
    }

    /*https://www.geeksforgeeks.org/first-string-from-the-given-array-whose-reverse-is-also-present-in-the-same-array/*/
    static String getReversed(String[] words, int length) {

        // Hashmap to store word as we traverse
        Map<String, Boolean> reversedWordMap = new HashMap<>();

        for (String word : words) {
            StringBuilder reverse = new StringBuilder(word);
            String reversed = reverse.reverse().toString();

            // check if reversed word exists in map.
            Boolean exists = reversedWordMap.get(reversed);
            if (exists != null && exists.booleanValue()) {
                return reversed;
            } else {
                // else put the word in map
                reversedWordMap.put(word, true);
            }

        }
        return "-1";
    }

    /*https://www.geeksforgeeks.org/longest-subsequence-equal-numbers-0-1/*/
    static int lengthofLargestSubsequenceof1and0(int arr[],int n){
        int countzero = 0, countone = 0;

        // traverse binary array and count
        // zeros and ones
        for (int i = 0; i < n; i++)
            if (arr[i] == 1)
                countone++;
            else
                countzero++;

        return Math.min(countone, countzero) * 2;
    }

    /*https://www.youtube.com/watch?v=9ZyLjjk536U&list=PLjKHUvMwTztuCoR0Tje43LLRkNiqbeIZv&index=23&t=0s
    * https://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/*/
    static int largestsubarrayof1and0(int arr[], int n){
        // Creates an empty hashMap hM
        HashMap<Integer, Integer> hM
                = new HashMap<Integer, Integer>();
        // Initialize sum of elements
        int sum = 0;
        // Initialize result
        int max_len = 0;
        int ending_index = -1;
        int start_index = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = (arr[i] == 0) ? -1 : 1;
        }
        // Traverse through the given array
        for (int i = 0; i < n; i++) {
            // Add current element to sum
            sum += arr[i];
            // To handle sum=0 at last index
            if (sum == 0) {
                max_len = i + 1;
                ending_index = i;
            }
            // If this sum is seen before,
            // then update max_len if required
            if (hM.containsKey(sum + n)) {
                if (max_len < i - hM.get(sum + n)) {
                    max_len = i - hM.get(sum + n);
                    ending_index = i;
                }
            }
            else // Else put this sum in hash table
                hM.put(sum + n, i);
        }
        for (int i = 0; i < n; i++) {
            arr[i] = (arr[i] == -1) ? 0 : 1;
        }
        int end = ending_index - max_len + 1;
        System.out.println(end + " to " + ending_index);
        return max_len;
    }
    static int longestsubstringwithequal1sand0s(String str){
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        // Initially difference is 0;
        map. put(0, -1);
        int res =0;
        int count_0 = 0, count_1 = 0;
        for(int i=0; i<str.length();i++)
        {
            // Keep track of count of 0s and 1s
            if(str.charAt(i)=='0')
                count_0++;
            else
                count_1++;
            // If difference between current counts
            // already exists, then substring between
            // previous and current index has same
            // no. of 0s and 1s. Update result if this
            // substring is more than current result.
            if(map.containsKey(count_1-count_0))
                res = Math.max(res, (i - map.get(count_1-count_0)));
                // If the current difference is seen first time.
            else
                map.put(count_1-count_0,i);
        }
        return res;
    }

    public  static void main(String[] args){

        String str = "acacacacacacac";
        int n = str.length();
        char x = 'a', y = 'c';

        System.out.println ("Count = " +
                substr_startwithX_endwithY(str, n, x, y));
        String str1="AACD";
        String str2="ACDA";
        if(areRotations(str1,str2))
            System.out.println("are rotations");
        else
            System.out.println("not rotations of each other");

        String str3 ="ABC";
        String S="";
        int n1 =str3.length();
        //permute(str3,0,n1-1);
        System.out.println("abc  to"+"  cba  "+check("abc","cba"));
        System.out.println(getInsertedCharacter("fhsvbfdbsrgnjnvklnvgafnvjksdfnjvksfvsjbnfdjnvdjfnvksdjfnvkjsfnv", "Zfhsvbfdbsrgnjnvklnvgafnvjksdfnjvksfvsjbnfdjnvdjfnvksdjfnvkjsfnv"));

        String s1="XXYM";
        String s2="XXZT";
        String s3="XXXZXYTM";
        System.out.println(isInterleaved(s1.toCharArray(),s2.toCharArray(),s3.toCharArray()));
        System.out.println(patternCount_101("1001ab010abc01001"));

        String searchstr = "donutsandwafflemakemehungry";
        String pattern = "flea";
        System.out.println(minWindow(searchstr,pattern));

        System.out.println(minWindow_ofdistinctchars_of_itself("aabcbcdbca"));
        int res = count_substrs_with_constraints("aabbcc",2);
        System.out.println(res);
        allAnagramsinlist("bacdgabcda","abcd");
    }
}
