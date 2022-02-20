package com.ares.seckill.service;

import com.ares.seckill.pojo.User;
import com.ares.seckill.vo.LoginVo;
import com.ares.seckill.vo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVo loginVo, HttpServletRequest request,HttpServletResponse response) ;

    User getUserByCookieId(String cookieId, HttpServletRequest request, HttpServletResponse response);
}
