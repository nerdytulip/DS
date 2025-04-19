package com.company.PrefixSum;

public class FindHighestAltitude {
    public int largestAltitude(int[] gain) {
        int currAltitude = 0;
        int maxAltitude = currAltitude;

        for(int altitudeGain:gain){
            currAltitude+=altitudeGain;
            maxAltitude = Math.max(maxAltitude,currAltitude);
        }
        return maxAltitude;
    }
}
