package com.ares.seckill.vo;


public enum RespEnum {

    SUCCESS(200,"成功"),
    ERROR(500,"服务器端异常"),

    LOGIN_ERROR(500210, "用户名或密码不正确"),
    MOBILE_ERROR(500211, "手机号码格式不正确"),
    BIND_ERROR(500212, "参数校验异常"),
    MOBILE_NOT_EXIST(500213, "手机号码不存在"),
    PASSWORD_UPDATE_FAIL(500214, "密码更新失败"),
    NO_LOGIN_OR_NOT_EXIST(500216,"未登录或用户不存在"),
    SESSION_ERROR(500215, "用户不存在"),
    INVALID_REQUEST(500217, "非法請求"),
    GOODS_REPEAT_BUY(500501,"秒杀商品限购一次"),
    GOODS_STOCK_NOT_ENOUGH(500500,"秒杀商品库存不足");

    private int code;
    private String msg;

    RespEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
