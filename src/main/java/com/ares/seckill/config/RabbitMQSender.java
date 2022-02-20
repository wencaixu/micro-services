package com.ares.seckill.config;


import com.ares.seckill.pojo.RabbitMessageVo;
import com.ares.seckill.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendRabbitMqMessage(RabbitMessageVo message){
        String messageJson = ObjectUtils.toJson(message);
        rabbitTemplate.convertAndSend("topicExchange","seckill.topic.order",messageJson);
    }
}
