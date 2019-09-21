package com.company;

public class encrpt {

    static StringBuffer caeser(String text,int s){
        StringBuffer res = new StringBuffer();
        for(int i=0;i<text.length();i++){
            if(Character.isUpperCase(text.charAt(i))){
                char ch = (char)(((int)text.charAt(i)+s-65)%26+65);
                res.append(ch);
            }
            else if(Character.isLowerCase(text.charAt(i))){
                char ch = (char)(((int)text.charAt(i)+s-97)%26+97);
                res.append(ch);
            }
        }
        return res;
    }

    static String generatekey(String str,String key){
        int x=str.length();
        for(int i=0;i<x;i++){
            if(x==i)
                i=0;
            if(key.length()==str.length())
                break;
            else{
            key+=(key.charAt(i));}
        }
        return key;
    }

    static String vignere(String str,String keyword){
        String cipher ="";
        for(int i=0;i<str.length();i++){
            int x =(str.charAt(i)+keyword.charAt(i))%26;
            x+='A';
            cipher+=(char)(x);
        }
        return cipher;
    }

    public static void main(String[] args){
        System.out.println("caeser cipher\n"+caeser("ATTACK",4));
        String S="ATTACK";
        String Key="ABC";
        String keyword= generatekey(S,Key);
        System.out.println("vignere cipher\n"+vignere(S,keyword));
    }
}
