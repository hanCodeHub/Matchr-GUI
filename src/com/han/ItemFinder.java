package com.han;


import java.util.Comparator;
import java.util.PriorityQueue;

// Generic class that will find any type of Racquet
public class ItemFinder<T extends Recommendable> {

    // comparator class used for ordering racquets based on matchIndex
    public class ItemComparator implements Comparator<Recommendable> {
        @Override
        public int compare(Recommendable x, Recommendable y) {
            // smaller matchIndex rank higher in queue
            if (x.getMatchIndex() < y.getMatchIndex())
                return -1;
            else if (x.getMatchIndex() > y.getMatchIndex())
                return 1;

            return 0;
        }
    }

    // properties for recommending matching racquets
    private int listings;               // number of recommended listings
    private PriorityQueue<Racquet> results;   // stores matched racquets




}
