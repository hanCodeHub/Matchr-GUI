package Matchr_App;

import Matchr_Models.RacquetModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;


// Can load Recommendable objects to a buffer and read from it simultaneously
// Postcondition: This is a contained experiment that uses classes from the main program,
    // but does not alter its flow of execution nor creates a dependency
public class ItemBuffer {


    private static List<Racquet> buffer = Collections.synchronizedList(new ArrayList<>());
    private static ReentrantLock lock = new ReentrantLock();
    private static boolean lazy = true;

    // Utility variables
    private static final String BLUE = "\u001B[34m";    // color for loader
    private static final String GREEN = "\u001B[32m";   // color for reader
    private static Random random = new Random();        // random number generator
    private static Boolean finished = false;            // tracks the end of loading

    public static void main(String[] args) {

        RacquetModel model = new RacquetModel();
        List<Racquet> inventory = model.getAllRacquets();
        System.out.println(inventory);

        ItemLoader<Racquet> loader = new ItemLoader<>(buffer, inventory);
        Thread loaderThread = new Thread(loader);

        ItemReader<Racquet> reader = new ItemReader<>(buffer, loaderThread);

        loaderThread.start();
        new Thread(reader).start();
    }


    private static class ItemReader<Item extends Recommendable> implements Runnable {
        private List<Item> buffer;
        private static Thread loaderThread;

        public ItemReader(List<Item> buffer, Thread loaderThread) {
            this.buffer = buffer;
            ItemReader.loaderThread = loaderThread;
        }

        public void batchRead() {
            try {
                loaderThread.join();
                for (Item item : buffer) {

                    System.out.println(BLUE + "Reader displaying: ");
                    System.out.println(item);
                }
            } catch (InterruptedException e) {
                System.out.println(BLUE + "Reading interrupted: " + e.getMessage());
            }
        }

        public void fetchRead(int latency) {

            while (true) {

                try {
                    Thread.sleep(latency);

                    lock.lock();

                    if (buffer.isEmpty()) continue;

                    System.out.println(BLUE + "Reader displaying: ");
                    System.out.println(buffer.remove(0));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                if (finished) break;
            }

            // read the remaining objects after the buffer has finished loading
            while (!buffer.isEmpty()) {
                System.out.println(BLUE + "Reader displaying: ");
                System.out.println(buffer.remove(0));
            }
        }

        // reads items from buffer into console one by one
        @Override
        public void run() {
            fetchRead(1000);
        }

    }

    private static class ItemLoader<Item extends Recommendable> implements Runnable {
        private List<Item> buffer;
        private List<Item> inventory;

        public ItemLoader(List<Item> buffer, List<Item> inventory) {
            this.buffer = buffer;
            this.inventory = inventory;
        }

        public void batchLoad() {
            for (int i = 0; i < inventory.size(); i++) {

                // sleeps thread to simulate the loading time of item
                try {
                    Thread.sleep(random.nextInt(1000));

                    buffer.add(inventory.get(i));

                    System.out.println(GREEN + "Item " + i + " loaded to buffer");
                } catch (InterruptedException e) {
                    System.out.println(GREEN + "Loading interrupted: " + e.getMessage());
                }

            }
        }

        public void lazyLoad() {
            for (int i = 0; i < inventory.size(); i++) {

                // sleeps thread for up to 1s each iteration to simulate the loading time
                try {
                    Thread.sleep(random.nextInt(1000));

                    lock.lock();
                    buffer.add(inventory.get(i));
                    System.out.println(GREEN + "Item " + (i + 1) + " loaded to buffer");

                    if (i + 1 == inventory.size())
                        finished = true;

                } catch (InterruptedException e) {
                    System.out.println(GREEN + "Loading interrupted: " + e.getMessage());
                } finally {
                    lock.unlock();
                }

            }

        }

        // loads items from inventory into buffer one by one
        @Override
        public void run() {
            lazyLoad();
        }

    }
}
