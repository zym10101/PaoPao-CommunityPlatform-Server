package com.mise.communitycenter.controller;


import com.mise.communitycenter.domain.vo.CommunityVO;
import com.mise.communitycenter.domain.vo.MemberVO;
import com.mise.communitycenter.domain.vo.PostVO;
import com.mise.communitycenter.domain.vo.Response;
import com.mise.communitycenter.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @PostMapping("/createCommunity")
    public Response createCommunity(@RequestParam long userID, @RequestBody CommunityVO communityVO) {
        boolean result = communityService.createCommunity(userID, communityVO);
        if(!result) {
            return Response.failed();
        }
        return Response.success();
    }

    @GetMapping("/getCommunityMembers")
    public Response getCommunityMembers(@RequestParam long communityID) {
        List<MemberVO> members = communityService.getCommunityMembers(communityID);
        if(members == null) {
            return Response.failed();
        }
        return Response.success(members);
    }

    @GetMapping("/getCommunityPosts")
    public Response getCommunityPosts(@RequestParam long communityID) {
        List<PostVO> posts = communityService.getPosts(communityID);
        if(posts == null) {
            return Response.failed();
        }
        return Response.success(posts);
    }

    @GetMapping("/deleteMember")
    public Response deleteMember(@RequestParam long communityID, @RequestParam long memberID) {
        boolean result = communityService.deleteMember(communityID, memberID);
        if(!result) {
            return Response.failed("删除成员失败");
        }
        return Response.success();
    }

    @GetMapping("/addMember")
    public Response addMember(@RequestParam long communityID, @RequestParam long memberID) {
        boolean result = communityService.addMember(communityID, memberID);
        if(!result) {
            return Response.failed("添加成员失败");
        }
        return Response.success();
    }


}
