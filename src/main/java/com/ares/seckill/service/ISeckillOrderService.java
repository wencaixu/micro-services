package com.ares.seckill.service;

import com.ares.seckill.pojo.TSeckillOrder;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ISeckillOrderService extends IService<TSeckillOrder> {

    TSeckillOrder findOrderVoByGoodsIdAndUserId(Long goodsId, Long userId);
}
