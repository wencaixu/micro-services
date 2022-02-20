package com.ares.seckill.exception;

import com.ares.seckill.vo.RespBean;
import com.ares.seckill.vo.RespEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Restful接口全局异常处理
 *
 * @author wencai.xu
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常处理
     *
     * @param e 异常实体
     * @return {@link RespBean}
     **/
    @ExceptionHandler(Exception.class)
    public RespBean handleException(Exception e) {
        if(e instanceof GlobalBusinessException){
            GlobalBusinessException ex = (GlobalBusinessException) e;
            return RespBean.error(ex.getRespEnum());
        }else if(e instanceof BindException){
            BindException ex = (BindException)e;
            RespBean error = RespBean.error(RespEnum.BIND_ERROR);
            error.setMessage("参数校验异常：" + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return error;
        }
        return RespBean.error(RespEnum.ERROR);
    }
}
