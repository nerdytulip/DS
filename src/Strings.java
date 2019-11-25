import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
    public static void main(String[] args){

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
    }
}
