package com.ares.seckill.service.impl;

import com.ares.seckill.mapper.TOrderMapper;
import com.ares.seckill.pojo.TOrder;
import com.ares.seckill.pojo.TSeckillOrder;
import com.ares.seckill.pojo.User;
import com.ares.seckill.service.IGoodsService;
import com.ares.seckill.service.IOrderService;
import com.ares.seckill.service.ISeckillOrderService;
import com.ares.seckill.utils.ObjectUtils;
import com.ares.seckill.vo.GoodsVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements IOrderService {

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IGoodsService iGoodsService;

    @Autowired
    private ISeckillOrderService iSeckillOrderService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public TOrder doSeckill(User user, Long goodsId) {
        GoodsVo goods = iGoodsService.findGoodsVoByGoodsId(goodsId);
        // 生成订单
        TOrder order = new TOrder();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(goods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        iOrderService.save(order);
        //生成秒杀订单
        TSeckillOrder seckillOrder = new TSeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodsId(goods.getId());
        iSeckillOrderService.save(seckillOrder);
        // 放到Redis中
        redisTemplate.opsForValue().set("seckill:goods:" + goodsId + ":user:" + user.getId(), ObjectUtils.toJson(seckillOrder));
        return order;
    }
}
