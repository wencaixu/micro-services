package com.ares.seckill.config;

import com.ares.seckill.anno.RateLimiter;
import com.ares.seckill.controller.DemoController;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;


public class ClassTypeTest {

/*    @Test
    public void testClass(){
        Class<DemoController> demoControllerClass = DemoController.class;

        Method[] methods = demoControllerClass.getMethods();

        for (Method method : methods) {
            if(method.isAnnotationPresent(RateLimiter.class)){
                RateLimiter annotation1 = method.getAnnotation(RateLimiter.class);
                System.out.println(annotation1.rate()+"-"+annotation1.time());
            }
        }

        Class<ClassChild> classChildClass = ClassChild.class;
        boolean annotationPresent = classChildClass.isAnnotationPresent(RateLimiter.class);

        if(annotationPresent){
        }

    }*/
}
