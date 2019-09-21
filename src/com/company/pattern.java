package com.company;

public class pattern {
    static void printpattern(int nr){
        int h=1;
        for(int i=1;i<=nr;i++){
            for(int j=1;j<=i;j++,h++){
                System.out.print(h);
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args){
        int no_rows=5;
        printpattern(no_rows);
    }
}
