package com.ares.seckill.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 队列
 *
 * @author wencai.xu
 */
@Configuration
public class RabbitMQConfig {

    private final String TOPIC_QUEUE = "queue";

    private final String ROUTING_KEY = "#.topic.#";

    private final String TOPIC_EXCHANGE = "topicExchange";

    @Bean
    public Queue queue(){
        return new Queue(TOPIC_QUEUE);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(topicExchange()).with(ROUTING_KEY);
    }
}
