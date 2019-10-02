import java.util.ArrayList;

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

    static void permute(String str,int l,int r){
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
        permute(str3,0,n1-1);
    }
}
