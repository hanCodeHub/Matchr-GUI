package Matchr_App;

import java.util.*;


// Generic class that will find any type of Recommendable item
public class ItemFinder<T extends Recommendable> {

    // comparator class used for ordering racquets based on matchIndex
    private static class ItemComparator implements Comparator<Recommendable> {
        @Override
        public int compare(Recommendable x, Recommendable y) {

            // sorts by smaller matchIndex first
            if (x.getMatchIndex() < y.getMatchIndex())
                return -1;
            else if (x.getMatchIndex() > y.getMatchIndex())
                return 1;

            return 0;
        }
    } // END INNER CLASS


    // ItemFinder class
    private PriorityQueue<T> orderedItems;   // stores items in order of preference
    private User user;                  // current user to find items for

    public ItemFinder(User user) {
        this.user = user;
        // matchIndex comparator used as key in PriorityQueue
        this.orderedItems = new PriorityQueue<>(new ItemComparator());
    }


    // ranks items by priority in the results PQueue
    public void rankItems(List<T> items) {

        // filter out other brands if user has brand preference
        if (!user.getBrand().isBlank())
            filterBrand(items);

        // save match index on each item and add item to PQueue
        items.forEach(item -> {
            item.calcMatchIndex(user);
            orderedItems.add(item);
        });

    }

    // filters items by specific brand - other brands removed from consideration
    private void filterBrand (List<T> items) {

        // check if user preferred brand exists amongst available items
        String userBrand = user.getBrand().toLowerCase();
        boolean brandExists = items.stream()
                .anyMatch(item -> item.getBrand().toLowerCase().equals(userBrand));

        // remove items with other brands
        if (brandExists)
            items.removeIf(item -> !item.getBrand().toLowerCase().equals(userBrand));

    }

    // returns the top result
    public T getTopResult() {
        return orderedItems.peek();
    }



    // USED IN LATER BUILDS
    // returns a list of top results of a given quantity
    public List<T> getResults(int quantity) {

        // get iterator for topResults PQueue and List for output
        Iterator<T> resultsIter = orderedItems.iterator();
        List<T> results = new ArrayList<>(quantity);

        // return the next specified number of results in the PQueue
        for (int i = 0; i < quantity; i++) {
            if (resultsIter.hasNext())
                results.add(resultsIter.next());
        }
        return results;
    }

}
