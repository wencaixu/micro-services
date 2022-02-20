package com.ares.seckill.anno;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Documented
@Inherited
@Target(value={ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {

    int rate() default 3;

    int time() default 0;

}
