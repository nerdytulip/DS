package com.company.DP.DPPrefixBased;

public class TrappingRainWater_VolumeOfHistogram {

    private class HistogramData{
        private int height;
        private int leftMaxIndex = -1;
        private int rightMaxIndex = -1;

        public HistogramData(int height) {
            this.height = height;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getLeftMaxIndex() {
            return leftMaxIndex;
        }

        public void setLeftMaxIndex(int leftMaxIndex) {
            this.leftMaxIndex = leftMaxIndex;
        }

        public int getRightMaxIndex() {
            return rightMaxIndex;
        }

        public void setRightMaxIndex(int rightMaxIndex) {
            this.rightMaxIndex = rightMaxIndex;
        }
    }

    public int trap(int[] height) {
        int start = 0;
        int end = height.length-1;

        HistogramData[] data = createHistogram(height);
        int max = data[0].getRightMaxIndex();
        int leftVolume = subgraphVolume(data,start,max,true);
        int rightVolume = subgraphVolume(data,max,end,false);

        return leftVolume+rightVolume;
    }

    private int subgraphVolume(HistogramData[] histogram, int start, int end, boolean isLeft) {
        if(start>=end){
            return 0;
        }

        int sum = 0;
        if(isLeft){
            int max = histogram[end-1].getLeftMaxIndex();
            sum += borderedVolume(histogram,max,end);
            sum += subgraphVolume(histogram,start,max,isLeft);
        }else{
            int max = histogram[start+1].getRightMaxIndex();
            sum += borderedVolume(histogram,start,max);
            sum += subgraphVolume(histogram,max,end,isLeft);
        }

        return sum;
    }

    private int borderedVolume(HistogramData[] histogram, int start, int end) {
        if(start>=end){
            return 0;
        }

        int min = Math.min(histogram[start].getHeight(),histogram[end].getHeight());
        int sum = 0;
        for(int i = start+1;i<end;i++){
            sum += min - histogram[i].getHeight();
        }

        return sum;
    }

    private HistogramData[] createHistogram(int[] height) {
        HistogramData[] histogram = new HistogramData[height.length];

        for(int i=0;i< height.length;i++){
            histogram[i] = new HistogramData(height[i]);
        }

        //set left max index
        int maxIndex = 0;
        for(int i=0;i<height.length;i++){
            if(height[maxIndex]<height[i]){
                maxIndex = i;
            }
            histogram[i].setLeftMaxIndex(maxIndex);
        }

        //set right max index
        maxIndex = histogram.length-1;
        for(int i=histogram.length-1;i>=0;i--){
            if(height[maxIndex]<height[i]){
                maxIndex = i;
            }
            histogram[i].setRightMaxIndex(maxIndex);
        }

        return histogram;
    }
}
