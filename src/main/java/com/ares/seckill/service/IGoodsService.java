package com.ares.seckill.service;

import com.ares.seckill.pojo.TGoods;
import com.ares.seckill.vo.GoodsVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IGoodsService extends IService<TGoods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
