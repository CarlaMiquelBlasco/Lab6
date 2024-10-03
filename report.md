# RabbitMQ Tutorial: Short Report

## Technical Problems Encountered

### Gradle Build and Classpath Issues
- **Problem**: When I initially tried to run the `Worker` or `NewTask` classes, I encountered `NoClassDefFoundError`. The necessary dependencies, such as the RabbitMQ client, were not included in the classpath.
- **Solution**: I ensured that the `build.gradle` file was properly configured with the RabbitMQ client dependency (`com.rabbitmq:amqp-client`). I also used the `-PmainClass` flag with the `./gradlew run` command to specify which class to run dynamically. This resolved the classpath and build issues.

### Queue Declaration Mismatch
- **Problem**: I encountered a mismatch issue when declaring a queue with inconsistent properties between different runs. Specifically, the queue was created with `durable=false` but later declared with `durable=true`, leading to a `PRECONDITION_FAILED` error.
- **Solution**: To fix this, I either had to delete the existing queue or ensure that both `NewTask` and `Worker` were using consistent queue declarations. By modifying both classes to declare the queue with the same properties, I was able to resolve this issue.

### Worker Not Staying Alive
- **Problem**: The `Worker` would run and immediately exit after printing a debug message, indicating that the process wasn't staying alive to wait for incoming messages.
- **Solution**: To fix this, I used a blocking mechanism (`synchronized (Worker.class).wait()`) to keep the `Worker` process running indefinitely. This ensured that the worker remained active and was able to receive messages as they were delivered.

### Unchecked Exception Handling
- **Problem**: I encountered a compile-time error because the `doWork` method was throwing an `InterruptedException`, a checked exception that wasn't being handled properly.
- **Solution**: I added a `try-catch` block around the `doWork` method inside the `DeliverCallback`. This allowed the program to catch the `InterruptedException` and prevented the worker from crashing. This solution ensured that the worker handled interruptions gracefully.

### Ensuring Consistent Queue Usage
- **Problem**: There was an inconsistency between the queue names or properties in the `Worker` and `NewTask` classes, which caused issues with message consumption.
- **Solution**: I ensured that both `NewTask` (producer) and `Worker` (consumer) were using the same queue name (`task_queue`) with matching properties. This consistency allowed messages to be properly delivered to and consumed by the worker.

## Integrating the Three Experiments in a Single Gradle Project

### Overview
I successfully integrated the three RabbitMQ experiments—`Hello World`, `Work Queues`, and `Publish/Subscribe (Topics)`—into a single Gradle project. To make switching between different examples easier, I added a **Help** class that provides instructions on how to run each application.

### Details of Integration
- **Experiment 2.2 (Hello World)**: I created `Send.java` and `Receive.java` in the `com.example.rabbitmq` package to demonstrate sending and receiving a basic "Hello World!" message.
- **Experiment 2.3 (Work Queues)**: The `NewTask.java` and `Worker.java` classes in the `com.example.rabbitmq2` package implemented a task queue system using round-robin message distribution between multiple workers.
- **Experiment 2.4 (Publish/Subscribe with Topics)**: I implemented `EmitLog.java` and `ReceiveLogs.java` in the `com.example.rabbitmq3` package to demonstrate the publish/subscribe pattern using RabbitMQ exchanges.

### Help Class
To streamline the process of running the different applications, I created a **Help** class. This class prints instructions for running each of the experiments when no specific `mainClass` is provided. The `Help.java` file provides detailed instructions, explaining how to run each experiment using custom Gradle tasks or the `-PmainClass` flag.

### Gradle Configuration
I added custom Gradle tasks for each of the classes so that each experiment can be run using a dedicated task. These tasks are defined in the `build.gradle` file and allow me to run the respective classes without needing to modify the `mainClass` manually each time.

For example:
- `./gradlew runSend`: Runs the Hello World sender.
- `./gradlew runReceive`: Runs the Hello World receiver.
- `./gradlew runEmitLog`: Runs the `EmitLog` class for publish/subscribe.
- `./gradlew runReceiveLogs`: Runs the `ReceiveLogs` class to receive logs from the exchange.
- `./gradlew runNewTask`: Runs the `NewTask` class for the task queue.
- `./gradlew runWorker`: Runs the `Worker` class to consume tasks.

This setup made it easy to manage multiple RabbitMQ experiments within a single Gradle project.

## Code Repository
All the code for the experiments described above is available at the following link:
[Link to Code Repository]

## Pending Issues
Currently, all major technical issues have been resolved, and the project works as expected. There are no pending issues with the assignment that I was unable to solve. Each experiment runs smoothly, and the message-passing and consumer behavior in RabbitMQ behave as expected.

