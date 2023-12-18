package com.mise.usercenter.client;

import com.mise.usercenter.domain.entity.User;
import com.mise.usercenter.domain.vo.R;
import com.mise.usercenter.domain.vo.community.CommunityVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("community-center")
public interface CommunityClient {

    @GetMapping("/application/getApplicationBy")
    R<Map<CommunityVO, List<Long>>> getApplicationByAdminId(@RequestParam String userId);
}
