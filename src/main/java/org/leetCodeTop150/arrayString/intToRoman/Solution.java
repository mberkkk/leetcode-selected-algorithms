package org.leetCodeTop150.arrayString.intToRoman;

import java.util.*;

public class Solution {
    public String intToRoman(int num) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1000, "M");
        treeMap.put(900, "CM");
        treeMap.put(500, "D");
        treeMap.put(400, "CD");
        treeMap.put(100, "C");
        treeMap.put(90, "XC");
        treeMap.put(50, "L");
        treeMap.put(40, "XL");
        treeMap.put(10, "X");
        treeMap.put(9, "IX");
        treeMap.put(5, "V");
        treeMap.put(4, "IV");
        treeMap.put(1, "I");

        int n = treeMap.floorKey(num);
        if (n == num){
            return treeMap.get(num);
        }
        return treeMap.get(n) + intToRoman(num - n);


    }
}