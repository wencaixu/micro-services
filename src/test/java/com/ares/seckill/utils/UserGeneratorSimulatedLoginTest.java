package com.ares.seckill.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest
class UserGeneratorSimulatedLoginTest {

    @Autowired
    private UserGeneratorSimulatedLogin userGeneratorSimulatedLogin;

    @Test
    void batchUserGenerator() {
        try {
            userGeneratorSimulatedLogin.batchUserGenerator(10000L,"C:\\Users\\Administrator\\Downloads\\jmeter.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSimulatedLogin(){
        try {
            userGeneratorSimulatedLogin.simulatedLogin("C:\\Users\\Administrator\\Desktop\\jmeter-user.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}