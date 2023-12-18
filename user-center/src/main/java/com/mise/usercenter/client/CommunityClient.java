package com.mise.usercenter.client;


import com.mise.communitycenter.domain.vo.ApplicationCheckVO;
import com.mise.communitycenter.domain.vo.CommunityVO;
import com.mise.usercenter.config.FeignConfig;
import com.mise.usercenter.domain.vo.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@FeignClient("community-center")
@Import(FeignConfig.class)
public interface CommunityClient {


    @GetMapping("/application/getApplicationByAdminId")
    Response<Object> getApplicationByAdminId(@RequestParam long adminID);
}