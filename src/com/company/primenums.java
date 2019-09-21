package com.company;

import java.util.ArrayList;

//reason for m=n/2 because a prime number is never divisible by a number greater than half of it
public class primenums {
    static ArrayList<Integer> primenumbetween_2no(int n1,int n2){
        int flag;
        ArrayList<Integer> l = new ArrayList<>();
        for(int i=n1;i<=n2;i++){
            if(i==1|| i==0)
            { continue;}
            flag =1;
            for(int j=2;j<i/2;++j){
                if(i%j==0){
                    flag=0;
                    break;
                }
            }
            if(flag==1){
                l.add(i);
            }
        }

        return l;
    }
    public static void main(String[] args){
        int i,m=0,flag=0;
        int n=3;//it is the number to be checked
        m=n/2;
        if(n==0||n==1){
            System.out.println(n+" is not prime number");
        }else{
            for(i=2;i<=m;i++){
                if(n%i==0){
                    System.out.println(n+" is not prime number");
                    flag=1;
                    break;
                }
            }
            if(flag==0)  { System.out.println(n+" is prime number"); }
        }//end of else
        System.out.println(primenumbetween_2no(1,50));
    }
}
