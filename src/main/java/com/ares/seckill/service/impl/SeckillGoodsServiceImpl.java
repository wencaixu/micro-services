package com.ares.seckill.service.impl;

import com.ares.seckill.mapper.TSeckillGoodsMapper;
import com.ares.seckill.pojo.SeckillGoods;
import com.ares.seckill.service.ISeckillGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<TSeckillGoodsMapper, SeckillGoods> implements ISeckillGoodsService {

    @Autowired
    private TSeckillGoodsMapper seckillGoodsMapper;

    @Override
    public Boolean updateSeckillStockByGoodsId(Long goodsId, Integer stockCount) {
        return seckillGoodsMapper.updateSeckillStockByGoodsId(goodsId,stockCount);
    }
}
