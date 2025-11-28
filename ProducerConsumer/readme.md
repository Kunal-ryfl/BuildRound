
# Producer–Consumer Synchronization (Java)

This project demonstrates a classic **Producer–Consumer pattern** using Java.

---

## Description

The program simulates concurrent data transfer between a producer and consumer:

- **Producer** reads items from a source container and puts them into a queue.
- **Consumer** reads items from the queue and stores them in a destination container.
- Uses **thread synchronization**, `wait()` / `notifyAll()`, and `BlockingQueue`.
- Demonstrates thread-safe communication using `Collections.synchronizedList`.

---

## Project Structure

