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

    public static void main(String[] args){

        String str = "acacacacacacac";
        int n = str.length();
        char x = 'a', y = 'c';

        System.out.print ("Count = " +
                substr_startwithX_endwithY(str, n, x, y));
    }
}
