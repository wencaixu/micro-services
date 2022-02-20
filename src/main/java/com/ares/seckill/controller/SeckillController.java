package com.ares.seckill.controller;

import com.ares.seckill.config.RabbitMQSender;
import com.ares.seckill.exception.GlobalBusinessException;
import com.ares.seckill.pojo.*;
import com.ares.seckill.service.ISeckillGoodsService;
import com.ares.seckill.utils.MD5Utils;
import com.ares.seckill.vo.RespBean;
import com.ares.seckill.vo.RespEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Slf4j
@Controller
@RequestMapping(value = "/seckill")
public class SeckillController implements InitializingBean {

    /**
     * 商品是否存在库存内存标记
     */
    private Map<Long, Boolean> existMemoryTag = new HashMap<>();

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ISeckillGoodsService seckillGoodsService;

    @Autowired
    private RedisScript<Long> redisScript;

    @RequestMapping("/{path}/doSeckill")
    @ResponseBody
    public RespBean doSeckill(@PathVariable("path") String path, User user, Long goodsId, HttpServletRequest request) {
        // 路径校验
        if (!isPathValid(path, request)) {
            return RespBean.error(RespEnum.INVALID_REQUEST);
        }
        // 内存标记
        if (existMemoryTag.get(goodsId) == null || !existMemoryTag.get(goodsId)) {
            return RespBean.error(RespEnum.GOODS_STOCK_NOT_ENOUGH);
        }

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        // 库存优化(走lua脚本)
        Long stockCount = redisTemplate.execute(redisScript, Collections.singletonList("good.stock:" + goodsId), new Object());
        if (stockCount == null || stockCount < 0) {
            existMemoryTag.put(goodsId, false);
            return RespBean.error(RespEnum.GOODS_STOCK_NOT_ENOUGH);
        }
        // 判断同一用户秒杀过程中是否重复下单(Redis)
        String orderInfo = valueOperations.get("seckill:goods:" + goodsId + ":user:" + user.getId());
        if (StringUtils.isNotBlank(orderInfo)) {
            return RespBean.error(RespEnum.GOODS_REPEAT_BUY);
        }
        // 异步队列
        RabbitMessageVo message = new RabbitMessageVo(goodsId, user);
        rabbitMQSender.sendRabbitMqMessage(message);
        return RespBean.success(0);
    }

    /**
     * 隐藏地址
     *
     * @param user
     * @param goodsId
     * @return
     */
    @GetMapping(value = "/getPath")
    @ResponseBody
    public RespBean getPath(User user, Long goodsId) {
        if (goodsId == null) {
            throw new GlobalBusinessException(RespEnum.ERROR);
        }
        UUID uuid = UUID.randomUUID();
        String path = MD5Utils.md5(uuid + ":" + user.getId() + ":" + goodsId);
        redisTemplate.opsForValue().set("seckill/" + path + "/doSeckill", path, 10, TimeUnit.SECONDS);
        return RespBean.success(path);
    }

    private Boolean isPathValid(String path, HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        String redisKey = requestUrl.substring(requestUrl.indexOf("seckill"));
        String cachePath = redisTemplate.opsForValue().get(redisKey);
        return StringUtils.equals(path, cachePath);
    }

    /**
     * 商品库存热加载
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<SeckillGoods> seckillGoods = seckillGoodsService.list();
        if (CollectionUtils.isEmpty(seckillGoods)) {
            return;
        }
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        seckillGoods.forEach(good -> {
            Integer stockCount = good.getStockCount();
            if (stockCount == null || stockCount <= 0) {
                return;
            }
            // 内存标记
            existMemoryTag.put(good.getId(), true);
            operations.set("good.stock:" + good.getId(), String.valueOf(stockCount));
        });
    }
}
