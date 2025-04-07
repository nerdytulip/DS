package com.company.ArrayQuestions;

public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int placedFlowerCount = 0;
        for(int i=0;i<flowerbed.length;i++){
            if(flowerbed[i] == 0){
                boolean isLeftPlotEmpty = i == 0 || flowerbed[i-1] == 0;
                boolean isRightPlotEmpty = i == flowerbed.length - 1 || flowerbed[i+1] == 0;

                if(isLeftPlotEmpty && isRightPlotEmpty){
                    flowerbed[i] = 1;
                    placedFlowerCount++;
                }
            }
        }
        return placedFlowerCount>=n;
    }
}
