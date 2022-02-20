package com.ares.seckill.mapper;

import com.ares.seckill.pojo.SeckillGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2021-07-28
 */
@Mapper
public interface TSeckillGoodsMapper extends BaseMapper<SeckillGoods> {

    /**
     * 更新数据库库存
     *
     * @param goodsId 商品id
     * @param stockCount 秒杀库存
     * @return
     */
    Boolean updateSeckillStockByGoodsId(Long goodsId, Integer stockCount);
}
