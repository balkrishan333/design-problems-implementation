package design.impl;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {

    private int capacity;
    private Map<Integer, Integer> cache;
    private Deque<Integer> queue;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        queue = new LinkedList<>();
        System.out.print("null ");
    }

    public int get(int key) {
        int retValue = -1;
        if(cache.containsKey(key)) {
            retValue = cache.get(key);

            queue.remove(key);
            queue.offerLast(key);
        }
        System.out.print(retValue + " ");
        return retValue;
    }

    public void put(int key, int value) {
        System.out.print("null ");
        if(cache.containsKey(key)) {
            cache.put(key,value);
            queue.remove(key);
            queue.offerLast(key);
            return;
        }

        if(cache.size() < this.capacity) {
            cache.put(key, value);
            //  if(queue.contains(key)) {
            //      queue.remove(key);
            //  }
            queue.offerLast(key);
        } else {
            int removed = queue.pollFirst();
            cache.remove(removed);
            cache.put(key, value);
            queue.offerLast(key);
        }
    }

    public static void main(String... args) {
        LRUCache lRUCache = new LRUCache(2);

        lRUCache.get(2);    // return 1
        lRUCache.put(2, 6); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(1);    // returns -1 (not found)
        lRUCache.put(1, 5); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.put(1, 2);    // return -1 (not found)
        lRUCache.get(1);    // return 3
        lRUCache.get(2);    // return 4
    }

}
