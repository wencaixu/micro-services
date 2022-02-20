package com.ares.seckill.config;

import com.ares.seckill.exception.GlobalBusinessException;
import com.ares.seckill.pojo.User;
import com.ares.seckill.service.IUserService;
import com.ares.seckill.utils.CookieUtil;
import com.ares.seckill.vo.RespEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private IUserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> declaringClass = methodParameter.getParameterType();
        return declaringClass == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {

        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        String ticket = CookieUtil.getCookieValue(request, "userTicket");
        if(StringUtils.isEmpty(ticket)){
            throw new GlobalBusinessException(RespEnum.NO_LOGIN_OR_NOT_EXIST);
        }
        User user = userService.getUserByCookieId(ticket, request, response);
        if(Objects.isNull(user)){
            throw new GlobalBusinessException(RespEnum.SESSION_ERROR);
        }
        return user;
    }
}
