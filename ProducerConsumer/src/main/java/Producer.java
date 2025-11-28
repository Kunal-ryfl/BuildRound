package ProducerConsumer.src.main.java;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Producer class implements Runnable to produce items from a source container
 * and place them into a shared BlockingQueue.
 *
 * Demonstrates thread synchronization using:
 * - synchronized + wait()/notify() on the source container
 * - BlockingQueue.put() for thread-safe communication with the consumer
 */
public class Producer implements Runnable {

    // Source container (shared list) from which producer takes items
    private final List<Integer> source;

    // Shared BlockingQueue acting as a buffer between producer and consumer
    private final BlockingQueue<Integer> queue;

    /**
     * Constructor to initialize producer with a source list and a shared queue
     * @param source Source container (synchronized list)
     * @param queue  BlockingQueue buffer
     */
    public Producer(List<Integer> source, BlockingQueue<Integer> queue) {
        this.source = source;
        this.queue = queue;
    }

    /**
     * Main run method of producer thread
     * Continuously produces items from the source and puts them into the queue
     */
    @Override
    public void run() {
        while (true) {
            int item;

            // Synchronize access to source list to ensure thread-safety
            synchronized (source) {
                // Wait if source is empty
                while (source.isEmpty()) {
                    try {
                        System.out.println("Producer waiting (source empty)...");
                        source.wait(); // wait until notified by another thread
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return; // exit thread if interrupted
                    }
                }

                // Remove the first item from the source
                item = source.remove(0);
                System.out.println("Producer → Source → " + item);

                // Notify other threads waiting on source (if any)
                source.notifyAll();
            }

            // Put item into BlockingQueue (blocks if queue is full)
            try {
                queue.put(item);  // thread-safe blocking put
                System.out.println("Producer → Queue: " + item);

                // Simulate production delay
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // preserve interrupt status
            }
        }
    }
}
