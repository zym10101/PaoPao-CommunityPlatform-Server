package com.mise.postcenter.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-center")
public interface UserClient {
    @GetMapping("/user/getNameById")
    String getUserNameById(@RequestParam("userId") String userId);
}
