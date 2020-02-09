package com.han;


import java.util.Comparator;
import java.util.PriorityQueue;

// Generic class that will find any type of Recommendable item
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
    } // END INNER CLASS


    private Inventory inventory;        // inventory of items to search through
    private PriorityQueue<T> results;   // stores items in order of preference

    public ItemFinder(Inventory inventory) {
        this.inventory = inventory;
    }

    
}
