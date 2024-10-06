# RabbitMQ: dat250-expass7

## Technical Problems Encountered

### Queue Declaration Mismatch
- **Problem**: At first, I used the same queue name for the first and second experiment, and this lead to inconsistent 
properties between different runs. Specifically, the queue was created during the first experiment with `durable=false` 
but later, in the second experiment, the same queue was declared with `durable=true`, leading to a `PRECONDITION_FAILED` 
error.
- **Solution**: To fix this, I created a new queue for the second experiment.

### Worker Not Staying Alive
- **Problem**: The `Worker` would run and immediately exit after printing a debug message, indicating that the process wasn't staying alive to wait for incoming messages.
- **Solution**: To fix this, I used a blocking mechanism (`synchronized (Worker.class).wait()`) to keep the `Worker` 
process running indefinitely. This ensured that the worker remained active and was able to receive messages as they were 
delivered.

### Unchecked Exception Handling
- **Problem**: I encountered a compile-time error because the `doWork` method was throwing an `InterruptedException`, 
a checked exception that wasn't being handled properly.
- **Solution**: I added a `try-catch` block around the `doWork` method inside the `DeliverCallback`. This allowed the 
program to catch the `InterruptedException` and prevented the worker from crashing.

### Integrating the Three Experiments in a Single Gradle Project
- **Problem**: I struggled on how to integrate the three experiments in a single Gradle Project. I wanted to make 
switching between different examples easier for the user.
- **Solution**: I added custom Gradle tasks for each of the classes so that each experiment could run using a dedicated 
task. These tasks are defined in the `build.gradle` file and allow to run the respective classes without needing to 
modify the `mainClass` manually each time. Additionally, I added a **Help** class that provides instructions on how to run 
each application:
- `./gradlew runSend`: Runs the Hello World sender.
- `./gradlew runReceive`: Runs the Hello World receiver.
- `./gradlew runNewTask`: Runs the `NewTask` class for the task queue.
- `./gradlew runWorker`: Runs the `Worker` class to consume tasks.
-  `./gradlew runEmitLog`: Runs the `EmitLog` class for publish/subscribe.
- `./gradlew runReceiveLogs`: Runs the `ReceiveLogs` class to receive logs from the exchange.

## Code Repository
- **Experiment 2.2 (Hello World)**: I created `Send.java` and `Receive.java` in the `com.example.rabbitmq` package to demonstrate sending and receiving a basic "Hello World!" message.
- **Experiment 2.3 (Work Queues)**: The `NewTask.java` and `Worker.java` classes in the `com.example.rabbitmq2` package implemented a task queue system using round-robin message distribution between multiple workers.
- **Experiment 2.4 (Publish/Subscribe with Topics)**: I implemented `EmitLog.java` and `ReceiveLogs.java` in the `com.example.rabbitmq3` package to demonstrate the publish/subscribe pattern using RabbitMQ exchanges.
All the code for the experiments described above is available at the following link:
[Link to code](https://github.com/CarlaMiquelBlasco/Lab6/tree/master/src/main/java/com/example)

## Pending Issues
There are no pending issues with the assignment that I was unable to solve.

