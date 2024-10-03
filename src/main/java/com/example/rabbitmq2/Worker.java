package com.example.rabbitmq2;

import com.rabbitmq.client.*;

public class Worker {

    private final static String QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        // Create a connection to the RabbitMQ server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Declare the queue to consume messages from
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            // Deliver callback for processing messages
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                try {
                    // Catch the InterruptedException thrown by doWork
                    doWork(message);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // Preserve interrupt status
                    System.err.println("Task interrupted: " + e.getMessage());
                } finally {
                    System.out.println(" [x] Done");
                }
            };

            // Prefetch setting to ensure fair dispatching
            channel.basicQos(1);

            // Start consuming messages
            boolean autoAck = true;  // Auto acknowledgment
            channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {});

            // Keep the program running
            synchronized (Worker.class) {
                Worker.class.wait();
            }
        }
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000);  // Simulate work with each dot
        }
    }
}
