package com.example.demo.myUtile.MessageQueue;//package com.example.demo.myUtile.MessageQueue;
//
//import com.rabbitmq.client.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Profile;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.util.concurrent.TimeoutException;
//
//@Profile("receive")
//public class Receive
//{
//    static Logger logger = LoggerFactory.getLogger(Receive.class);
//
//    private final static String QUEUE_NAME = "My Queue";
//
//    public  static void main(String[] argv) throws IOException, TimeoutException
//    {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.queueDeclare(QUEUE_NAME,
//                false,
//                false,
//                false,
//                null);
//
//        logger.info("[!] Waiting for messages. To exit press Ctrl+C");
//
//        Consumer consumer = new DefaultConsumer(channel)
//        {
//
//            @Override
//            public void handleDelivery(String consumerTag,
//                                       Envelope envelope,
//                                       AMQP.BasicProperties properties,
//                                       byte[] body)
//                    throws IOException
//            {
//
//                ByteArrayInputStream in = new ByteArrayInputStream(body);
//                ObjectInputStream is = new ObjectInputStream(in);
//                try {
//                    Message message = (Message)is.readObject();
//                    logger.info("[x] Message Receive' " + message + "'");
//
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//
//
//            }
//        };
//
//        channel.basicConsume(QUEUE_NAME, true, consumer);
//    }
//}