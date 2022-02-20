package com.ares.seckill.service.impl;

import com.ares.seckill.mapper.TGoodsMapper;
import com.ares.seckill.pojo.TGoods;
import com.ares.seckill.service.IGoodsService;
import com.ares.seckill.vo.GoodsVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl extends ServiceImpl<TGoodsMapper, TGoods> implements IGoodsService {

    @Autowired
    private TGoodsMapper goodsMapper;

    public List<GoodsVo> findGoodsVo(){
        return goodsMapper.findGoodsVo();
    }

    @Override
    public GoodsVo findGoodsVoByGoodsId(Long goodsId) {
        return goodsMapper.findGoodsVoByGoodsId(goodsId);
    }

}
