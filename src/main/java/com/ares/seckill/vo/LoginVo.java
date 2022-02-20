package com.ares.seckill.vo;

import com.ares.seckill.validator.IsMobile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {

    /**
     * 手机号，唯一主键
     */
    @NotNull
    @IsMobile
    private Long mobile;

    /**
     * 用户密码
     */
    @NotNull
    @Length(max = 32)
    private String password;
}
