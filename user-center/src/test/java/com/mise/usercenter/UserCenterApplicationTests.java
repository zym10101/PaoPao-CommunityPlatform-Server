package com.mise.usercenter;

import com.mise.usercenter.domain.entity.User;
import com.mise.usercenter.domain.vo.community.CommunityVO;
import com.mise.usercenter.service.SmsService;
import com.mise.usercenter.service.UCService;
import com.mise.usercenter.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;


@SpringBootTest
class UserCenterApplicationTests {
    @Autowired
    private SmsService smsService;

    @Autowired
    private UCService ucService;

    @Autowired
    private UserService userService;

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

    @Test
    void test_user_community() {
        Map<CommunityVO, List<User>> applicationByAdminId = userService.getApplicationByAdminId("1");
    }



}
