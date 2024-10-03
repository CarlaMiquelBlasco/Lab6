package com.example;

public class Help {
    public static void main(String[] args) {
        System.out.println("=== RabbitMQ Applications ===");
        System.out.println("Available applications:");
        System.out.println("1. Send (Hello World) - Sends 'Hello World!' message to the 'hello' queue");
        System.out.println("   Command: ./gradlew runSend");
        System.out.println("2. Receive (Hello World) - Receives messages from the 'hello' queue");
        System.out.println("   Command: ./gradlew runReceive");
        System.out.println("3. Worker - Receives tasks from the task_queue");
        System.out.println("   Command: ./gradlew runWorker");
        System.out.println("4. NewTask - Sends tasks to the task_queue");
        System.out.println("   Command: ./gradlew runNewTask --args=\"<message>\"");
        System.out.println("5. ReceiveLogs - Receives logs from the fanout exchange");
        System.out.println("   Command: ./gradlew runReceiveLogs");
        System.out.println("6. EmitLog - Sends logs to a fanout exchange");
        System.out.println("   Command: ./gradlew runEmitLog --args=\"<message>\"");
        System.out.println("To run any of the above, use the respective Gradle task.");
    }
}
