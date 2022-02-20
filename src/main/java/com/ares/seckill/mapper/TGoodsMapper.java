package com.ares.seckill.mapper;

import com.ares.seckill.pojo.TGoods;
import com.ares.seckill.vo.GoodsVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @since 2021-07-28
 */
@Mapper
public interface TGoodsMapper extends BaseMapper<TGoods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
