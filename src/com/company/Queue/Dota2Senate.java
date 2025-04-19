package com.company.Queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class Dota2Senate {
    public String predictPartyVictory(String senate) {
        Deque<Integer> radiant = new ArrayDeque<>();
        Deque<Integer> dire = new ArrayDeque<>();

        for(int i = 0;i < senate.length();i++ ){
            if(senate.charAt(i) == 'R'){
                radiant.add(i);
            }else{
                dire.add(i);
            }
        }

        while(!radiant.isEmpty() && !dire.isEmpty()){
            int radiantPosition = radiant.poll();
            int direPosition = dire.poll();

            if(radiantPosition<direPosition){
                radiant.addLast(radiantPosition+senate.length());
            }else{
                dire.addLast(direPosition+senate.length());
            }
        }

        return radiant.isEmpty() ? "Dire" : "Radiant";
    }

}
