package com.example.demo.myUtile.MessageQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeoutException;

@Profile("send")
public class Send
{
    static Logger logger = LoggerFactory.getLogger(Send.class);

    private final static String QUEUE_NAME = "A to B";

    public static void send_message() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,
                false,
                false,
                false,
                null);


        //String username = Appuser.getAuthUsername();

        String username = "admin";

        Message message = new Message(username,"Message From Application A to Application B");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(message);
        oos.flush();
        byte [] data = bos.toByteArray();


        channel.basicPublish("",
                QUEUE_NAME,
                null,
                data );

        logger.debug("[!] Sent '" + message + "'");
        System.out.println("[!] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}

