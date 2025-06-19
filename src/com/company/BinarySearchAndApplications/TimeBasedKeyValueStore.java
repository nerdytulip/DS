package com.company.BinarySearchAndApplications;

import java.util.*;

public class TimeBasedKeyValueStore {
    /**
     * https://leetcode.com/problems/time-based-key-value-store/description/
     * https://www.youtube.com/watch?v=OWKIm52pUC4
     * */
    class TimeStampedValue{
        public int timestamp;
        public String value;

        public TimeStampedValue(int timestamp,String value){
            this.timestamp = timestamp;
            this.value = value;
        }
    }
    class TimeMap {
        Map<String, List<TimeStampedValue>> entriesByKey;

        public TimeMap() {
            entriesByKey = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if(!entriesByKey.containsKey(key)){
                entriesByKey.put(key,new ArrayList<>());
            }

            List<TimeStampedValue> values = entriesByKey.get(key);
            values.add(new TimeStampedValue(timestamp,value));
        }

        public String get(String key, int timestamp) {
            if(!entriesByKey.containsKey(key)){
                return "";
            }

            List<TimeStampedValue> values = entriesByKey.get(key);
            Optional<TimeStampedValue> timeStamp = binarySearchTimestamp(values,timestamp);

            if(timeStamp.isEmpty()){
                return "";
            }

            return timeStamp.get().value;
        }

        private Optional<TimeStampedValue> binarySearchTimestamp(List<TimeStampedValue> arr, int target){
            int left = 0;
            int right = arr.size()-1;
            int matchIndex = -1;

            while(left<=right){
              int mid = left + (right - left)/2;
              TimeStampedValue curr = arr.get(mid);

              if(curr.timestamp <= target){
                  matchIndex = mid;
                  left = mid+1;
              }else{
                  right = mid-1;
              }
            }

            if(matchIndex == -1){
                return Optional.empty();
            }

            return Optional.of(arr.get(matchIndex));
        }
    }
}
