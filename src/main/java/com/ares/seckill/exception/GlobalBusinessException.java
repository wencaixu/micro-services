package com.ares.seckill.exception;

import com.ares.seckill.vo.RespEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalBusinessException extends RuntimeException {
    private RespEnum respEnum;
}

