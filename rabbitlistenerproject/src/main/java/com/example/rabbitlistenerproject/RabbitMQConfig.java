package com.example.rabbitlistenerproject;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    private static final String MY_QUEUE = "MyQueue";

    @Bean
    Queue myQueue(){
        return new Queue(MY_QUEUE, true); //Setting up our Queue
    }

    @Bean
    ConnectionFactory connectionFactory(){ // Setting up our connection to the queue
        ConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost"); // stating where to
        // look for rabbit mq and this case it is local host  but i can image if the rabbit mq is not local you would have to give the url
        // in this location
        ((CachingConnectionFactory) cachingConnectionFactory).setUsername("guest"); // Setting the user name and password
        ((CachingConnectionFactory) cachingConnectionFactory).setPassword("guest");
        return cachingConnectionFactory;
    }
    @Bean
    MessageListenerContainer messageListenerContainer(){ //Binding the Queue and Connection and Listener
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(myQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
        return simpleMessageListenerContainer;
    }


}
