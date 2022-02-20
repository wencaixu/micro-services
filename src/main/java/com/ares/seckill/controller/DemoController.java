package com.ares.seckill.controller;

import com.ares.seckill.anno.RateLimiter;
import com.ares.seckill.config.RabbitMQSender;
import com.ares.seckill.pojo.RabbitMessageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @RequestMapping(value = "/sendRed")
    @ResponseBody
    @RateLimiter(time = 10,rate = 3)
    public void send(){
        RabbitMessageVo message = new RabbitMessageVo();
        rabbitMQSender.sendRabbitMqMessage(message);
    }


    @RequestMapping(value = "/health")
    public String health(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }


}
