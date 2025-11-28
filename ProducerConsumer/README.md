
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
````
ProducerConsumer/
│── README.md
└── src/
    ├── main/java/
    │   ├── App.java
    │   ├── Producer.java
    │   ├── Consumer.java
    │   └── SharedContainers.java
    └── test/java/
        └── ProducerConsumerTest.java

````
## Setup Instructions

1. Clone the repository:

````
git clone git@github.com:Kunal-ryfl/BuildRound.git
````
2. Open the project in IntelliJ IDEA or another Java IDE.

3. Make sure src/main/resources is marked as Resources Root.

4. Run the main class:
````
App.main()
````
5. Run unit tests from ProducerConsumerTest.java.

## Sample Output and Screenshots
````
Producer → Source → 1
Producer → Queue: 1
Consumer ← Queue: 1
Consumer ← Destination: 1
Producer → Source → 2
Producer → Queue: 2
Consumer ← Queue: 2
Consumer ← Destination: 2
...
````
<img width="1327" height="832" alt="image" src="https://github.com/user-attachments/assets/4e6e5063-242a-41b7-bf4b-b019867cc7d8" />

<img width="970" height="509" alt="image" src="https://github.com/user-attachments/assets/f0c7182d-2b7c-4286-8097-75ff3530fdcd" />
