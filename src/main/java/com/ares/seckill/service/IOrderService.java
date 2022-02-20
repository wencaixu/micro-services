package com.ares.seckill.service;

import com.ares.seckill.pojo.TOrder;
import com.ares.seckill.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IOrderService extends IService<TOrder> {

    TOrder doSeckill(User user, Long goodsId);
}
