package com.ares.seckill.service;

import com.ares.seckill.pojo.SeckillGoods;
import com.baomidou.mybatisplus.extension.service.IService;


public interface ISeckillGoodsService extends IService<SeckillGoods> {

    Boolean updateSeckillStockByGoodsId(Long goodsId, Integer stockCount);
}
