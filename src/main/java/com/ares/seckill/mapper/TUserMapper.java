package com.ares.seckill.mapper;

import com.ares.seckill.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wencai.xu
 * @since 2021-07-24
 */
@Mapper
public interface TUserMapper extends BaseMapper<User> {

    User findUser();
}
