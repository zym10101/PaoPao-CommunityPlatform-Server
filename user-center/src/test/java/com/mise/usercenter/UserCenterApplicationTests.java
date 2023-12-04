package com.mise.usercenter;

import com.mise.usercenter.service.SmsService;
import com.mise.usercenter.service.UCService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserCenterApplicationTests {
    @Autowired
    private SmsService smsService;

    @Autowired
    private UCService ucService;

    @Test
    void contextLoads() {
    }

    @Test
    void testSms() {
        try {
            smsService.send("18051964286");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
