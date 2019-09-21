package com.company;

import java.util.ArrayList;

public class primefactors {
    static int floorSqrt(int x){
        if(x==0 || x==1)
            return x;
        int i=1,result=1;
        while(result<=x){
            i++;
            result=i*i;
        }
        return i-1;
    }
    static ArrayList<Integer> primeFactors(int n)
    {
        ArrayList<Integer> list = new ArrayList<>();
        // Print the number of 2s that divide n
        while (n%2==0)
        {
            //System.out.print(2 + " ");
            list.add(2);
            n /= 2;
        }

        // n must be odd at this point.  So we can
        // skip one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i+= 2)
        {
            // While i divides n, print i and divide n
            while (n%i == 0)
            {
                //System.out.print(i + " ");
                list.add(i);
                n /= i;
            }
        }

        // This condition is to handle the case whien
        // n is a prime number greater than 2
        if (n > 2)
            //System.out.print(n);
            list.add(n);

     return list;
    }
    public static void main(String[] args){
        int n = 23;
        System.out.println(primeFactors(n));
    }
}
