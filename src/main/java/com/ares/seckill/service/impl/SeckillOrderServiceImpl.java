package com.ares.seckill.service.impl;

import com.ares.seckill.mapper.TSeckillOrderMapper;
import com.ares.seckill.pojo.TSeckillOrder;
import com.ares.seckill.service.ISeckillOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillOrderServiceImpl extends ServiceImpl<TSeckillOrderMapper, TSeckillOrder> implements ISeckillOrderService {

    @Autowired
    private TSeckillOrderMapper seckillOrderMapper;

    @Override
    public TSeckillOrder findOrderVoByGoodsIdAndUserId(Long goodsId, Long userId) {
        return seckillOrderMapper.findOrderVoByGoodsIdAndUserId(goodsId,userId);
    }
}
