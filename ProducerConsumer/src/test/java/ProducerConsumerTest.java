package ProducerConsumer.src.test.java;

import ProducerConsumer.src.main.java.Consumer;
import ProducerConsumer.src.main.java.Producer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class ProducerConsumerTest {

    @Test
    void testProducerRemovesFromSource() throws Exception {
        List<Integer> source = new java.util.ArrayList<>(List.of(1, 2, 3));
        var queue = new LinkedBlockingQueue<Integer>(5);

        Producer producer = new Producer(source, queue);
        Thread t = new Thread(producer);
        t.start();

        Thread.sleep(300);
        t.interrupt();

        assertTrue(source.size() < 3);
    }

    @Test
    void testConsumerAddsToDestination() throws Exception {
        var destination = new java.util.ArrayList<Integer>();
        var queue = new LinkedBlockingQueue<Integer>(5);

        queue.put(10);

        Consumer consumer = new Consumer(destination, queue);
        Thread t = new Thread(consumer);
        t.start();

        Thread.sleep(300);
        t.interrupt();

        assertTrue(destination.contains(10));
    }

    @Test
    void testQueueTransfer() throws Exception {
        var queue = new LinkedBlockingQueue<Integer>(5);
        queue.put(5);

        assertEquals(5, queue.take());
    }
}
