package com.ares.seckill.mapper;

import com.ares.seckill.pojo.TSeckillOrder;
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
public interface TSeckillOrderMapper extends BaseMapper<TSeckillOrder> {
    TSeckillOrder findOrderVoByGoodsIdAndUserId(Long goodsId, Long userId);
}
