package com.mise.communitycenter.controller;


import com.mise.communitycenter.domain.vo.CommunityVO;
import com.mise.communitycenter.domain.vo.MemberVO;
import com.mise.communitycenter.domain.vo.PostVO;
import com.mise.communitycenter.domain.vo.Response;
import com.mise.communitycenter.service.CommunityService;
import com.mise.communitycenter.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/getCommunityById")
    public Response getCommunityById(@RequestParam long communityID) {
        CommunityVO community = communityService.getCommunityById(communityID);
        if(community == null) {
            return Response.failed("查询社区信息失败");
        }
        return Response.success(community);
    }

    @GetMapping("/add-admin")
    public Response addAdmin(@RequestParam long communityId, @RequestParam long userId) {
        boolean res = communityService.addAdmin(communityId, userId);
        if(!res) {
            return Response.failed("添加管理员失败");
        }
        return Response.success();
    }

    @GetMapping("/remove-admin")
    public Response removeAdmin(@RequestParam long communityId, @RequestParam long userId) {
        boolean res = communityService.removeAdmin(communityId, userId);
        if(!res) {
            return Response.failed("删除管理员失败");
        }
        return Response.success();
    }

    @GetMapping("/getCreatedCommunityById")
    public Response getCreatedCommunityById(@RequestParam long userId) {
        List<CommunityVO> communityVOs = new ArrayList<CommunityVO>();
        CommunityVO communityVO = new CommunityVO();
        communityVO.setCommunityID(200);
        communityVO.setName("1111");
        communityVO.setCreateTime(TimeUtil.getCurrentTime());
        communityVO.setPublic(true);
        communityVOs.add(communityVO);
        communityVOs.add(communityVO);
        communityVOs.add(communityVO);
        return Response.success(communityVOs);
    }

    @GetMapping("/getManagedCommunityById")
    public Response getManagedCommunityById(@RequestParam long userId) {
        List<CommunityVO> communityVOs = new ArrayList<CommunityVO>();
        CommunityVO communityVO = new CommunityVO();
        communityVO.setCommunityID(200);
        communityVO.setName("2222");
        communityVO.setCreateTime(TimeUtil.getCurrentTime());
        communityVO.setPublic(true);
        communityVOs.add(communityVO);
        communityVOs.add(communityVO);
        communityVOs.add(communityVO);
        return Response.success(communityVOs);
    }

    @GetMapping("/getJoinedCommunityById")
    public Response getJoinedCommunityById(@RequestParam long userId) {
        List<CommunityVO> communityVOs = new ArrayList<CommunityVO>();
        CommunityVO communityVO = new CommunityVO();
        communityVO.setCommunityID(200);
        communityVO.setName("3333");
        communityVO.setCreateTime(TimeUtil.getCurrentTime());
        communityVO.setPublic(true);
        communityVOs.add(communityVO);
        communityVOs.add(communityVO);
        communityVOs.add(communityVO);
        return Response.success(communityVOs);
    }


}
