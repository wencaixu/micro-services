package com.ares.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author jobob
 * @since 2021-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_order")
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收货地址id
     */
    private Long deliveryAddrId;

    /**
     * 商品名称，冗余
     */
    private String goodsName;

    /**
     * 商品个数
     */
    private Integer goodsCount;

    /**
     * 善品单价
     */
    private BigDecimal goodsPrice;

    /**
     * 1pc 2android 3 ios
     */
    private Integer orderChannel;

    /**
     * 订单状态，0，新建未支付，1已支付，2已发货，4已退款5已完成
     */
    private Integer status;

    /**
     * 订单创建时间
     */
    private Date createDate;

    /**
     * 支付时间
     */
    private Date payDate;

    /**
     * 商品id
     */
    private Long goodsId;


}
