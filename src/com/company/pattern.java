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

        h=h-1;
        for (int i=nr;i>0;i--){
            for (int j=1;j<=i;j++,h--){
                System.out.print(h);
            }
            System.out.print("\n");
        }
    }

    //multiply without using multiply,division,bitwise operator,and no loops
    //so we recursion
    static int multiply(int x,int y){
        if (y==0)
            return 0;
        if (y>0)
            return (x+multiply(x,y-1));
        if(y<0)
            return -multiply(x,-y);
        return -1;
    }

    public static void main(String[] args){
        int no_rows=5;
        printpattern(no_rows);
    }
}
