package com.ares.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    private long code;
    private String message;
    private Object obj;

    /**
     * 响应成功状态码和状态信息，以及响应数据
     *
     * @param obj 响应数据
     * @return {@link RespBean}
     */
    public static RespBean success(Object obj) {
        return new RespBean(RespEnum.SUCCESS.getCode(), RespEnum.SUCCESS.getMsg(), obj);
    }

    /**
     * 响应失败状态码和状态信息，以及响应数据
     *
     * @param obj 响应数据
     * @return {@link RespBean}
     */
    public static RespBean error(Object obj) {
        return new RespBean(RespEnum.ERROR.getCode(), RespEnum.ERROR.getMsg(), obj);
    }

    /**
     * 响应成功状态码和状态信息，不携带数据
     *
     * @return {@link RespBean}
     */
    public static RespBean success() {
        return new RespBean(RespEnum.SUCCESS.getCode(), RespEnum.SUCCESS.getMsg(), null);
    }

    /**
     * 响应失败状态码和状态信息，不携带数据
     *
     * @return {@link RespBean}
     */
    public static RespBean error() {
        return new RespBean(RespEnum.ERROR.getCode(), RespEnum.ERROR.getMsg(), null);
    }

    /**
     * 根据枚举返回状态信息
     *
     * @param respEnum 响应枚举
     * @return {@link RespBean}
     */
    public static RespBean error(RespEnum respEnum) {
        return new RespBean(respEnum.getCode(), respEnum.getMsg(), null);
    }
}
