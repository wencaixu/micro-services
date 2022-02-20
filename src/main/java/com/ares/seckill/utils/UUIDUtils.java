package com.ares.seckill.utils;


import org.springframework.util.StringUtils;

import java.util.UUID;

public final class UUIDUtils {

    public static String uuid(){
        return StringUtils.replace(UUID.randomUUID().toString(),"-","");
    }

}
