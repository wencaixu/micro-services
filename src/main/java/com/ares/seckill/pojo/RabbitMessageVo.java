package com.ares.seckill.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMessageVo {

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 用户实体
     */
    private User user;
}
