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

    static StringBuffer caeser_decrypt(String text,int s){
        StringBuffer res = new StringBuffer();
        for(int i=0;i<text.length();i++){
            if(Character.isUpperCase(text.charAt(i))){
                char ch = (char)(((int)text.charAt(i)-s-65)%26+65);
                res.append(ch);
            }
            else if(Character.isLowerCase(text.charAt(i))){
                char ch = (char)(((int)text.charAt(i)-s-97)%26+97);
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

    static String vignere_decrypt(String cipher,String keyword){
        String original_cipher="";
        for (int i=0;i<cipher.length();i++){
            int x=(cipher.charAt(i)-keyword.charAt(i)+26)%26;
            x+='A';
            original_cipher+=(char)(x);
        }
        return original_cipher;
    }

    static String substitution_encrypt(String key,String text){
        String cipher="";
        String[] keyspace=new String[key.length()];
        for (int i=0;i<key.length();i++){
            keyspace[i]=String.valueOf(key.charAt(i));
        }

        for (int i=0;i<text.length();i++){
            int index =text.charAt(i)-65;
            if (index >keyspace.length || index<0){
                cipher+=String.valueOf(text.charAt(i));
            }
            else{
                cipher+=keyspace[index];
            }
        }
        return cipher;
    }

    static String substitution_decrypt(String key,String cipher){
        String plaintext="";
        for (int i=0;i<cipher.length();i++){
            char character = cipher.charAt(i);
            int index = key.indexOf(character);
            int ascii=index +65;
            if (ascii<65 || ascii>90){
                plaintext+=String.valueOf(character);
            }
            else{
                plaintext+=String.valueOf((char)ascii);
            }
        }
        return plaintext;
    }

    public static void main(String[] args){
        StringBuffer result=caeser("ATTACK",4);
        System.out.println("caeser cipher :"+result);
        System.out.println("caeser decrypt :"+caeser_decrypt(result.toString(),4));
        String S="ATTACK";
        String Key="ABC";
        String keyword= generatekey(S,Key);
        String vignere_result=vignere(S,keyword);
        System.out.println("vignere cipher :"+ vignere_result);
        System.out.println("vignere decrypt :"+vignere_decrypt(vignere_result,keyword));
    }
}
