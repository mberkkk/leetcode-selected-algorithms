package org.leetCodeTop150.arrayString.InsertDeleteGetRandom;

import java.util.*;

class RandomizedSet {
    private Map<Integer, Integer> map;
    private List<Integer> list;
    private Random random;

    public RandomizedSet() {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
        this.random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        int lastIndexElement  = list.getLast();
        list.set(index, lastIndexElement);
        list.removeLast();
        map.put(lastIndexElement, index);
        map.remove(val);
        return true;



    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}



/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */