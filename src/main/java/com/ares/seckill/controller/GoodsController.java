package com.ares.seckill.controller;


import com.ares.seckill.exception.GlobalBusinessException;
import com.ares.seckill.pojo.User;
import com.ares.seckill.service.IGoodsService;
import com.ares.seckill.vo.GoodsVo;
import com.ares.seckill.vo.RespEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器,URL缓存，页面静态化，静态资源的优化，CDN优化
 * <p>
 * 页面缓存
 * <p>
 * 数据库更新需要把考虑redis更新（对象缓存）
 * </p>
 *
 * @author qingmu
 * @since 2021-07-28
 */
@Slf4j
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @RequestMapping(value = "/toList", produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String toList(Model model, User user,
                         HttpServletRequest request, HttpServletResponse response) {
        // 页面缓存
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String html = (String) valueOperations.get("goodsList");
        if (!StringUtils.isBlank(html)) {
            return html;
        }

        model.addAttribute("username", user.getNickname());
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        WebContext webContext = new WebContext(request, response, request.getServletContext(),
                request.getLocale(), model.asMap());
        // 设置template名称和webContext
        html = thymeleafViewResolver.getTemplateEngine().process("/goodsList", webContext);
        if (StringUtils.isNotBlank(html)) {
            // 设置缓存，过期时间为60s
            valueOperations.set("goodsList", html, 60, TimeUnit.SECONDS);
        }
        return html;
    }

    // 前QPS 2400
    // 后QPS 5780
    @RequestMapping(value = "/toDetail/{goodId}", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toDetail(Model model, User user, @PathVariable("goodId") Long goodId,
                           HttpServletRequest request, HttpServletResponse response) {
        // URL缓存
        if (Objects.isNull(goodId)) {
            throw new GlobalBusinessException(RespEnum.BIND_ERROR);
        }
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String html = (String) valueOperations.get("goodId:" + goodId);
        if (StringUtils.isNotBlank(html)) {
            return html;
        }

        model.addAttribute("username", user.getNickname());
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodId);
        // 设置秒杀状态
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date curDate = new Date();

        int secKillStatus = 0;
        int remainSeconds = 0;

        // 秒杀还未开始
        if (curDate.before(startDate)) {
            remainSeconds = (int) ((endDate.getTime() - startDate.getTime()) / 1000);
        } else if (curDate.after(endDate)) {
            // 秒杀已结束
            secKillStatus = 2;
            remainSeconds = -1;
        } else {
            // 秒杀进行中
            secKillStatus = 1;
        }
        model.addAttribute("secKillStatus", secKillStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("goods", goodsVo);

        WebContext webContext = new WebContext(request, response, request.getServletContext(),
                request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("/goodsDetail", webContext);
        if (StringUtils.isNotBlank(html)) {
            valueOperations.set("goodId:" + goodId, html, 60, TimeUnit.SECONDS);
        }
        //return "/goodsDetail";
        return html;
    }

}
