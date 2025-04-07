package com.company.ArrayQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GreatestNumberOfCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = Arrays.stream(candies).max().orElse(0);

        List<Boolean> result = new ArrayList<>();
        for (int candy : candies) {
            if (candy + extraCandies >= maxCandies) {
                result.add(true);
            }else{
                result.add(false);
            }
        }
        return result;
    }
}
