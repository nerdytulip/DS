package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


public class directi {
     static final int no_of_chars=256;
    /*static class util{
        String res="";
    }*/
    static String removeChar(String s,char c){
        int j,count=0,n=s.length();
        char t[]=s.toCharArray();
        for(int i=j=0;i<n;i++){
            if (t[i]!=c){
                t[j++]=t[i];
            }
            else {
                count++;
            }
        }
        while (count>0){
            t[j++]='\0';
            count--;
        }
        //System.out.println(t);
        String sres = new String(t);
        return sres;
    }

    static String minimum_window_containing_allcharOfA(String A,String S){
        int len1=S.length();
        int len2=A.length();
        if(len1<len2){
            return "";
        }
        int hash_pat[]=new int[no_of_chars];
        int hash_str[]=new int[no_of_chars];
        for(int i=0;i<len2;i++){
            hash_pat[A.charAt(i)]++;
        }
        int start=0,start_index=-1,min_len=Integer.MAX_VALUE;
        int count =0;
        for(int j=0;j<len1;j++){
            hash_str[S.charAt(j)]++;

            if(hash_pat[S.charAt(j)]!=0 && hash_str[S.charAt(j)]<=hash_pat[S.charAt(j)]){
                count++;
            }
            if(count == len2){
                while (hash_str[S.charAt(start)]>hash_pat[S.charAt(start)]||
                        hash_pat[S.charAt(start)]==0){
                    if (hash_str[S.charAt(start)]>hash_pat[S.charAt(start)])
                        hash_str[S.charAt(start)]--;
                    start++;
                }
                int len_window=j-start+1;
                if (min_len>len_window){
                    min_len=len_window;
                    start_index=start;
                }
            }
        }
        if (start_index==-1)
            return "";

        return S.substring(start_index,start_index+min_len);
    }

    static void solve_utility(String A,String S,int K){
        ArrayList<Character> l = new ArrayList<>();
        for(int i=0;i<S.length();i++){
            if(A.indexOf(S.charAt(i))==-1){
                if(!l.contains(S.charAt(i))){
                l.add(S.charAt(i));}
            }
        }
        int min_length=Integer.MAX_VALUE;
        //key=length ,value=removed character that caused it
        HashMap<Integer,Character> map = new HashMap<>();
        StringBuilder res= new StringBuilder();
        for (Character character : l) {
            String r = removeChar(S, character);
            String test=minimum_window_containing_allcharOfA(A,r);
            //System.out.println(test);
            if ((!test.equals("")) && test.length()>=K) {
                map.put(test.length(),character);
                //String temp = Character.toString(character);
                //res.append(temp);
            }
        }

        for(Integer key:map.keySet()){
            if(key<min_length)
                min_length=key;
        }
        String temp =Character.toString(map.get(min_length));
        res.append(temp);
        System.out.println(res);
        //return res.toString();
    }

   /* static void solve(String A,String S,int K){
        util m= new util();
        solve_utility(A,S,K,m);
        //String result=Integer.toString(m.res.length()) +"\n"+m.res;
        System.out.println(m.res);
    }*/

    //nlogn
    //https://www.geeksforgeeks.org/find-the-point-where-maximum-intervals-overlap/
    static String max_interval_overlap(int arrl[],int exit[],int n){
        Arrays.sort(arrl);
        Arrays.sort(exit);
        //guests_in guests at a time
        int guests_in=1,max_guests=1,time=arrl[0];
        int i=1,j=0;
        while(i<n && j<n){
            if(arrl[i]<=exit[j]){
                guests_in++;
                if(guests_in>max_guests){
                    max_guests=guests_in;
                    time=arrl[i];
                }
                i++;
            }
            else{
                guests_in--;
                j++;
            }
        }

        String S ="max num of guests ="+max_guests+"at time"+time;
        return S;
    }

    /*@https://www.geeksforgeeks.org/count-number-of-strings-made-of-r-g-and-b-using-given-combination/*/
    static int possibleString(int n,int r,int g,int b){
        int fact[]=new int[n+1];
        fact[0]=1;
        for(int i =1;i<=n;i++){
            fact[i]=fact[i-1]*i;
        }
        int remainder=n-(r+g+b);
        int sum =0;
        for(int i=0;i<=remainder;i++){
            for(int j=0;j<=remainder-i;j++){
                int k = remainder-(i+j);
                sum = sum + fact[n]/(fact[i+r]*fact[j+b]*fact[k+g]);
            }
        }
        return sum;
    }

    /*@https://www.geeksforgeeks.org/check-if-a-sorted-array-can-be-divided-in-pairs-whose-sum-is-k/*/
    static boolean canPairs_sortedarr_ofsumequalK(int arr[],int n,int k){
        if(n==1)
            return false;
        int l=0,r=n-1;
        while (l<r){
            if (arr[l]+arr[r]!=k){
                return false;
            }
            l++;
            r--;
        }

        return true;
    }

    /*@https://www.geeksforgeeks.org/check-if-an-array-can-be-divided-into-pairs-whose-sum-is-divisible-by-k/*/
    static boolean canPairs_ofsumdivbyk(int arr[],int k){
        if(arr.length%2==1)
            return false;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i =0;i<arr.length;i++){
            int rem=arr[i]%k;
            if(!map.containsKey(rem)){
                map.put(rem,0);
            }
            map.put(rem,map.get(rem)+1);
        }
        /*
        * the logic is as follows:
        * if 2*rem=k that means that this element is a multiple of k
        * else if rem ==0,that means that is completely divisible by k
        * else the number of occurences of remainder must be equal to number of
        * of occurences of k-rem*/

        for(int i=0;i<arr.length;i++){
            int rem = arr[i]%k;
            if(2*rem==k){
                if (map.get(rem)%2==1)
                    return false;
            }
            else if(rem==0){
                if(map.get(rem)%2==1)
                    return false;
            }
            else{
                if(map.get(k-rem)!=map.get(rem))
                    return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        int arrl[] ={1,2,10,5,5};
        int exit[]={4,5,12,9,12};
        int n1 =arrl.length;
        max_interval_overlap(arrl,exit,n1);

        int arr[]= {1,2,3,3,3,3,4,5};
        int k =6;
        int n = arr.length;
        if(canPairs_sortedarr_ofsumequalK(arr,n,k)){
            System.out.println("True");
        }else{
            System.out.println("False");
        }

        String S="hackerearthproblem";
        String A="eapth";
        int K=5;
       solve_utility(A,S,K);
       //System.out.println("anwer is"+result);
        //solve(A,S,K);
    }
}
