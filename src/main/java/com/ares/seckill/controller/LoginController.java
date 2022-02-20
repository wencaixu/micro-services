package com.ares.seckill.controller;


import com.ares.seckill.service.IUserService;
import com.ares.seckill.vo.LoginVo;
import com.ares.seckill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 登陆控制器
 *
 * @author wencai.xu
 */
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IUserService userService;

    /**
     * 登陆页面控制器
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login";
    }

    /**
     * 登陆接口
     * @param vo        {@link LoginVo}
     * @param request   {@link HttpServletRequest}
     * @param response  {@link HttpServletResponse}
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVo vo, HttpServletRequest request, HttpServletResponse response){
        return userService.doLogin(vo,request,response);
    }
}
