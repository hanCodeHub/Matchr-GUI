package com.han;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

// Generic class that will find any type of Racquet
public class RacquetFinder<T extends Racquet> {

    // comparator used for ordering racquets based on matchIndex
    public static class RacquetComparator implements Comparator<Racquet> {
        @Override
        public int compare(Racquet x, Racquet y) {
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
    private PriorityQueue<T> results;   // stores matched racquets




}
