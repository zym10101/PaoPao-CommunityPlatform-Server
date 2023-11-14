package com.mise.communitycenter;


import com.mise.communitycenter.mapper.LocalhostMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommunityAdminTest {

    @Autowired
    private LocalhostMapper localhostMapper;

    @Test
    public void init_community_admin() {

    }
}
