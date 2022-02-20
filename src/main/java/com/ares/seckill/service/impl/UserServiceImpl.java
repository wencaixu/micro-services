package com.ares.seckill.service.impl;

import com.ares.seckill.exception.GlobalBusinessException;
import com.ares.seckill.pojo.User;
import com.ares.seckill.mapper.TUserMapper;
import com.ares.seckill.service.IUserService;
import com.ares.seckill.utils.CookieUtil;
import com.ares.seckill.utils.MD5Utils;
import com.ares.seckill.utils.UUIDUtils;
import com.ares.seckill.vo.LoginVo;
import com.ares.seckill.vo.RespBean;
import com.ares.seckill.vo.RespEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl extends ServiceImpl<TUserMapper, User> implements IUserService {

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        Long telephone = loginVo.getMobile();
        String password = loginVo.getPassword();

        User user = userMapper.selectById(telephone);
        if(null == user){
            throw new GlobalBusinessException(RespEnum.SESSION_ERROR);
        }
        String dbPass = MD5Utils.formPassToDbPass(password, user.getSalt());
        if(!StringUtils.equals(dbPass,user.getPassword())){
            throw new GlobalBusinessException(RespEnum.LOGIN_ERROR);
        }
        // 生成sessionId,放入Session中
        String userTicket = UUIDUtils.uuid();
        redisTemplate.opsForValue().set(userTicket,user);
        // session 方式，但是分布式情况下不行
        //request.getSession().setAttribute(userTicket,user);
        // 设置Cookie
        CookieUtil.setCookie(request,response,"userTicket",userTicket);
        return RespBean.success(userTicket);
    }

    public User getUserByCookieId(String cookieId,HttpServletRequest request,HttpServletResponse response){
        Object user = redisTemplate.opsForValue().get(cookieId);
        if(null == user){
            return null;
        }
        CookieUtil.setCookie(request,response,"userTicket",cookieId);
        return (User)user;
    }
}
