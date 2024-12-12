package com.company.DP;

public class MinCostForTickets {

    private int solveRec(int[] days, int[] costs,int index) {
        if(index>=days.length){
            return 0;
        }

        //1 day pass
        int option1 = costs[0] + solveRec(days,costs,index+1);

        //7 day pass
        int i;
        for(i=index;i<days.length && days[i]<days[index]+7)
    }


    public int mincostTickets(int[] days, int[] costs) {
        return solveRec(days,costs,0);
    }
}
