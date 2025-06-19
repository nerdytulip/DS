package com.company.LinkedListQuestions;

import java.util.Deque;
import java.util.LinkedList;

class KeyValuePair<K, V> {
    K key;
    V value;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache<K, V> {

    /***
     *https://leetcode.com/problems/lru-cache/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=9RQvjzszwsg
     */

    private Deque<KeyValuePair<K, V>> list;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.list = new LinkedList<>();
    }

    public void put(K k, V v) {
        KeyValuePair<K, V> existingPair = getItem(k);
        if (existingPair != null) {
            list.remove(existingPair); // remove old
        } else if (list.size() == capacity) {
            list.removeLast(); // remove LRU
        }

        list.addFirst(new KeyValuePair<>(k, v)); // insert MRU
    }

    public V get(K k) {
        KeyValuePair<K, V> pair = getItem(k);
        if (pair != null) {
            list.remove(pair);
            list.addFirst(pair);
            return pair.value;
        }
        return null;
    }

    private KeyValuePair<K, V> getItem(K k) {
        for (KeyValuePair<K, V> pair : list) {
            if (pair.key.equals(k)) {
                return pair;
            }
        }
        return null;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
