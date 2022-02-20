package com.ares.seckill.config;

import com.ares.seckill.pojo.RabbitMessageVo;
import com.ares.seckill.pojo.User;
import com.ares.seckill.service.IOrderService;
import com.ares.seckill.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class RabbitMQReceiver {

    @Autowired
    private IOrderService orderService;

    @RabbitListener(queues = "queue")
    public void receive(String msg){
        RabbitMessageVo rabbitMessageVo = ObjectUtils.toObject(msg, RabbitMessageVo.class);
        if(Objects.nonNull(rabbitMessageVo)){
            Long goodsId = rabbitMessageVo.getGoodsId();
            User user = rabbitMessageVo.getUser();
            orderService.doSeckill(user,goodsId);
        }
    }
}
