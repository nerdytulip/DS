package com.company;import java.util.*;
import java.io.*;
import java.lang.*;


public class caterpillar {

    public static void main(String[] args) {
      Scanner inputS = new Scanner(System.in);
        HashMap<String,Integer> map = new HashMap<>();
      while(inputS.hasNextLine())
      {
          String input = inputS.nextLine();
          String[] str = input.split(" ");
          int count = Integer.parseInt(str[0]);
          String dir =str[1];
          //System.out.println("count\n"+count+"dir\n"+dir
          map.put(dir,count);
          if (inputS.nextLine().equals("x")){
                   System.exit(0);
            }

      }
      int n =map.size();
      char matrix[][]= new char[n][n];
      for(int i=0;i<n;i++){
          if(map.containsKey("N")||map.containsKey("S")){
              int m = map.get("N");
          }
      }

    }

}
