package com.example.rabbitlistenerproject;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMQMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.print("Message =  " + new String(message.getBody()));
    }
}
