package Matchr_App;


// USE ONLY WHEN NOT RUNNING PROGRAM WITH UI
public class Main {

    public static void main(String[] args) {
        // updates the inventory to populate data (change after implementing database)
        Inventory.updateInventory();

        // initializes new session and returns the user with saved preferences
        Session session = new Session();
        User user = session.initSession();

        // search inventory for matching racquet for the user
        ItemFinder<Racquet> racquetFinder = new ItemFinder<>(user);
        racquetFinder.rankItems(Inventory.getInventory());

        // display the recommendation
        Racquet result = racquetFinder.getTopResult();
        System.out.println("\nRecommended for you:\n" + result);
    }
}
