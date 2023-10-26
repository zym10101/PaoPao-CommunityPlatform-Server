package com.mise.usercenter;

import com.alibaba.fastjson.JSONObject;
import com.mise.usercenter.service.SmsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class UserCenterApplicationTests {
    @Autowired
    private SmsService smsService;

    @Test
    void contextLoads() {
    }

    @Test
    void testSms() {
        try {
            smsService.send("19975106089");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
