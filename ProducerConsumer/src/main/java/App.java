package ProducerConsumer.src.main.java;

/**
 * Main class to run the Producer-Consumer simulation.
 *
 * Demonstrates:
 * - Thread synchronization using BlockingQueue
 * - Synchronized access to shared source and destination containers
 * - Producer producing items from a source container
 * - Consumer consuming items into a destination container
 */
public class App {

    public static void main(String[] args) {

        // Initialize shared containers: source list, destination list, and queue
        SharedContainers containers = new SharedContainers();

        // Create producer thread:
        // Reads items from sourceContainer and puts them into the queue
        Thread producer = new Thread(new Producer(containers.sourceContainer, containers.queue));

        // Create consumer thread:
        // Takes items from the queue and adds them to destinationContainer
        Thread consumer = new Thread(new Consumer(containers.destinationContainer, containers.queue));

        // Start both threads
        producer.start();
        consumer.start();

        // Optional: In real applications, you might join threads or stop them gracefully
        // Here, they will run indefinitely demonstrating the producer-consumer pattern
    }
}
