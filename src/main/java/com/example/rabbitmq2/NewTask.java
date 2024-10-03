package com.example.rabbitmq2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class NewTask {

    private final static String QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        // Create a connection to the RabbitMQ server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Declare a queue for the message
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = String.join(" ", argv);

            // Publish a message to the queue
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}

