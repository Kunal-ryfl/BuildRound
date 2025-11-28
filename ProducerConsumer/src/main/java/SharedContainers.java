package ProducerConsumer.src.main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * SharedContainers class holds all shared data structures used by Producer and Consumer.
 *
 * 1. sourceContainer: Contains initial data to be produced.
 * 2. destinationContainer: Stores items consumed by the consumer.
 * 3. queue: Thread-safe blocking queue for communication between producer and consumer.
 */
public class SharedContainers {

    // Source list containing integers to be produced.
    // Synchronized to allow safe access by multiple threads.
    public final List<Integer> sourceContainer = Collections.synchronizedList(new ArrayList<>());

    // Destination list where consumer stores consumed items.
    // Synchronized to ensure thread-safety.
    public final List<Integer> destinationContainer = Collections.synchronizedList(new ArrayList<>());

    // BlockingQueue acts as a buffer between producer and consumer.
    // Capacity 5 to demonstrate blocking behavior.
    public final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

    /**
     * Constructor initializes the source container with values 1 to 10.
     */
    public SharedContainers() {
        for (int i = 1; i <= 10; i++) {
            sourceContainer.add(i);
        }
    }
}
