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
        Random random = new Random();
        int code = random.nextInt(100000, 999999);
        System.out.println(code);
        System.out.println(JSONObject.toJSONString(new JSONObject().fluentPut("code", code)));
    }

}
