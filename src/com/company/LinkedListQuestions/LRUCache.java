package com.company.LinkedListQuestions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class LRUCache {

    /***
     *https://leetcode.com/problems/lru-cache/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=9RQvjzszwsg
     */

    private class LinkedListNode{
        int key;
        int value;

        LinkedListNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Map<Integer,LinkedListNode> cache;
    private LinkedList<LinkedListNode> lruList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        lruList = new LinkedList<>();
    }

    public int get(int key) {
        if(cache.containsKey(key)){
            LinkedListNode node = cache.get(key);
            lruList.remove(node);
            lruList.addFirst(node);

            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)){
            LinkedListNode node = cache.get(key);
            lruList.remove(node);
            // update the new value
            node.value = value;
            lruList.addFirst(node);
        }else{
            // we have to first check if it is beyond the capacity, remove last
            if(cache.size()>=capacity){
                LinkedListNode node = lruList.removeLast();
                cache.remove(node.key);
            }

            LinkedListNode node = new LinkedListNode(key,value);
            lruList.addFirst(node);
            cache.put(key,node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
