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
            smsService.send("19975106089");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testJoinCommunity() {
        ucService.joinCommunity(1L, 5L);
    }

}
