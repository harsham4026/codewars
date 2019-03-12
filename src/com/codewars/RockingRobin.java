package com.codewars;

import java.util.*;

/**
 * https://www.codewars.com/kata/rockin-robin/train/java
 */
public class RockingRobin implements Iterator<Integer> {
    private List<Iterator<Integer>> collections;
    private int i = 0;

    public RockingRobin(Collection<Iterator<Integer>> collections) {
        this.collections = new ArrayList<>(collections);
        this.i = 0;
    }

    @Override
    public boolean hasNext() {
        return collections.stream().anyMatch(Iterator::hasNext);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (!collections.get(i).hasNext()) {
            i = (i + 1) % collections.size();
        }
        int res = collections.get(i).next();
        i = (i + 1) % collections.size();
        return res;
    }
}