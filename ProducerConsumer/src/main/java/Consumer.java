package ProducerConsumer.src.main.java;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Consumer class implements Runnable to consume items from a shared BlockingQueue
 * and store them in a destination container.
 *
 * Demonstrates thread synchronization using:
 * - BlockingQueue.take() to safely retrieve items from the queue
 * - synchronized + notifyAll() on the destination container
 */
public class Consumer implements Runnable {

    // Destination container where consumed items are stored
    private final List<Integer> destination;

    // Shared BlockingQueue acting as a buffer between producer and consumer
    private final BlockingQueue<Integer> queue;

    /**
     * Constructor to initialize consumer with a destination list and a shared queue
     *
     * @param destination Destination container (synchronized list)
     * @param queue       BlockingQueue buffer to consume items from
     */
    public Consumer(List<Integer> destination, BlockingQueue<Integer> queue) {
        this.destination = destination;
        this.queue = queue;
    }

    /**
     * Main run method of consumer thread
     * Continuously consumes items from the queue and stores them in the destination
     */
    @Override
    public void run() {
        while (true) {
            try {
                // Take an item from the queue (blocks if empty)
                int item = queue.take();
                System.out.println("Consumer ← Queue: " + item);

                // Synchronize access to destination to ensure thread-safety
                synchronized (destination) {
                    destination.add(item);
                    System.out.println("Consumer ← Destination: " + item);

                    // Notify other threads waiting on destination (if any)
                    destination.notifyAll();
                }

                // Simulate processing delay
                Thread.sleep(400);

            } catch (InterruptedException e) {
                // Preserve interrupt status and exit if thread is interrupted
                Thread.currentThread().interrupt();
            }
        }
    }
}
