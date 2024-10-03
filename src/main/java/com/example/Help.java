package com.example;

public class Help {
    public static void main(String[] args) {
        System.out.println("=== RabbitMQ Applications ===");
        System.out.println("Available applications:");
        System.out.println("1. Worker - Receives tasks from the task_queue");
        System.out.println("   Command: ./gradlew runWorker");
        System.out.println("2. NewTask - Sends tasks to the task_queue");
        System.out.println("   Command: ./gradlew runNewTask --args=\"<message>\"");
        System.out.println("3. ReceiveLogs - Receives logs from the fanout exchange");
        System.out.println("   Command: ./gradlew runReceiveLogs");
        System.out.println("4. EmitLog - Sends logs to a fanout exchange");
        System.out.println("   Command: ./gradlew runEmitLog --args=\"<message>\"");
        System.out.println("To run any of the above, use the respective Gradle task.");
    }
}
