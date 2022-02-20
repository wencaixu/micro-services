package com.ares.seckill.controller;


import com.ares.seckill.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;



/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wencai.xu
 * @since 2021-07-24
 */
@Slf4j
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

}
