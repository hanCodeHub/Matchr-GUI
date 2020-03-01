package Matchr_App;

import Matchr_Models.RacquetModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;


// Simulation program for using multithreading in a Producer Consumer design pattern
// Can load Recommendable objects to a buffer and read from it simultaneously
// This is a contained experiment that uses classes from the main program,
// but does not alter its flow of execution nor creates dependencies
public class ItemBuffer {

    // buffer is a synchronizedList to avoid race conditions
    private List<Racquet> buffer = Collections.synchronizedList(new ArrayList<>());

    // lock is used to coordinate execution between loader and reader threads
    private ReentrantLock lock = new ReentrantLock();

    // program will load and read eagerly if true
    private boolean eagerMode;

    // represents server latency in milliseconds - used by loader
    private static final int SERVERLAG = 1000;
    // represents network latency in milliseconds - used by reader
    private int networkLag;

    // Utility variables
    private final String BLUE = "\u001B[34m";    // color for reader
    private final String GREEN = "\u001B[32m";   // color for loader
    private Random random = new Random();        // random number generator
    private Boolean finished = false;            // tracks the end of loading


    // Main simulation program
    public void initBufferSimulation() {

        // stores database objects in inventory
        RacquetModel model = new RacquetModel();
        List<Racquet> inventory = model.getAllRacquets();
        System.out.println("Current items in inventory:");
        inventory.forEach(System.out::print);
        System.out.println("-------------------------\n");

        // creates new thread for loader - passing in inventory to load
        ItemLoader<Racquet> loader = new ItemLoader<>(buffer, inventory);
        Thread loaderThread = new Thread(loader);

        // creates new thread for reader - passing in loader (used in batch reading)
        ItemReader<Racquet> reader = new ItemReader<>(buffer, loaderThread);
        Thread readerThread = new Thread(reader);

        loaderThread.start();
        readerThread.start();

    }

    // ItemBuffer Constructor defaults to batch loading (eagerMode false)
    public ItemBuffer() {
    }
    // Network latency value may be provided if eagerMode is set to true
    public ItemBuffer(boolean eagerMode, int networkLag) {
        this.eagerMode = eagerMode;
        if (eagerMode)
            this.networkLag = networkLag;
    }


    // INNER CLASSES - for loading into buffer and reading from it
    // Both classes share the synchronizedList buffer

    // Reads recommendable items from the buffer on its own Runnable thread
    class ItemReader<Item extends Recommendable> implements Runnable {
        private List<Item> buffer;
        private Thread loaderThread;

        public ItemReader(List<Item> buffer, Thread loaderThread) {
            this.buffer = buffer;
            this.loaderThread = loaderThread;
        }

        // reads items from buffer into console based on the mode
        @Override
        public void run() {
            if (eagerMode)
                eagerRead(networkLag);
            else
                batchRead();
        }


        // reads the whole batch of items by waiting for loader to finish first
        public void batchRead() {
            try {
                loaderThread.join();  // waits for loader thread to finish loading

                // displays each item in blue
                for (Item item : buffer) {
                    System.out.println(BLUE + "Reader displaying: ");
                    System.out.println(item);
                }
            } catch (InterruptedException e) {
                System.out.println(BLUE + "Reading interrupted: " + e.getMessage());
            }
        }


        // reads the items as soon as they become available in the buffer
        public void eagerRead(int latency) {

            // continuously poll the buffer at a given latency (in milliseconds)
            while (true) {
                try {
                    // simulates network lag in reading the items from buffer
                    Thread.sleep(latency);

                    lock.lock(); // obtains lock from loader

                    // poll again if buffer is empty, else read it
                    if (buffer.isEmpty()) continue;

                    System.out.println(BLUE + "Reader displaying: ");
                    System.out.println(buffer.remove(0));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock(); // returns lock to loader
                }
                // exit the polling loop if loader has finished
                if (finished) break;
            }

            // read the remaining objects after the buffer has finished loading
            while (!buffer.isEmpty()) {
                System.out.println(BLUE + "Reader displaying: ");
                System.out.println(buffer.remove(0));
            }
        }

    }



    // Loads recommendable items from inventory to the buffer on its own Runnable thread
    class ItemLoader<Item extends Recommendable> implements Runnable {
        private List<Item> buffer;
        private List<Item> inventory;

        public ItemLoader(List<Item> buffer, List<Item> inventory) {
            this.buffer = buffer;
            this.inventory = inventory;
        }

        // loads items from inventory into buffer based on the mode
        @Override
        public void run() {
            if (eagerMode)
                eagerLoad();
            else
                batchLoad();
        }


        // loads the whole batch of items without waiting on another thread
        public void batchLoad() {
            for (int i = 0; i < inventory.size(); i++) {

                try { // sleeps thread to simulate server processing time
                    Thread.sleep(random.nextInt(SERVERLAG));

                    // loads each item to buffer and outputs message to console in green
                    buffer.add(inventory.get(i));
                    System.out.println(GREEN + "Item " + (i + 1) + " loaded to buffer");

                } catch (InterruptedException e) {
                    System.out.println(GREEN + "Loading interrupted: " + e.getMessage());
                }

            }
        }

        // loads items to the buffer while allowing Reader to consume it eagerly
        public void eagerLoad() {
            for (int i = 0; i < inventory.size(); i++) {

                try { // sleeps thread to simulate server processing time
                    Thread.sleep(random.nextInt(SERVERLAG));

                    lock.lock();  // obtains lock from reader

                    // each item loaded to the buffer gives reader a chance to display it
                    buffer.add(inventory.get(i));
                    System.out.println(GREEN + "Item " + (i + 1) + " loaded to buffer");

                    // turn the finished flag on once all items are loaded
                    if (i + 1 == inventory.size())
                        finished = true;

                } catch (InterruptedException e) {
                    System.out.println(GREEN + "Loading interrupted: " + e.getMessage());
                } finally {
                    lock.unlock(); // releases lock to reader
                }

            }

        }

    }
}
